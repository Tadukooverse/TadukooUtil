package com.github.tadukoo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ByteUtilTest{
	
	@Test
	public void testParseByte(){
		assertEquals(85, ByteUtil.parseByte("01010101").intValue());
	}
	
	@Test
	public void testParseByteNegative(){
		assertEquals(-1, ByteUtil.parseByte("11111111").intValue());
	}
	
	@Test
	public void testToBinaryString(){
		assertEquals("01010101", ByteUtil.toBinaryString(ByteUtil.parseByte("01010101")));
	}
	
	@Test
	public void testToBinaryStringNegative(){
		assertEquals("11111111", ByteUtil.toBinaryString(ByteUtil.parseByte("11111111")));
	}
	
	@Test
	public void testGetBit0(){
		byte bite = Byte.parseByte("00000001", 2);
		assertEquals(1, ByteUtil.getBit(bite, 0));
	}
	
	@Test
	public void testGetBit1(){
		byte bite = Byte.parseByte("1111101", 2);
		assertEquals(0, ByteUtil.getBit(bite, 1));
	}
	
	@Test
	public void testGetBit2(){
		byte bite = Byte.parseByte("00000100", 2);
		assertEquals(1, ByteUtil.getBit(bite, 2));
	}
	
	@Test
	public void testGetBit3(){
		byte bite = Byte.parseByte("1110111", 2);
		assertEquals(0, ByteUtil.getBit(bite, 3));
	}
	
	@Test
	public void testGetBit4(){
		byte bite = Byte.parseByte("00010000", 2);
		assertEquals(1, ByteUtil.getBit(bite, 4));
	}
	
	@Test
	public void testGetBit5(){
		byte bite = Byte.parseByte("1011111", 2);
		assertEquals(0, ByteUtil.getBit(bite, 5));
	}
	
	@Test
	public void testGetBit6(){
		byte bite = Byte.parseByte("01000000", 2);
		assertEquals(1, ByteUtil.getBit(bite, 6));
	}
	
	@Test
	public void testGetBit7(){
		byte bite = Byte.parseByte("01111111", 2);
		assertEquals(0, ByteUtil.getBit(bite, 7));
	}
	
	@Test
	public void testSetBit0(){
		byte bite = Byte.parseByte("00000000", 2);
		bite = ByteUtil.setBit(bite, 0);
		assertEquals(1, bite);
	}
	
	@Test
	public void testSetBit1(){
		byte bite = Byte.parseByte("00000000", 2);
		bite = ByteUtil.setBit(bite, 1);
		assertEquals(2, bite);
	}
	
	@Test
	public void testSetBit2(){
		byte bite = Byte.parseByte("00000000", 2);
		bite = ByteUtil.setBit(bite, 2);
		assertEquals(4, bite);
	}
	
	@Test
	public void testSetBit3(){
		byte bite = Byte.parseByte("00000000", 2);
		bite = ByteUtil.setBit(bite, 3);
		assertEquals(8, bite);
	}
	
	@Test
	public void testSetBit4(){
		byte bite = Byte.parseByte("00000000", 2);
		bite = ByteUtil.setBit(bite, 4);
		assertEquals(16, bite);
	}
	
	@Test
	public void testSetBit5(){
		byte bite = Byte.parseByte("00000000", 2);
		bite = ByteUtil.setBit(bite, 5);
		assertEquals(32, bite);
	}
	
	@Test
	public void testSetBit6(){
		byte bite = Byte.parseByte("00000000", 2);
		bite = ByteUtil.setBit(bite, 6);
		assertEquals(64, bite);
	}
	
	@Test
	public void testSetBit7(){
		byte bite = Byte.parseByte("01111111", 2);
		bite = ByteUtil.setBit(bite, 7);
		assertEquals(-1, bite);
	}
	
	@Test
	public void testSetBitAlreadySet(){
		byte bite = Byte.parseByte("00000001", 2);
		assertEquals(1, bite);
		bite = ByteUtil.setBit(bite, 0);
		assertEquals(1, bite);
	}
	
	@Test
	public void testClearBit0(){
		byte bite = Byte.parseByte("01111111", 2);
		bite = ByteUtil.clearBit(bite, 0);
		assertEquals(126, bite);
	}
	
	@Test
	public void testClearBit1(){
		byte bite = Byte.parseByte("01111111", 2);
		bite = ByteUtil.clearBit(bite, 1);
		assertEquals(125, bite);
	}
	
	@Test
	public void testClearBit2(){
		byte bite = Byte.parseByte("01111111", 2);
		bite = ByteUtil.clearBit(bite, 2);
		assertEquals(123, bite);
	}
	
	@Test
	public void testClearBit3(){
		byte bite = Byte.parseByte("01111111", 2);
		bite = ByteUtil.clearBit(bite, 3);
		assertEquals(119, bite);
	}
	
	@Test
	public void testClearBit4(){
		byte bite = Byte.parseByte("01111111", 2);
		bite = ByteUtil.clearBit(bite, 4);
		assertEquals(111, bite);
	}
	
	@Test
	public void testClearBit5(){
		byte bite = Byte.parseByte("01111111", 2);
		bite = ByteUtil.clearBit(bite, 5);
		assertEquals(95, bite);
	}
	
	@Test
	public void testClearBit6(){
		byte bite = Byte.parseByte("01111111", 2);
		bite = ByteUtil.clearBit(bite, 6);
		assertEquals(63, bite);
	}
	
	@Test
	public void testClearBit7(){
		byte bite = Byte.parseByte("01111111", 2);
		bite = ByteUtil.setBit(bite, 7);
		assertEquals(-1, bite);
		bite = ByteUtil.clearBit(bite, 7);
		assertEquals(127, bite);
	}
	
	@Test
	public void testClearBitAlreadyCleared(){
		byte bite = Byte.parseByte("01111111", 2);
		assertEquals(127, bite);
		bite = ByteUtil.clearBit(bite, 7);
		assertEquals(127, bite);
	}
	
	@Test
	public void testToggleBit0(){
		byte bite = Byte.parseByte("01111111", 2);
		bite = ByteUtil.toggleBit(bite, 0);
		assertEquals(126, bite);
	}
	
	@Test
	public void testToggleBit1(){
		byte bite = Byte.parseByte("00000000", 2);
		bite = ByteUtil.toggleBit(bite, 1);
		assertEquals(2, bite);
	}
	
	@Test
	public void testToggleBit2(){
		byte bite = Byte.parseByte("01111111", 2);
		bite = ByteUtil.toggleBit(bite, 2);
		assertEquals(123, bite);
	}
	
	@Test
	public void testToggleBit3(){
		byte bite = Byte.parseByte("00000000", 2);
		bite = ByteUtil.toggleBit(bite, 3);
		assertEquals(8, bite);
	}
	
	@Test
	public void testToggleBit4(){
		byte bite = Byte.parseByte("01111111", 2);
		bite = ByteUtil.toggleBit(bite, 4);
		assertEquals(111, bite);
	}
	
	@Test
	public void testToggleBit5(){
		byte bite = Byte.parseByte("00000000", 2);
		bite = ByteUtil.toggleBit(bite, 5);
		assertEquals(32, bite);
	}
	
	@Test
	public void testToggleBit6(){
		byte bite = Byte.parseByte("01111111", 2);
		bite = ByteUtil.toggleBit(bite, 6);
		assertEquals(63, bite);
	}
	
	@Test
	public void testToggleBit7(){
		byte bite = Byte.parseByte("01111111", 2);
		bite = ByteUtil.toggleBit(bite, 7);
		assertEquals(-1, bite);
	}
	
	@Test
	public void testIsBitSet0(){
		byte bite = Byte.parseByte("00000001", 2);
		assertTrue(ByteUtil.checkBit(bite, 0));
	}
	
	@Test
	public void testIsBitSet1(){
		byte bite = Byte.parseByte("1111101", 2);
		assertFalse(ByteUtil.checkBit(bite, 1));
	}
	
	@Test
	public void testIsBitSet2(){
		byte bite = Byte.parseByte("00000100", 2);
		assertTrue(ByteUtil.checkBit(bite, 2));
	}
	
	@Test
	public void testIsBitSet3(){
		byte bite = Byte.parseByte("1110111", 2);
		assertFalse(ByteUtil.checkBit(bite, 3));
	}
	
	@Test
	public void testIsBitSet4(){
		byte bite = Byte.parseByte("00010000", 2);
		assertTrue(ByteUtil.checkBit(bite, 4));
	}
	
	@Test
	public void testIsBitSet5(){
		byte bite = Byte.parseByte("1011111", 2);
		assertFalse(ByteUtil.checkBit(bite, 5));
	}
	
	@Test
	public void testIsBitSet6(){
		byte bite = Byte.parseByte("01000000", 2);
		assertTrue(ByteUtil.checkBit(bite, 6));
	}
	
	@Test
	public void testIsBitSet7(){
		byte bite = Byte.parseByte("01111111", 2);
		assertFalse(ByteUtil.checkBit(bite, 7));
	}
	
	@Test
	public void testToSignedInt(){
		assertEquals(-1, ByteUtil.toSignedInt(ByteUtil.parseByte("11111111")));
	}
	
	@Test
	public void testToUnsignedInt(){
		assertEquals(255, ByteUtil.toUnsignedInt(ByteUtil.parseByte("11111111")));
	}
	
	@Test
	public void testToHex15(){
		byte bite = Byte.parseByte("00001111", 2);
		assertEquals("0F", ByteUtil.toHex(bite));
	}
	
	@Test
	public void testToHex16(){
		byte bite = Byte.parseByte("00010000", 2);
		assertEquals("10", ByteUtil.toHex(bite));
	}
	
	@Test
	public void testToHex35(){
		byte bite = Byte.parseByte("00100011", 2);
		assertEquals("23", ByteUtil.toHex(bite));
	}
	
	@Test
	public void testToHexArray(){
		byte[] bytes = {Byte.parseByte("00010011", 2), Byte.parseByte("01111111", 2)};
		assertEquals("137F", ByteUtil.toHex(bytes));
	}
	
	@Test
	public void testHexToInt0(){
		assertEquals(0, ByteUtil.hexToInt('0'));
	}
	
	@Test
	public void testHexToInt1(){
		assertEquals(1, ByteUtil.hexToInt('1'));
	}
	
	@Test
	public void testHexToInt2(){
		assertEquals(2, ByteUtil.hexToInt('2'));
	}
	
	@Test
	public void testHexToInt3(){
		assertEquals(3, ByteUtil.hexToInt('3'));
	}
	
	@Test
	public void testHexToInt4(){
		assertEquals(4, ByteUtil.hexToInt('4'));
	}
	
	@Test
	public void testHexToInt5(){
		assertEquals(5, ByteUtil.hexToInt('5'));
	}
	
	@Test
	public void testHexToInt6(){
		assertEquals(6, ByteUtil.hexToInt('6'));
	}
	
	@Test
	public void testHexToInt7(){
		assertEquals(7, ByteUtil.hexToInt('7'));
	}
	
	@Test
	public void testHexToInt8(){
		assertEquals(8, ByteUtil.hexToInt('8'));
	}
	
	@Test
	public void testHexToInt9(){
		assertEquals(9, ByteUtil.hexToInt('9'));
	}
	
	@Test
	public void testHexToIntA(){
		assertEquals(10, ByteUtil.hexToInt('A'));
	}
	
	@Test
	public void testHexToIntB(){
		assertEquals(11, ByteUtil.hexToInt('B'));
	}
	
	@Test
	public void testHexToIntC(){
		assertEquals(12, ByteUtil.hexToInt('C'));
	}
	
	@Test
	public void testHexToIntD(){
		assertEquals(13, ByteUtil.hexToInt('D'));
	}
	
	@Test
	public void testHexToIntE(){
		assertEquals(14, ByteUtil.hexToInt('E'));
	}
	
	@Test
	public void testHexToIntF(){
		assertEquals(15, ByteUtil.hexToInt('F'));
	}
	
	@Test
	public void testHexToIntALow(){
		assertEquals(10, ByteUtil.hexToInt('a'));
	}
	
	@Test
	public void testHexToIntBLow(){
		assertEquals(11, ByteUtil.hexToInt('b'));
	}
	
	@Test
	public void testHexToIntCLow(){
		assertEquals(12, ByteUtil.hexToInt('c'));
	}
	
	@Test
	public void testHexToIntDLow(){
		assertEquals(13, ByteUtil.hexToInt('d'));
	}
	
	@Test
	public void testHexToIntELow(){
		assertEquals(14, ByteUtil.hexToInt('e'));
	}
	
	@Test
	public void testHexToIntFLow(){
		assertEquals(15, ByteUtil.hexToInt('f'));
	}
	
	@Test
	public void testHexToIntBad(){
		assertEquals(-1, ByteUtil.hexToInt('T'));
	}
	
	@Test
	public void testFromHexNotEven(){
		try{
			ByteUtil.fromHex("D");
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("hex string must be an even length: D", e.getMessage());
		}
	}
	
	@Test
	public void testFromHexIllegalCharacter(){
		try{
			ByteUtil.fromHex("0T");
			fail();
		}catch(IllegalArgumentException e){
			assertEquals("hex string contains an illegal hex character: 0T", e.getMessage());
		}
	}
	
	@Test
	public void testFromHexPass(){
		byte[] bites = ByteUtil.fromHex("FF05");
		assertEquals(2, bites.length);
		assertEquals("11111111", ByteUtil.toBinaryString(bites[0]));
		assertEquals("00000101", ByteUtil.toBinaryString(bites[1]));
	}
}
