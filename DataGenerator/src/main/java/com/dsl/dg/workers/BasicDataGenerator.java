package com.dsl.dg.workers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dsl.dg.DataGeneration.Generators;

public class BasicDataGenerator implements Callable<JSONArray> {

	Map<String,JSONObject> input = new HashMap<String,JSONObject>();
	int rowcount;

	public BasicDataGenerator(Map<String,JSONObject> arr, int rowcount1) {

		input = arr;
		rowcount = rowcount1;
	}

	public JSONArray call() {
		return null;
	
	}
}
