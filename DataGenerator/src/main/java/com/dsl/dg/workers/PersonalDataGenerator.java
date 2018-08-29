package com.dsl.dg.workers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.json.JSONArray;
import org.json.JSONObject;

public class PersonalDataGenerator implements Callable<JSONArray>{

	Map<String,JSONObject> input = new HashMap<String,JSONObject>();
	
	
	PersonalDataGenerator(Map<String,JSONObject> data){
		
		input = data;
	}
	
	
	public JSONArray call() throws Exception {
	
		
		
		
		return null;
	}

	

}
