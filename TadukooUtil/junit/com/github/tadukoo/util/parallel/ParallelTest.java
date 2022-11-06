package com.github.tadukoo.util.parallel;

import com.github.tadukoo.util.ListUtil;
import com.github.tadukoo.util.logger.EasyLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParallelTest{
	
	private static final List<Integer> psalmsChapterVerses = ListUtil.createList(
			6, 12, 8, 8, 12, 10, 17, 9, 20, 18, 7, 8, 6, 7, 5, 11, 15, 50, 14, 9, 13,
			31, 6, 10, 22, 12, 14, 9, 11, 12, 24, 11, 22, 22, 28, 12, 40, 22, 13, 17, 13, 11,
			5, 26, 17, 11, 9, 14, 20, 23, 19, 9, 6, 7, 23, 13, 11, 11, 17, 12, 8, 12, 11, 10,
			13, 20, 7, 35, 36, 5, 24, 20, 28, 23, 10, 12, 20, 72, 13, 19, 16, 8, 18, 12, 13,
			17, 7, 18, 52, 17, 16, 15, 5, 23, 11, 13, 12, 9, 9, 5, 8, 28, 22, 35, 45, 48, 43,
			13, 31, 7, 10, 10, 9, 8, 18, 19, 2, 29, 176, 7, 8, 9, 4, 8, 5, 6, 5, 6, 8, 8, 3,
			18, 3, 3, 21, 26, 9, 8, 24, 13, 10, 7, 12, 15, 21, 10, 20, 14, 9, 6);
	private static Properties psalmsFile;
	private Properties result;
	private EasyLogger logger;
	
	// This just exists to not have a logger
	private static class DummyLogger extends Logger{
		
		public DummyLogger(String name){
			super(name, null);
		}
		
		@Override
		public void log(Level level, String message){
			throw new RuntimeException(level.toString() + ": " + message);
		}
		
		@Override
		public void log(Level level, String message, Throwable t){
			throw new RuntimeException(t);
		}
	}
	
	@BeforeEach
	public void setup() throws IOException{
		psalmsFile = new Properties();
		psalmsFile.load(new FileInputStream("junit-resource/Psalms.properties"));
		result = null;
		logger = new EasyLogger(new DummyLogger("Testing Parallel stuff"));
	}
	
	private static class ChapterWorkInfo{
		private final Integer chapter;
		private List<String> pages;
		
		public ChapterWorkInfo(Integer chapter){
			this.chapter = chapter;
			pages = new ArrayList<>();
		}
		
		public Integer getChapter(){
			return chapter;
		}
		
		public List<String> getPages(){
			return pages;
		}
		
		public void setPages(List<String> pages){
			this.pages = pages;
		}
	}
	
	public static class ChapterWorker extends ParallelWorker<ChapterWorkInfo>{
		
		public ChapterWorker(EasyLogger logger, Queue<ChapterWorkInfo> todoQueue, Queue<ChapterWorkInfo> doneQueue){
			super(logger, todoQueue, doneQueue);
		}
		
		@Override
		protected boolean checkToContinueWork(ChapterWorkInfo work){
			return work.getChapter() != null;
		}
		
		@Override
		protected void doWork(ChapterWorkInfo work){
			int chapter = work.getChapter();
			
			List<String> pages = new ArrayList<>();
			StringBuilder page = new StringBuilder("Chapter ").append(chapter).append("\n");
			
			List<String> verses = new ArrayList<>();
			for(int i = 1; i <= psalmsChapterVerses.get(chapter-1); i++){
				verses.add(psalmsFile.getProperty("ch" + chapter + "v" + i));
			}
			for(int i = 0; i < verses.size(); i++){
				if(page.length() + verses.get(i).length() + 9 <= 256){
					if(i == 0){
						page.append(verses.get(i));
					}else{
						page.append(" &l").append(i + 1).append("&r").append(verses.get(i));
					}
				}else{
					String[] verseSplit = verses.get(i).split(" ");
					if(page.length() + verseSplit[0].length() + 9 > 256){
						pages.add(page.toString());
						page = new StringBuilder("&l").append(i + 1).append("&r").append(verses.get(i));
					}else{
						page.append(" &l").append(i + 1).append("&r").append(verseSplit[0]);
						for(int j = 1; j < verseSplit.length; j++){
							if(page.length() + verseSplit[j].length() + 1 > 256){
								pages.add(page.toString());
								page = new StringBuilder(verseSplit[j]);
							}else{
								page.append(" ").append(verseSplit[j]);
							}
						}
					}
				}
			}
			pages.add(page.toString());
			work.setPages(pages);
		}
	}
	
	private class GenerateBookParallel extends ParallelRunner<ChapterWorkInfo>{
		
		protected GenerateBookParallel(EasyLogger logger){
			super(logger, 150, 10, ChapterWorker.class);
		}
		
		@Override
		protected void doWork(Queue<ChapterWorkInfo> todoQueue, Queue<ChapterWorkInfo> doneQueue){
			// Send out work
			int chps = psalmsChapterVerses.size();
			for(int j = 1; j <= chps; j++){
				ChapterWorkInfo work = new ChapterWorkInfo(j);
				try{
					todoQueue.enqueue(work);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			
			// Receive work
			Properties bookFile = new Properties();
			int bookNum = 1, pageNum = 1;
			int chpDone = 0;
			List<ChapterWorkInfo> works = new ArrayList<>();
			for(int j = 1; j <= chps; j++){
				try{
					ChapterWorkInfo info = doneQueue.dequeue();
					if(info.getChapter() == chpDone + 1){
						for(String page: info.getPages()){
							bookFile.put("Book" + bookNum + "Page" + pageNum, page);
							pageNum++;
							if(pageNum > 50){
								bookNum++;
								pageNum = 1;
							}
						}
						chpDone++;
						while(works.size() > 0 && works.get(0).getChapter() == chpDone + 1){
							info = works.remove(0);
							for(String page: info.getPages()){
								bookFile.put("Book" + bookNum + "Page" + pageNum, page);
								pageNum++;
								if(pageNum > 50){
									bookNum++;
									pageNum = 1;
								}
							}
							chpDone++;
						}
					}else{
						boolean infoInArray = false;
						for(int k = 0; k < works.size(); k++){
							if(works.get(k).getChapter() > info.getChapter()){
								works.add(k, info);
								infoInArray = true;
								break;
							}
						}
						if(!infoInArray){
							works.add(info);
						}
					}
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			result = bookFile;
		}
		
		@Override
		protected void sendTerminateInfo(Queue<ChapterWorkInfo> todoQueue) throws InterruptedException{
			todoQueue.enqueue(new ChapterWorkInfo(null));
		}
	}
	
	@Test
	public void parallelTest()
			throws InterruptedException, InvocationTargetException, NoSuchMethodException,
			InstantiationException, IllegalAccessException, IOException{
		// Run the parallel runner
		GenerateBookParallel parallelRunner = new GenerateBookParallel(logger);
		parallelRunner.runParallelWork();
		
		// Verify the result based on a pre-created result
		Properties expectedProperties = new Properties();
		expectedProperties.load(new FileInputStream("junit-resource/Psalms-MinecraftBook.properties"));
		for(Object key: expectedProperties.keySet()){
			String keyString = (String) key;
			assertEquals(expectedProperties.getProperty(keyString), result.get(keyString));
		}
	}
}
