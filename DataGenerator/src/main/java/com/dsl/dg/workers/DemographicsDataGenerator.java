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
		System.out.println("demo call");
		JSONObject finobj = new JSONObject();
		for (int i = 0; i < input.length(); i++) {
			
			
			
/*
			JSONObject data = input.getJSONObject(i);
			if (data.getString("TDM Column Name").equals("Address")) {
				finobj.put(data.getString("User Column Name"), name.getSex());

			}

			if (data.getString("TDM Column Name").equals("Zipcode")) {

				finobj.put(data.getString("User Column Name"), name.getFullName());

			}
			if (data.getString("TDM Column Name").equals("City")) {

				finobj.put(data.getString("User Column Name"), name.getFirstName());

			}
			if (data.getString("TDM Column Name").equals("County")) {

				finobj.put(data.getString("User Column Name"), name.getLastName());

			}
			if (data.getString("TDM Column Name").equals("Latitude")) {

				finobj.put(data.getString("User Column Name"), name.getCompanyEmail());

			}
			
			if (data.getString("TDM Column Name").equals("Longitude")) {

				finobj.put(data.getString("User Column Name"), wrpm.next());

			}
			
			if (data.getString("TDM Column Name").equals("State")) {

				finobj.put(data.getString("User Column Name"), name.getCompany().getName());

			}
			*/
		}	
		
		return null;
	}
	
	
	

}
