package com.gmail.realtadukoo.util.fileformat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class FormatNode{
	private String name;
	private String titleRegex;
	private String dataRegex;
	private String titleFormat;
	private String dataFormat;
	private int level;
	private List<String> parentNames;
	private List<String> childNames;
	private List<String> prevSiblingNames;
	private List<String> nextSiblingNames;
	
	public static class FormatNodeBuilder{
		private Logger logger = null;
		private String name = null;
		private String titleRegex;
		private String titleFormat = null;
		private String dataRegex = null;
		private String dataFormat = null;
		private int level = -1;
		private List<String> parentNames = null;
		private List<String> childNames = null;
		private List<String> prevSiblingNames = null;
		private List<String> nextSiblingNames = null;
		
		private FormatNodeBuilder(){ }
		
		public FormatNodeBuilder logger(Logger logger){
			this.logger = logger;
			return this;
		}
		
		public FormatNodeBuilder name(String name){
			this.name = name;
			return this;
		}
		
		public FormatNodeBuilder titleRegex(String titleRegex){
			this.titleRegex = titleRegex;
			return this;
		}
		
		public FormatNodeBuilder titleFormat(String titleFormat){
			this.titleFormat = titleFormat;
			return this;
		}
		
		public FormatNodeBuilder dataRegex(String dataRegex){
			this.dataRegex = dataRegex;
			return this;
		}
		
		public FormatNodeBuilder dataFormat(String dataFormat){
			this.dataFormat = dataFormat;
			return this;
		}
		
		public FormatNodeBuilder level(int level){
			this.level = level;
			return this;
		}
		
		public FormatNodeBuilder parentNames(List<String> parentNames){
			this.parentNames = parentNames;
			return this;
		}
		
		public FormatNodeBuilder parentName(String parentName){
			if(parentNames == null){
				parentNames = new ArrayList<>();
			}
			parentNames.add(parentName);
			return this;
		}
		
		private void checkForErrors(){
			List<String> errors = new ArrayList<>();
			
			if(name == null){
				errors.add("Name can't be null!");
			}
			
			if(titleFormat != null && titleRegex != null){
				errors.add("Can't specify both title format and title regex!");
			}
			
			if(dataFormat != null && dataRegex != null){
				errors.add("Can't specify both data format and data regex!");
			}
			
			if(level == -1){
				errors.add("Must specify level!");
			}
			
			if(!errors.isEmpty()){
				throw new IllegalArgumentException("Failed to create FormatNode: " + errors.toString());
			}
		}
		
		public FormatNode build(){
			checkForErrors();
			
			if(titleFormat != null){
				titleRegex = TFormatRegexConverter.convertTFormatToRegex(logger, titleFormat);
			}
			
			if(dataFormat != null){
				dataRegex = TFormatRegexConverter.convertTFormatToRegex(logger, dataFormat);
			}
			
			return new FormatNode(name, titleRegex, dataRegex, level, 
									parentNames, childNames, prevSiblingNames, nextSiblingNames);
		}
	}
	
	private FormatNode(String name, String titleRegex, String dataRegex, int level,
						List<String> parentNames, List<String> childNames, 
						List<String> prevSiblingNames, List<String> nextSiblingNames){
		this.name = name;
		this.titleRegex = titleRegex;
		this.dataRegex = dataRegex;
		this.level = level;
		this.parentNames = parentNames;
		this.childNames = childNames;
		this.prevSiblingNames = prevSiblingNames;
		this.nextSiblingNames = nextSiblingNames;
	}
	
	public FormatNode(Logger logger, String text){
		Node headNode = new Node(text);
		Node formatNode = headNode.getChild();
		Node levelNode = formatNode.getNextSibling();
		Node parentsNode = levelNode.getNextSibling();
		Node childrenNode = parentsNode.getNextSibling();
		Node prevSiblingNode = childrenNode.getNextSibling();
		Node nextSiblingNode = prevSiblingNode.getNextSibling();
		
		initialize(logger, headNode.getData(), formatNode.getTitle(), formatNode.getData(), Integer.parseInt(levelNode.getData()), 
				parseArrayListFromString(parentsNode.getData()), parseArrayListFromString(childrenNode.getData()), 
				parseArrayListFromString(prevSiblingNode.getData()), parseArrayListFromString(nextSiblingNode.getData()));
	}
	
	public FormatNode(Logger logger, String name, String titleFormat, String dataFormat, int level, List<String> parentNames, 
			List<String> childNames, List<String> prevSiblingNames, List<String> nextSiblingNames){
		initialize(logger, name, titleFormat, dataFormat, level, parentNames, childNames, prevSiblingNames, nextSiblingNames);
	}
	
	private void initialize(Logger logger, String name, String titleFormat, String dataFormat, int level, 
			List<String> parentNames, List<String> childNames, List<String> prevSiblingNames, 
			List<String> nextSiblingNames){
		this.name = name;
		this.titleFormat = titleFormat;
		this.titleRegex = TFormatRegexConverter.convertTFormatToRegex(logger, this.titleFormat);
		this.dataFormat = dataFormat;
		this.dataRegex = TFormatRegexConverter.convertTFormatToRegex(logger, this.dataFormat);
		this.level = level;
		if(parentNames == null){
			this.parentNames = new ArrayList<String>();
			this.parentNames.add("<null>");
		}else{
			this.parentNames = parentNames;
		}
		if(childNames == null){
			this.childNames = new ArrayList<String>();
			this.childNames.add("<null>");
		}else{
			this.childNames = childNames;
		}
		if(prevSiblingNames == null){
			this.prevSiblingNames = new ArrayList<String>();
			this.prevSiblingNames.add("<null>");
		}else{
			this.prevSiblingNames = prevSiblingNames;
		}
		if(nextSiblingNames == null){
			this.nextSiblingNames = new ArrayList<String>();
			this.nextSiblingNames.add("<null>");
		}else{
			this.nextSiblingNames = nextSiblingNames;
		}
	}
	
	public String getName(){
		return name;
	}
	
	public String getTitleRegex(){
		return titleRegex;
	}
	
	public String getDataRegex(){
		return dataRegex;
	}
	
	public String getTitleFormat(){
		return titleFormat;
	}
	
	public String getDataFormat(){
		return dataFormat;
	}
	
	public String getNodeFormat(){
		return titleFormat + ":" + dataFormat;
	}
	
	public int getLevel(){
		return level;
	}
	
	public List<String> getParentNames(){
		return parentNames;
	}
	
	public List<String> getChildNames(){
		return childNames;
	}
	
	public List<String> getPrevSiblingNames(){
		return prevSiblingNames;
	}
	
	public List<String> getNextSiblingNames(){
		return nextSiblingNames;
	}
	
	private List<String> parseArrayListFromString(String text){
		return new ArrayList<String>(Arrays.asList(text.split(",")));
	}
	
	private String formatArrayListAsString(List<String> list){
		String text = list.get(0);
		for(int i = 1; i < list.size(); i++){
			text += "," + list.get(i);
		}
		return text;
	}
	
	public String toText(){
		Node headNode = new Node("Format", name, 0, null, null, null, null);
		Node formatNode = new Node(titleFormat, dataFormat, 1, headNode, null, null, null);
		headNode.setChild(formatNode);
		Node levelNode = new Node("Level", String.valueOf(level), 1, null, null, formatNode, null);
		formatNode.setNextSibling(levelNode);
		Node parentsNode = new Node("parents:", formatArrayListAsString(parentNames), 1, null, null, levelNode, null);
		levelNode.setNextSibling(parentsNode);
		Node childrenNode = new Node("children:", formatArrayListAsString(childNames), 1, null, null, parentsNode, null);
		parentsNode.setNextSibling(childrenNode);
		Node prevSiblingNode = new Node("prevSiblings:", formatArrayListAsString(prevSiblingNames), 1, null, null, 
				childrenNode, null);
		childrenNode.setNextSibling(prevSiblingNode);
		Node nextSiblingNode = new Node("nextSiblings:", formatArrayListAsString(nextSiblingNames), 1, null, null,
				prevSiblingNode, null);
		prevSiblingNode.setNextSibling(nextSiblingNode);
		return headNode.toString();
	}
}
