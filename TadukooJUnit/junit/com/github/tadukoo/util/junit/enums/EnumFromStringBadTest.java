package com.github.tadukoo.util.junit.enums;

import com.github.tadukoo.util.StringUtil;

public class EnumFromStringBadTest extends EnumFromValueBadTest<String, EnumFromStringBadTest.TestEnum>{
	
	protected enum TestEnum{
		YEP("Yep");
		
		private final String str;
		
		TestEnum(String str){
			this.str = str;
		}
		
		public static TestEnum fromString(String str){
			for(TestEnum e: values()){
				if(StringUtil.equalsIgnoreCase(e.str, str)){
					return e;
				}
			}
			return null;
		}
		
		public static TestEnum badFromString(String str){
			for(TestEnum e: values()){
				if(!StringUtil.equalsIgnoreCase(e.str, str)){
					return e;
				}
			}
			return null;
		}
	}
	
	public EnumFromStringBadTest(){
		super(EnumTest::assertFromStringBad, EnumTest::assertFromStringBad, EnumTest::assertFromStringBad,
				TestEnum::fromString, TestEnum::badFromString, TestEnum.YEP, DEFAULT_WRONG_STRING);
	}
}
