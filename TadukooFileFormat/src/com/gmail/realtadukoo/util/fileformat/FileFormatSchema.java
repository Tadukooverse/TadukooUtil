package com.gmail.realtadukoo.util.fileformat;

import java.util.ArrayList;

public class FileFormatSchema{
	private String fileExtension;
	private ArrayList<FormatNode> formatNodes;
	
	public FileFormatSchema(String fileExtension, ArrayList<FormatNode> formatNodes){
		this.fileExtension = fileExtension;
		this.formatNodes = formatNodes;
	}
	
	public String getFileExtension(){
		return fileExtension;
	}
	
	public ArrayList<FormatNode> getFormatNodes(){
		return formatNodes;
	}
}
