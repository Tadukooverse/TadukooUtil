package com.github.tadukoo.util.junit.enums;

public class EnumFromIntBadTest extends EnumFromValueBadTest<Integer, EnumFromIntBadTest.TestEnum>{
	
	protected enum TestEnum{
		YEP(6);
		
		private final int i;
		
		TestEnum(int i){
			this.i = i;
		}
		
		public static TestEnum fromInt(int i){
			for(TestEnum e: values()){
				if(e.i == i){
					return e;
				}
			}
			return null;
		}
		
		public static TestEnum badFromInt(int i){
			for(TestEnum e: values()){
				if(e.i != i){
					return e;
				}
			}
			return null;
		}
	}
	
	public EnumFromIntBadTest(){
		super(EnumTest::assertFromIntBad, EnumTest::assertFromIntBad, EnumTest::assertFromIntBad,
				TestEnum::fromInt, TestEnum::badFromInt, TestEnum.YEP, DEFAULT_WRONG_INT);
	}
}
