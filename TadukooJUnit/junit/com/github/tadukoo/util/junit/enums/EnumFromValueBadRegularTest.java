package com.github.tadukoo.util.junit.enums;

import com.github.tadukoo.util.StringUtil;

import static com.github.tadukoo.util.junit.enums.EnumTest.assertFromValueBad;

public class EnumFromValueBadRegularTest extends EnumFromValueBadTest<String, EnumFromValueBadRegularTest.TestEnum>{
	
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
	
	public EnumFromValueBadRegularTest(){
		super(fromValFunc -> assertFromValueBad(fromValFunc, DEFAULT_WRONG_STRING), EnumTest::assertFromValueBad,
				EnumTest::assertFromValueBad, TestEnum::fromString, TestEnum::badFromString,
				TestEnum.YEP, DEFAULT_WRONG_STRING);
	}
}
