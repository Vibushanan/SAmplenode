package com.dsl.dg.process;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dsl.dg.DataGeneration.DataCategorization;
import com.dsl.dg.workers.PersonalDataGenerator;

public class DataGeneration_Coordinator {

	static JSONObject inputObj = new JSONObject();
	static int row_count = 0;

	public DataGeneration_Coordinator(JSONObject inputJSON, int row_count1) {

		inputObj = inputJSON;
		row_count = row_count1;

	}

	public static JSONArray coordinator() {

		JSONArray data = inputObj.getJSONArray("Data");

		Map<String, JSONArray> filterData = DataCategorization.columnfilter(data);

		PersonalDataGenerator pd = new PersonalDataGenerator(filterData);
		// pd.getpersonaldata(rowcount);

		// getpersonaldata();

		// JSONArray personalData = pd.call();

		// basics, bank details, demo

		return pd.getpersonaldata(row_count);

	}

}
