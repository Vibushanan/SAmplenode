package com.dsl.dg.workers;

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

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;
import javafx.scene.shape.Arc;

public class PersonalDataGenerator implements Callable<JSONArray> {

	JSONArray input = new JSONArray();
	int rowcount;

	public PersonalDataGenerator(JSONArray map, int rowcount1) {

		input = map;
		rowcount = rowcount1;
	}

	public JSONArray call() {

		System.out.println("in call");
		Fairy fairy = Fairy.create();

		Person name = null;
		JSONArray finarr = new JSONArray();

		ArrayList<String> al = new ArrayList<String>();

		WeightedRandomPicker wrp = new WeightedRandomPicker();
		
		WeightedRandomPicker wrpm = new WeightedRandomPicker();
		wrpm.add(50, "Married").add(50, "single");
		for (int y = 0; y < rowcount; y++) {

			for (int i = 0; i < input.length(); i++) {

				JSONObject data = input.getJSONObject(i);

				if (data.getString("TDM Column Name").equals("Gender")) {

					JSONObject obj_data = data.getJSONObject("Additional Information").getJSONObject("Range");
					int male = (Integer) obj_data.get("male%");
					int female = (Integer) obj_data.get("female%");

					wrp.add(male, "Male").add(female, "Female");

					al.add(wrp.next().toString());
				}

			}

		}

		for (int y = 0; y < al.size(); y++) {

			if (al.get(y).equals("Male")) {
				name = fairy.person(PersonProperties.male());

			} else {
				name = fairy.person(PersonProperties.female());
			}
			JSONObject finobj = new JSONObject();

			for (int i = 0; i < input.length(); i++) {

				JSONObject data = input.getJSONObject(i);
				if (data.getString("TDM Column Name").equals("Gender")) {
					finobj.put(data.getString("User Column Name"), name.getSex());

				}

				if (data.getString("TDM Column Name").equals("Full_Name")) {

					finobj.put(data.getString("User Column Name"), name.getFullName());

				}
				if (data.getString("TDM Column Name").equals("First_Name")) {

					finobj.put(data.getString("User Column Name"), name.getFirstName());

				}
				if (data.getString("TDM Column Name").equals("Last_Name")) {

					finobj.put(data.getString("User Column Name"), name.getLastName());

				}
				if (data.getString("TDM Column Name").equals("Work_Mail")) {

					finobj.put(data.getString("User Column Name"), name.getCompanyEmail());

				}
				if (data.getString("TDM Column Name").equals("Age")) {
					if (data.getJSONObject("Additional Information").length() != 0) {
						JSONObject obj_data = data.getJSONObject("Additional Information").getJSONObject("Range");
						int min = (Integer) obj_data.get("Min");
						int max = (Integer) obj_data.get("Max");
						finobj.put(data.getString("User Column Name"), RandomNumberGenerator.getRandomNumber(min, max));
					} else {

						finobj.put(data.getString("User Column Name"), RandomNumberGenerator.getRandomNumber(1, 99));
					}

				}

				if (data.getString("TDM Column Name").equals("Marital_Status")) {

					finobj.put(data.getString("User Column Name"), wrpm.next());

				}
				if (data.getString("TDM Column Name").equals("Occupation")) {

					finobj.put(data.getString("User Column Name"), DataDictionary.occupations
							.get(RandomNumberGenerator.getRandomNumber(0, DataDictionary.occupations.size() - 1)));

				}
				if (data.getString("TDM Column Name").equals("Company")) {

					finobj.put(data.getString("User Column Name"), name.getCompany().getName());

				}
				if (data.getString("TDM Column Name").equals("ATM_Zipcode")) {
					String zipcode = name.getAddress().getPostalCode();
					finobj.put(data.getString("User Column Name"), zipcode);

				}
				if (data.getString("TDM Column Name").equals("Monthly_Income")) {
					if (data.getJSONObject("Additional Information").length() != 0) {
						JSONObject obj_data = data.getJSONObject("Additional Information").getJSONObject("Range");
						int min = (Integer) obj_data.get("Min");
						int max = (Integer) obj_data.get("Max");
						finobj.put(data.getString("User Column Name"), RandomNumberGenerator.getRandomNumber(min, max));
					} else {
						finobj.put(data.getString("User Column Name"),
								RandomNumberGenerator.getRandomNumber(3000, 9000));
					}

				}
				if (data.getString("TDM Column Name").equals("Phone")) {

					finobj.put(data.getString("User Column Name"), name.getTelephoneNumber());

				}

			}
			finarr.put(finobj);

		}

		System.out.println("personal generated data   " + finarr);

		return finarr;

	}

}
