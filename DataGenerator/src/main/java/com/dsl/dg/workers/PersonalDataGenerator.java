package com.dsl.dg.workers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

import javax.servlet.ServletException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dsl.dg.DataGeneration.DataDictionary;
import com.dsl.dg.DataGeneration.RandomNumberGenerator;
import com.dsl.dg.DataGeneration.WeightedRandomPicker;

import com.github.javafaker.Faker;

public class PersonalDataGenerator implements Callable<JSONArray> {

	static Map<String, JSONArray> input = new HashMap<String, JSONArray>();

	public PersonalDataGenerator(Map<String, JSONArray> map) {

		input = map;

	}

	public JSONArray call() {

		return null;

	}

	public static JSONArray getpersonaldata(int rowcount) {

		Faker faker = new Faker();
		JSONArray finarr = new JSONArray();

		if (input.containsKey("Personal")) {
			JSONArray arr = input.get("Personal");

			for (int y = 0; y < rowcount; y++) {
				JSONObject finobj = new JSONObject();
				for (int i = 0; i < arr.length(); i++) {

					JSONObject data = arr.getJSONObject(i);
					if (data.getString("TDM Column Name").equals("Full_Name")) {

						finobj.put(data.getString("User Column Name"), faker.name().fullName());

					}

					if (data.getString("TDM Column Name").equals("First_Name")) {

						finobj.put(data.getString("User Column Name"), faker.name().firstName());

					}
					if (data.getString("TDM Column Name").equals("Last_Name")) {

						finobj.put(data.getString("User Column Name"), faker.name().lastName());

					}
					if (data.getString("TDM Column Name").equals("Age")) {

						finobj.put(data.getString("User Column Name"), RandomNumberGenerator.getRandomNumber(20, 60));

					}
					if (data.getString("TDM Column Name").equals("Marital_Status")) {
						WeightedRandomPicker<Object> rc = new WeightedRandomPicker<Object>().add(50, "Married").add(50,
								"single");
						finobj.put(data.getString("User Column Name"), rc.next());

					}

					if (data.getString("TDM Column Name").equals("Work_Mail")) {

						finobj.put(data.getString("User Column Name"), faker.internet().emailAddress());

					}
					if (data.getString("TDM Column Name").equals("Gender")) {

						finobj.put(data.getString("User Column Name"), faker.demographic().sex());

					}
					if (data.getString("TDM Column Name").equals("Occupation")) {

						finobj.put(data.getString("User Column Name"), DataDictionary.occupations
								.get(RandomNumberGenerator.getRandomNumber(0, DataDictionary.occupations.size() - 1)));

					}
					if (data.getString("TDM Column Name").equals("Company")) {

						finobj.put(data.getString("User Column Name"), faker.company().name());

					}
					if (data.getString("TDM Column Name").equals("ATM_Zipcode")) {
						String zipcode = faker.address().zipCode().substring(0, 5);
						finobj.put(data.getString("User Column Name"), zipcode);

					}
					if (data.getString("TDM Column Name").equals("Monthly_Income")) {

						finobj.put(data.getString("User Column Name"),
								RandomNumberGenerator.getRandomNumber(5000, 60000));

					}
					if (data.getString("TDM Column Name").equals("Phone")) {

						finobj.put(data.getString("User Column Name"),
								faker.phoneNumber().cellPhone().replace(".", "-"));

					}

				}
				finarr.put(finobj);
			}

		}
		System.out.println(" branch   " + finarr);

		return finarr;
	}
}
