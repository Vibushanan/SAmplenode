package com.dsl.dg.DataGeneration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dsl.dg.models.BankDetails;
import com.dsl.dg.models.Basics;
import com.dsl.dg.models.Demographics;
import com.dsl.dg.models.Personal;

public class DataCategorization {

	public static Map<String, Map<String, JSONObject>> columnfilter(JSONArray obj) {
		
		
		Map<String,Map<String,JSONObject>> categorized = new HashMap<String,Map<String,JSONObject>>();
		

		
		
		
		JSONArray obj_pern = new JSONArray();
		JSONArray obj_demo = new JSONArray();
		JSONArray obj_basic = new JSONArray();
		JSONArray obj_bank = new JSONArray();

		Map<String, JSONArray> main = new HashMap<String, JSONArray>();

		/*************************************************
		 * ADD ENUM Values
		 * ************************************************
		 */
		
		ArrayList<String> pesnl_arr = new ArrayList<String>();
		for (Personal s1 : Personal.values()) {
			pesnl_arr.add(s1.toString());

		}

		ArrayList<String> Demo_arr = new ArrayList<String>();
		for (Demographics s2 : Demographics.values()) {
			Demo_arr.add(s2.toString());

		}

		ArrayList<String> Basi_arr = new ArrayList<String>();
		for (Basics s3 : Basics.values()) {
			Basi_arr.add(s3.toString());

		}

		ArrayList<String> Bank_arr = new ArrayList<String>();
		for (BankDetails s4 : BankDetails.values()) {
			Bank_arr.add(s4.toString());

		}

		//************************
		
		for (int i = 0; i < obj.length(); i++) {

			JSONObject data = obj.getJSONObject(i);

			String tdm_col = data.getString("TDM Column Name");

			
			//Check for personal data
			if (pesnl_arr.contains(tdm_col)) {
				
				if(categorized.containsKey(com.dsl.dg.models.Constants.category_personal)) {
					
					categorized.get(com.dsl.dg.models.Constants.category_personal).put(tdm_col, data);
					
				}else {
					
					Map<String,JSONObject> pdata = new HashMap<String,JSONObject>();
					
					pdata.put(tdm_col, data);
					
					categorized.put(com.dsl.dg.models.Constants.category_personal, pdata);
				}
				
		
				obj_pern.put(data);
			}
			//Check for demographics data
			if (Demo_arr.contains(tdm_col)) {
				
				
				if(categorized.containsKey(com.dsl.dg.models.Constants.category_demographics)) {
					
					categorized.get(com.dsl.dg.models.Constants.category_demographics).put(tdm_col, data);
					
				}else {
					
					Map<String,JSONObject> pdata = new HashMap<String,JSONObject>();
					
					pdata.put(tdm_col, data);
					
					categorized.put(com.dsl.dg.models.Constants.category_demographics, pdata);
				}

				obj_demo.put(data);

			}
			//check for basics
			if (Basi_arr.contains(tdm_col)) {
				
				if(categorized.containsKey(com.dsl.dg.models.Constants.category_basics)) {
					
					categorized.get(com.dsl.dg.models.Constants.category_basics).put(tdm_col, data);
					
				}else {
					
					Map<String,JSONObject> pdata = new HashMap<String,JSONObject>();
					
					pdata.put(tdm_col, data);
					
					categorized.put(com.dsl.dg.models.Constants.category_basics, pdata);
				}


				obj_basic.put(data);

			}
			//check for banking fields
			if (Bank_arr.contains(tdm_col)) {
				
					if(categorized.containsKey(com.dsl.dg.models.Constants.category_bank)) {
					
					categorized.get(com.dsl.dg.models.Constants.category_bank).put(tdm_col, data);
					
				}else {
					
					Map<String,JSONObject> pdata = new HashMap<String,JSONObject>();
					
					pdata.put(tdm_col, data);
					
					categorized.put(com.dsl.dg.models.Constants.category_bank, pdata);
				}

				obj_bank.put(data);
			}
			
			System.out.println("New Category "+categorized);
			
			
			
		}
		
		return categorized;
	}

}
