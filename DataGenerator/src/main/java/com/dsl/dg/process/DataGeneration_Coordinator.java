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
import com.dsl.dg.workers.BasicDataGenerator;
import com.dsl.dg.workers.DemographicsDataGenerator;
import com.dsl.dg.workers.PersonalDataGenerator;

public class DataGeneration_Coordinator {

	static JSONObject inputObj = new JSONObject();
	static int row_count = 0;

	public DataGeneration_Coordinator(JSONObject inputJSON, int row_count1) {

		inputObj = inputJSON;
		row_count = row_count1;

	}

	public static List<Future<JSONArray>> coordinator() throws InterruptedException {

		
		JSONArray data = inputObj.getJSONArray("Data");

		Map<String, JSONArray> filterData = DataCategorization.columnfilter(data);

		JSONArray arr_personal = filterData.get("Personal");
		JSONArray arr_demographics = filterData.get("Demographics");
		JSONArray arr_basics = filterData.get("Basics");
		
		/*//rough for working
		JSONArray json=new JSONArray();
		
		BasicDataGenerator bdg=new BasicDataGenerator(arr_basics,row_count);
		
		try {
			json=bdg.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//with callable
		ExecutorService executor = Executors.newFixedThreadPool(2);

		List<Future<JSONArray>> list = new ArrayList<Future<JSONArray>>();

		Callable<JSONArray> callable_p = new PersonalDataGenerator(arr_personal, row_count);
		Callable<JSONArray> callable_b = new BasicDataGenerator(arr_basics,row_count);
		
		
	//	Callable<JSONArray> callable1 = new DemographicsDataGeneration(arr_demographics, row_count);
		
		

		Future<JSONArray> datar = executor.submit(callable_p);
		
		Future<JSONArray> datar1 = executor.submit(callable_b);
		list.add(datar);
list.add(datar1);
		executor.shutdown();
		
		
		

		
		
		
		try {
			System.out.println("thread \t" + list.get(0).get());
			System.out.println("thread \t" + list.get(1).get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	//	return json;
	}

}
