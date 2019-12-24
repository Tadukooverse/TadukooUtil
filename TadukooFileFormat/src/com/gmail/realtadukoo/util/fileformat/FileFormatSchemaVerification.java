package com.gmail.realtadukoo.util.fileformat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFormatSchemaVerification{
	
	public static boolean verifyFileFormat(Logger logger, FileFormatSchema schema, String filepath){
		logger.log(Level.INFO, "Starting verification of file " + filepath + "...");
		// This will be the return value. It gets set false on any failure to match the FileFormatSchema
		boolean correctFileFormat = true;
		
		// Get fileExtension to check against FileFormatSchema's fileExtension
		String[] filepathPieces = filepath.split("/");
		String filename = filepathPieces[filepathPieces.length - 1];
		int firstPeriodIndex = filename.split("\\.")[0].length();
		String fileExtension = filename.substring(firstPeriodIndex);
		
		// Verify that the FileFormatSchema's fileExtension matches the one passed in
		if(!schema.getFileExtension().equalsIgnoreCase(fileExtension)){
			logger.log(Level.SEVERE, "File Extension doesn't match!\n"
					+ "* Expected: " + schema.getFileExtension() + ", but was: " + fileExtension + "!");
			correctFileFormat = false;
		}
		
		// Convert the ArrayList of nodes from FileFormatSchema to a HashMap for easier access to a particularly named FormatNode
		Map<String, FormatNode> nodes = getFormatNodesHashMap(schema);
		
		// Load the file
		Node headNode = Node.loadFromFile(filepath);
		boolean goodNodes = verifyNode(logger, nodes, headNode, new ArrayList<String>(Arrays.asList("head")), filepath);
		
		// Update correctFileFormat to false if the nodes failed
		correctFileFormat = correctFileFormat && goodNodes;
		
		if(correctFileFormat){
			logger.log(Level.INFO, "File: " + filepath + " matches the FileFormatSchema!");
		}else{
			logger.log(Level.WARNING, "File: " + filepath + " does not match the FileFormatSchema!");
		}
		
		// Return whether the file's format matches that of the FileFormatSchema or not
		return correctFileFormat;
	}
	
	private static Map<String, FormatNode> getFormatNodesHashMap(FileFormatSchema schema){
		ArrayList<FormatNode> nodes = schema.getFormatNodes();
		HashMap<String, FormatNode> nodesMap = new HashMap<String, FormatNode>();
		for(FormatNode node: nodes){
			nodesMap.put(node.getName(), node);
		}
		return nodesMap;
	}
	
	private static boolean verifyNode(Logger logger, Map<String, FormatNode> formatNodes, Node node, 
			List<String> nodeNames, String filepath){
		boolean goodNode = false;
		int i = 0;
		String name;
		FormatNode format = null;
		
		if(node == null){
			if(nodeNames.contains("<null>")){
				goodNode = true;
			}
		}else{
			while(!goodNode && i < nodeNames.size()){
				name = nodeNames.get(i);
				if(!name.equalsIgnoreCase("<null>")){
					format = formatNodes.get(name);
					boolean titleMatch = verifyFormat(filepath, format.getTitleRegex(), node.getTitle());
					if(!titleMatch){
						logger.log(Level.FINE, "Title doesn't match!\n"
								+ "* In checking Node " + node.toString() + " as a " + name + "\n"
								+ "* Format Expected: " + format.getTitleFormat() + "\n"
								+ "* Title Received: " + node.getTitle());
					}
					boolean dataMatch = verifyFormat(filepath, format.getDataRegex(), node.getData());
					if(!dataMatch){
						logger.log(Level.FINE, "Data doesn't match!\n"
								+ "* In checking Node " + node.toString() + " as a " + name + "\n"
								+ "* Format Expected: " + format.getDataFormat() + "\n"
								+ "* Data Received: " + node.getData());
					}
					boolean levelMatch = format.getLevel() == node.getLevel();
					if(!levelMatch){
						logger.log(Level.FINE, "Incorrect Node level!\n"
								+ "* In checking Node " + node.toString() + " as a " + name + "\n"
								+ "* Expected: " + format.getLevel() + ", but was: " + node.getLevel() + "!");
					}
					goodNode = titleMatch && dataMatch && levelMatch;
					if(!goodNode){
						logger.log(Level.FINE, "Node does not match the " + name + " FormatNode!");
					}
				}
				i++;
			}
			
			if(!goodNode){
				logger.log(Level.WARNING, "Node could not be identified!");
			}else{
				// Note: Parent and PrevSibling are not checked due to proper relationships being enforced elsewhere
				boolean childMatch = verifyNode(logger, formatNodes, node.getChild(), format.getChildNames(), filepath);
				boolean nextSiblingMatch = verifyNode(logger, formatNodes, node.getNextSibling(), format.getNextSiblingNames(), 
						filepath);
				goodNode = childMatch && nextSiblingMatch;
			}
		}
		
		if(goodNode && node != null){
			logger.log(Level.FINER, "This was a good node!\n"
					+ "* Format: " + format.getNodeFormat() + "\n"
					+ "* Actual: " + node.toString());
		}
		
		return goodNode;
	}
	
	private static boolean verifyFormat(String filepath, String regex, String actual){
		// Get filename for use in matching against <filename> in the FileFormatSchema
		String[] filepathPieces = filepath.split("/");
		String filename = filepathPieces[filepathPieces.length - 1];
		
		// Get fileTitle and fileExtension for future use
		int firstPeriodIndex = filename.split("\\.")[0].length();
		// FileTitle is used in matching against <filetitle> in the FileFormatSchema
		String fileTitle = filename.substring(0, firstPeriodIndex);
		// FileExtension is used in matching against <fileExtension> in the FileFormatSchema
		String fileExtension = filename.substring(firstPeriodIndex);
		
		if(regex.contains("<filename>")){
			regex = regex.replaceAll("<filename>", filename);
		}
		if(regex.contains("<fileTitle>")){
			regex = regex.replaceAll("<fileTitle>", fileTitle);
		}
		if(regex.contains("<fileExtension>")){
			regex = regex.replaceAll("<fileExtension>", fileExtension);
		}
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(actual);
		
		return matcher.matches();
	}
}
