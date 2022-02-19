package com.github.tadukoo.util.junit.enums;

public class EnumToFromIntTest extends EnumToFromValueSimpleTest<Integer, EnumToFromIntTest.TestEnum>{
	
	protected enum TestEnum{
		YEP(42), NOPE(27);
		
		private final int val;
		
		TestEnum(int val){
			this.val = val;
		}
		
		public static TestEnum fromInt(int val){
			for(TestEnum e: values()){
				if(e.val == val){
					return e;
				}
			}
			return null;
		}
		
		public static TestEnum badFromInt(int val){
			for(TestEnum e: values()){
				if(e.val == val){
					if(e == YEP){
						return NOPE;
					}else{
						return YEP;
					}
				}
			}
			return YEP;
		}
		
		public int toInt(){
			return val;
		}
	}
	
	public EnumToFromIntTest(){
		super(EnumTest::assertToFromInt, EnumTest::assertToFromInt,
				TestEnum::fromInt, TestEnum::badFromInt,
				TestEnum.YEP::toInt, TestEnum.YEP, 42,
				TestEnum.NOPE::toInt, TestEnum.NOPE, 27);
	}
}
