package com.dsl.dg.DataGeneration;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class datacategorization {

	public static JSONObject columnfilter(JSONArray obj) {
		JSONObject main = new JSONObject();
		JSONArray obj_pern = new JSONArray();
		JSONArray obj_demo = new JSONArray();
		JSONArray obj_basic = new JSONArray();
		JSONArray obj_bank = new JSONArray();

		ArrayList<String> Personal = new ArrayList<String>();
		Personal.add("First Name");
		Personal.add("Last Name");
		Personal.add("Full Name");
		Personal.add("Age");
		Personal.add("Marital Status");
		Personal.add("Work Mail");
		Personal.add("Gender");
		Personal.add("Occupation");
		Personal.add("Company");
		Personal.add("ATM Zipcode");
		Personal.add("Monthly Income");
		Personal.add("Phone");

		ArrayList<String> Demographics = new ArrayList<String>();
		Demographics.add("Address");
		Demographics.add("Zipcode");
		Demographics.add("City");
		Demographics.add("County");
		Demographics.add("Latitude");
		Demographics.add("Longitude");
		Demographics.add("State");

		ArrayList<String> Basics = new ArrayList<String>();
		Basics.add("ID");
		Basics.add("Row Number");
		Basics.add("Boolean");
		Basics.add("Date");

		ArrayList<String> Bank_Details = new ArrayList<String>();
		Bank_Details.add("Bank ID");
		Bank_Details.add("Bank Name");
		Bank_Details.add("Ban SWIFT BIC (Bank Identifier Code)");

		for (int i = 0; i < obj.length(); i++) {

			JSONObject p_names = obj.getJSONObject(i);

			String tdm_col = p_names.getString("TDM Column Name");
			if (Personal.contains(tdm_col)) {

				obj_pern.put(p_names);
			}
			if (Demographics.contains(tdm_col)) {

				obj_demo.put(p_names);

			}
			if (Basics.contains(tdm_col)) {

				obj_basic.put(p_names);

			}
			if (Bank_Details.contains(tdm_col)) {

				obj_bank.put(p_names);
			}

			main.put("Personal", obj_pern);
			main.put("Demographics", obj_demo);
			main.put("Basics", obj_basic);
			main.put("Bank Details", obj_bank);
		}

		return main;
	}

}
