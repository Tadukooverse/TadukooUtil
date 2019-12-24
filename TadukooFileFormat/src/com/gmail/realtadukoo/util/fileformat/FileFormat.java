package com.gmail.realtadukoo.util.fileformat;

import java.util.HashMap;

public abstract class FileFormat{
	private HashMap<String, FileFormatSchema> schemas;
	
	public FileFormat(HashMap<String, FileFormatSchema> schemas){
		this.schemas = schemas;
	}
	
	public FileFormatSchema getSchema(String version){
		return schemas.get(version);
	}
	
	public abstract Node updateFile(Node oldFile, String oldVersion, String newVersion);
}
