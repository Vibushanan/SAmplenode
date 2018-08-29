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

public class datacategorization {

	public static Map<String, JSONArray> columnfilter(JSONArray obj) {
		JSONArray obj_pern = new JSONArray();
		JSONArray obj_demo = new JSONArray();
		JSONArray obj_basic = new JSONArray();
		JSONArray obj_bank = new JSONArray();

		Map<String, JSONArray> main = new HashMap<String, JSONArray>();

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

		for (int i = 0; i < obj.length(); i++) {

			JSONObject data = obj.getJSONObject(i);

			String tdm_col = data.getString("TDM Column Name");

			if (pesnl_arr.contains(tdm_col)) {

				obj_pern.put(data);
			}
			if (Demo_arr.contains(tdm_col)) {

				obj_demo.put(data);

			}
			if (Basi_arr.contains(tdm_col)) {

				obj_basic.put(data);

			}
			if (Bank_arr.contains(tdm_col)) {

				obj_bank.put(data);
			}
			main.put("Personal", obj_pern);
			main.put("Demographics", obj_demo);
			main.put("Basics", obj_basic);
			main.put("Bank Details", obj_bank);
		}
		System.out.println(main);
		return main;
	}

}
