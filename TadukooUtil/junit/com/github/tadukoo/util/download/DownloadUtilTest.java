package com.github.tadukoo.util.download;

import com.github.tadukoo.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DownloadUtilTest{
	private final String address = "https://github.com/Tadukooverse/TadukooUtil/releases/download/v.0.1-alpha/" +
			"TadukooUtil-0.1-Alpha-SNAPSHOT.jar";
	
	@Test
	public void testGetFileSize() throws MalformedURLException{
		assertEquals(37282L, DownloadUtil.getFileSize(null, new URL(address)));
	}
	
	@Test
	public void testDownloadFile() throws IOException{
		String destDir = "target/junit/";
		FileUtil.createDirectory(destDir);
		String dest = destDir + "TadukooUtil-0.1-Alpha-SNAPSHOT.jar";
		FileUtil.deleteFile(dest);
		DownloadUtil.downloadFile(null, null, address, dest);
		String actual = "junit-resource/TadukooUtil-0.1-Alpha-SNAPSHOT.jar";
		assertArrayEquals(FileUtil.readAsBytes(actual), FileUtil.readAsBytes(dest));
	}
}
