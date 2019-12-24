package com.gmail.realtadukoo.util;

public class AutoCloseableUtil {
	
	public static final void closeQuietly(AutoCloseable autoCloseable){
		if(autoCloseable != null){
			try{
				autoCloseable.close();
			}catch(Exception e){
				
			}
		}
	}
}
