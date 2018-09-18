package com.dsl.dg.workers;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dsl.dg.DataGeneration.Generators;

public class BasicDataGenerator implements Callable<JSONArray> {

	JSONArray input = new JSONArray();
	int rowcount;

	public BasicDataGenerator(JSONArray arr, int rowcount1) {

		input = arr;
		rowcount = rowcount1;
	}

	public JSONArray call() {
		JSONArray finarr = new JSONArray();
		Generators gn = new Generators();
		Random rnd = new Random();
	//	System.out.println("basic data genertor in");

		ArrayList<Integer> sn_arr = gn.getserialnumber(rowcount);
		ArrayList<String> uni_range = null;
		ArrayList<String> randomdates = null;

		for (int i = 0; i < input.length(); i++) {
			JSONObject data = input.getJSONObject(i);
			if (data.getString("TDM Column Name").equals("ID")) {
				JSONObject objrange = data.getJSONObject("Additional Information").getJSONObject("Range");
				String uni = data.getJSONObject("Additional Information").getString("unique");
				uni_range = gn.getuniqueid(uni, rowcount, objrange);
			}
			if (data.getString("TDM Column Name").equals("Date")) {
				JSONObject range = data.getJSONObject("Additional Information").getJSONObject("Range");
				String format = data.getJSONObject("Additional Information").getString("format");
				randomdates = (gn.getrandomdates(format, range,rowcount));
			}
		}

		for (int y = 0; y < rowcount; y++) {
			JSONObject finobj = new JSONObject();
			for (int i = 0; i < input.length(); i++) {

				JSONObject data = input.getJSONObject(i);

				if (data.getString("TDM Column Name").equals("ID")) {

					finobj.put(data.getString("User Column Name"), uni_range.get(y));
				}

				if (data.getString("TDM Column Name").equals("Row_Number")) {

					finobj.put(data.getString("User Column Name"), sn_arr.get(y));
				}

				if (data.getString("TDM Column Name").equals("Boolean")) {
					finobj.put(data.getString("User Column Name"), rnd.nextBoolean());
				}

				if (data.getString("TDM Column Name").equals("Date")) {
					int ran_no = rnd.nextInt(rowcount);
					finobj.put(data.getString("User Column Name"), randomdates.get(ran_no));
				}
			}
			finarr.put(finobj);
			
		}
		
		return finarr;
	}
}
