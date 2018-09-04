package com.dsl.dg.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

	public static JSONArray coordinator() throws InterruptedException {

		JSONArray data = inputObj.getJSONArray("Data");

		Map<String, JSONArray> filterData = DataCategorization.columnfilter(data);

		JSONArray arr = filterData.get("Personal");
		ExecutorService executor = Executors.newFixedThreadPool(1);

		List<Future<JSONArray>> list = new ArrayList<Future<JSONArray>>();

		Callable<JSONArray> callable = new PersonalDataGenerator(arr, row_count);

		Future<JSONArray> datar = executor.submit(callable);
		list.add(datar);

		executor.shutdown();
		try {
			System.out.println("thread \t" + list.get(0).get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
