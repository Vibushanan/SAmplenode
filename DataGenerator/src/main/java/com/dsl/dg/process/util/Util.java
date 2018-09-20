package com.dsl.dg.process.util;

public class Util {
	
	
	
	public static int getExecutorsCount(int records_to_generate) {
		
		
		return (int) Math.ceil(records_to_generate/100);
		
	}

}
