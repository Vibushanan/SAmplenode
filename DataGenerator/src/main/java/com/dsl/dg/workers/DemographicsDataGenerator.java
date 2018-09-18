package com.dsl.dg.workers;

import java.util.concurrent.Callable;

import org.json.JSONArray;
import org.json.JSONObject;



public class DemographicsDataGenerator implements Callable<JSONArray>{

	JSONArray input = new JSONArray();
	int rowcount;

	
public DemographicsDataGenerator(JSONArray map, int rowcount1){
		input = map;
		rowcount = rowcount1;
		
	}



	public JSONArray call() throws Exception {
		
		return null;
	}
	
	
	

}
