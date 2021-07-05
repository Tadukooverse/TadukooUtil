package com.github.tadukoo.util;

/**
 * Util functions for dealing with bytes.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.5
 */
public final class ByteUtil{
	
	/** An array containing 0-9 and then A-F, used for converting to hex */
	public static final char[] hexChars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
													'A', 'B', 'C', 'D', 'E', 'F'};
	
	/** Not allowed to create a ByteUtil */
	private ByteUtil(){ }
	
	/**
	 * Parse the given String into a byte. String should be a binary representation of a byte
	 *
	 * @param byteString The binary representation of a byte
	 * @return A Byte parsed from the given binary String
	 */
	public static Byte parseByte(String byteString){
		return (byte) Integer.parseInt(byteString, 2);
	}
	
	/**
	 * Parse the given byte into a binary string
	 *
	 * @param bite The byte to be converted to a binary string
	 * @return The binary string representation of the byte
	 */
	public static String toBinaryString(byte bite){
		return String.format("%8s", Integer.toBinaryString(bite & 0xFF)).replace(' ', '0');
	}
	
	/**
	 * Gets the bit at the given position
	 *
	 * @param bite The byte to grab a bit from
	 * @param position The position of the bit to be grabbed
	 * @return The value of the bit at the given position
	 */
	public static int getBit(byte bite, int position){
		return (bite >>> position) & 1;
	}
	
	/**
	 * Sets the bit at the given position (to 1/true)
	 *
	 * @param bite The byte to set the bit on
	 * @param position The position of the bit to be set
	 * @return A byte with the given bit set
	 */
	public static byte setBit(byte bite, int position){
		return (byte) (bite | (1 << position));
	}
	
	/**
	 * Clears the bit at the given position (sets to 0/false)
	 *
	 * @param bite The byte to clear the bit on
	 * @param position The position of the bit to be cleared
	 * @return A byte with the given bit cleared
	 */
	public static byte clearBit(byte bite, int position){
		return (byte) (bite & ~(1 << position));
	}
	
	/**
	 * Toggles the bit at the given position
	 *
	 * @param bite The byte to toggle the bit on
	 * @param position The position of the bit to be toggled
	 * @return A byte with the given bit toggled
	 */
	public static byte toggleBit(byte bite, int position){
		return (byte) (bite ^ (1 << position));
	}
	
	/**
	 * Checks if the bit at the given position is set or not
	 *
	 * @param bite The byte to check a bit from
	 * @param position The position of the bit to be checked
	 * @return true if the bit is set (equal to 1) or false if not set (equal to 0)
	 */
	public static boolean checkBit(byte bite, int position){
		return getBit(bite, position) == 1;
	}
	
	/**
	 * Converts the given byte to a 2 digit hex string
	 *
	 * @param bite The byte to convert to hex
	 * @return The hex representation of the given byte
	 */
	public static String toHex(byte bite){
		return String.valueOf(hexChars[(bite >>> 4) & 0xF]) + hexChars[bite & 0xF];
	}
	
	/**
	 * Converts the given byte array into a hex string
	 *
	 * @param bytes The bytes to convert to hex
	 * @return The hex representation of the given bytes
	 */
	public static String toHex(byte[] bytes){
		StringBuilder hex = new StringBuilder();
		for(byte bite: bytes){
			hex.append(toHex(bite));
		}
		return hex.toString();
	}
	
	/**
	 * Converts the given hex character to an int
	 *
	 * @param hexChar The hex character to convert
	 * @return The int value of the hex character, or -1 if invalid
	 */
	public static int hexToInt(char hexChar){
		if('0' <= hexChar && hexChar <= '9'){
			return hexChar - '0';
		}else if('A' <= hexChar && hexChar <= 'F'){
			return hexChar - 'A' + 10;
		}else if('a' <= hexChar && hexChar <= 'f'){
			return hexChar - 'a' + 10;
		}else{
			return -1;
		}
	}
	
	/**
	 * Convert the given hex string into a byte array
	 *
	 * @param hex The hex string to convert
	 * @return A byte array representing the given hex string
	 */
	public static byte[] fromHex(String hex){
		int size = hex.length();
		
		// Check that the size is even
		if(size % 2 != 0){
			throw new IllegalArgumentException("hex string must be an even length: " + hex);
		}
		
		// Create byte array to store the bytes in
		byte[] bites = new byte[size/2];
		
		// Iterate over the string, 2 characters at a time
		for(int i = 0; i < size; i+=2){
			int highNibble = hexToInt(hex.charAt(i));
			int lowNibble = hexToInt(hex.charAt(i+1));
			// If either nibble came out -1, we have an illegal hex character
			if(highNibble == -1 || lowNibble == -1){
				throw new IllegalArgumentException("hex string contains an illegal hex character: " + hex);
			}
			
			bites[i/2] = (byte) (highNibble*16 + lowNibble);
		}
		
		return bites;
	}
}
