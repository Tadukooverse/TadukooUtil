package com.github.tadukoo.util.junit.enums;

import com.github.tadukoo.util.StringUtil;

public class EnumToFromValueRegularTest extends EnumToFromValueSimpleTest<String, EnumToFromValueRegularTest.TestEnum>{
	
	protected enum TestEnum{
		YEP("Yep"), NOPE("Nope");
		
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
				if(StringUtil.equalsIgnoreCase(e.str, str)){
					if(e == YEP){
						return NOPE;
					}else{
						return YEP;
					}
				}
			}
			return YEP;
		}
		
		@Override
		public String toString(){
			return str;
		}
		
		public String customToString(){
			return str;
		}
	}
	
	public EnumToFromValueRegularTest(){
		super(EnumTest::assertToFromValue, EnumTest::assertToFromValue,
				TestEnum::fromString, TestEnum::badFromString,
				TestEnum.YEP::customToString, TestEnum.YEP, "Yep",
				TestEnum.NOPE::customToString, TestEnum.NOPE, "Nope");
	}
}
