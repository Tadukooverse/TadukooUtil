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

/**
 * This class is used to verify that files match a particular {@link FileFormatSchema}.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public class FileFormatSchemaVerification{
	
	// Not allowed to make a FileFormatSchemaVerification instance
	private FileFormatSchemaVerification(){ }
	
	/**
	 * Checks if the file located at the given file path matches the given 
	 * {@link FileFormatSchema} or not.
	 * 
	 * @param logger The Logger to log any messages
	 * @param schema The FileFormatSchema to use for checking against
	 * @param filepath The path to the file to be checked
	 * @return If the file matches the formatting or not
	 */
	public static boolean verifyFileFormat(Logger logger, FileFormatSchema schema, String filepath){
		logger.log(Level.INFO, "Starting verification of file " + filepath + "...");
		// This will be the return value. It gets set false on any failure to match the FileFormatSchema
		boolean correctFileFormat = true;
		
		// Grab filename off the file path
		String[] filepathPieces = filepath.split("/");
		String filename = filepathPieces[filepathPieces.length - 1];
		// Grab the file extension off the file name
		int periodIndex = filename.indexOf('.');
		String fileExtension = filename.substring(periodIndex);
		
		// Verify that the FileFormatSchema's fileExtension matches the one on the actual file
		if(!schema.getFileExtension().equalsIgnoreCase(fileExtension)){
			logger.log(Level.SEVERE, "File Extension doesn't match!\n"
					+ "* Expected: " + schema.getFileExtension() + ", but was: " + fileExtension + "!");
			correctFileFormat = false;
		}
		
		// Convert the List of nodes from FileFormatSchema to a Map for easier access to a particularly named FormatNode
		Map<String, FormatNode> nodes = getFormatNodesMap(schema);
		
		// Load the file
		Node headNode = Node.loadFromFile(filepath);
		boolean goodNodes = verifyNode(logger, nodes, headNode, new ArrayList<String>(Arrays.asList("head")), filepath);
		
		// Update correctFileFormat to false if the nodes failed
		correctFileFormat = correctFileFormat && goodNodes;
		
		// Give a logger message on whether the format of the file matched or not
		if(correctFileFormat){
			logger.log(Level.INFO, "File: " + filepath + " matches the FileFormatSchema!");
		}else{
			logger.log(Level.WARNING, "File: " + filepath + " does not match the FileFormatSchema!");
		}
		
		// Return whether the file's format matches that of the FileFormatSchema or not
		return correctFileFormat;
	}
	
	/**
	 * Creates a Map out of the {@link FormatNode}s present in the given {@link FileFormatSchema}.
	 * 
	 * @param schema The FileFormatSchema to grab the FormatNodes off of
	 * @return A mapping of FormatNode names to the FormatNodes themselves
	 */
	private static Map<String, FormatNode> getFormatNodesMap(FileFormatSchema schema){
		// Grab the List of Nodes from the schema
		List<FormatNode> nodes = schema.getFormatNodes();
		
		// Make a Map of the Nodes' names to the Nodes themselves
		Map<String, FormatNode> nodesMap = new HashMap<String, FormatNode>();
		for(FormatNode node: nodes){
			nodesMap.put(node.getName(), node);
		}
		
		return nodesMap;
	}
	
	/**
	 * Checks if the given Node (and any children and siblings down the line) matches 
	 * the proper formatting passed in or not.
	 * 
	 * @param logger The Logger to log any messages
	 * @param formatNodes The FormatNodes as a Map of their names to them
	 * @param node The Node to be tested
	 * @param nodeNames The allowed names for this particular Node's format
	 * @param filepath The path to the file (used in some formatting)
	 * @return If the Node matches the formatting appropriately or not
	 */
	private static boolean verifyNode(Logger logger, Map<String, FormatNode> formatNodes, Node node, 
			List<String> nodeNames, String filepath){
		// Remove null node from the list of names if it's there and determine if it's allowed
		boolean nullAllowed = nodeNames.remove(FormatNode.NULL_NODE);
		
		// Need to determine if the passed-in Node is a good one or not
		boolean goodNode = false;
		int i = 0;
		String name;
		FormatNode format = null;
		
		if(node == null){
			// If Node is null, it's only good if null is allowed
			goodNode = nullAllowed;
		}else{
			// If Node isn't null, compare its format with the allowed formats until we find a match
			while(!goodNode && i < nodeNames.size()){
				// Grab current Node name to check
				name = nodeNames.get(i);
				// Grab FormatNode with the given name
				format = formatNodes.get(name);
				
				// Check if the title of this Node matches the FormatNode's format
				boolean titleMatch = verifyFormat(filepath, format.getTitleRegex(), node.getTitle());
				// If title doesn't match, give a Logger message
				if(!titleMatch){
					logger.log(Level.FINE, "Title doesn't match!\n"
							+ "* In checking Node " + node.toString() + " as a " + name + "\n"
							+ "* Format Expected: " + format.getTitleRegex() + "\n"
							+ "* Title Received: " + node.getTitle());
				}
				
				// Check if the data of this Node matches the FormatNode's format
				boolean dataMatch = verifyFormat(filepath, format.getDataRegex(), node.getData());
				// If data doesn't match, give a Logger message
				if(!dataMatch){
					logger.log(Level.FINE, "Data doesn't match!\n"
							+ "* In checking Node " + node.toString() + " as a " + name + "\n"
							+ "* Format Expected: " + format.getDataRegex() + "\n"
							+ "* Data Received: " + node.getData());
				}
				
				// Check if the level of this Node matches the FormatNode's required level
				boolean levelMatch = format.getLevel() == node.getLevel();
				// If level doesn't match, give a Logger message
				if(!levelMatch){
					logger.log(Level.FINE, "Incorrect Node level!\n"
							+ "* In checking Node " + node.toString() + " as a " + name + "\n"
							+ "* Expected: " + format.getLevel() + ", but was: " + node.getLevel() + "!");
				}
				
				// It's a good Node if all things matched
				goodNode = titleMatch && dataMatch && levelMatch;
				
				// If not a good Node, give a Logger message
				if(!goodNode){
					logger.log(Level.FINE, "Node does not match the " + name + " FormatNode!");
				}
				
				// Increment i to move to next name if it's not a match
				i++;
			}
			
			// If no match was made, we got an issue
			if(!goodNode){
				logger.log(Level.WARNING, "Node could not be identified!");
			}else{
				// If it's a match, continue in checking child and next sibling
				// Note: Parent and PrevSibling are not checked due to proper relationships being enforced elsewhere
				boolean childMatch = verifyNode(logger, formatNodes, node.getChild(), format.getChildNames(), filepath);
				boolean nextSiblingMatch = verifyNode(logger, formatNodes, node.getNextSibling(), format.getNextSiblingNames(), 
						filepath);
				
				// If child or next sibling fail, we got issues
				goodNode = childMatch && nextSiblingMatch;
			}
		}
		
		// If it's a good Node, give a Logger message about it
		if(goodNode && node != null){
			logger.log(Level.FINER, "This was a good node!\n"
					+ "* Format: " + format.getNodeRegex() + "\n"
					+ "* Actual: " + node.toString());
		}
		
		return goodNode;
	}
	
	/**
	 * Checks if the formatting of the given String matches the given formatting. 
	 * The filepath is passed in to be replaced in the regex if any TFormatting is 
	 * present specific to filepath variables.
	 * 
	 * @param filepath The path to the file
	 * @param regex The formatting to match against
	 * @param actual The String to be tested for formatting conformity
	 * @return If the String matches the formatting or not
	 */
	private static boolean verifyFormat(String filepath, String regex, String actual){
		// Get filename for use in matching against <filename> in the FileFormatSchema
		String[] filepathPieces = filepath.split("/");
		String filename = filepathPieces[filepathPieces.length - 1];
		
		// Get fileTitle and fileExtension for future use
		int periodIndex = filename.indexOf('.');
		// FileTitle is used in matching against <filetitle> in the FileFormatSchema
		String fileTitle = filename.substring(0, periodIndex);
		// FileExtension is used in matching against <fileExtension> in the FileFormatSchema
		String fileExtension = filename.substring(periodIndex);
		
		// Replace <filename> (used in TFormatting) with the actual filename
		if(regex.contains("<filename>")){
			regex = regex.replaceAll("<filename>", filename);
		}
		
		// Replace <fileTitle> (used in TFormatting) with the actual fileTitle
		if(regex.contains("<fileTitle>")){
			regex = regex.replaceAll("<fileTitle>", fileTitle);
		}
		
		// Replace <fileExtension> (used in TFormatting) with the actual fileExtension
		if(regex.contains("<fileExtension>")){
			regex = regex.replaceAll("<fileExtension>", fileExtension);
		}
		
		// Create the Pattern and a Matcher for it
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(actual);
		
		// Check if the given string actually matches the formatting
		return matcher.matches();
	}
}
