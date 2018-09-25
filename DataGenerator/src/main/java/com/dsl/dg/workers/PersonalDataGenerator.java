package com.dsl.dg.workers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.servlet.ServletException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dsl.dg.DataGeneration.DataDictionary;
import com.dsl.dg.DataGeneration.RandomNumberGenerator;
import com.dsl.dg.DataGeneration.WeightedRandomPicker;
import com.dsl.dg.models.InputConstants;
import com.dsl.dg.models.Personal;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;
import io.codearte.jfairy.producer.person.PersonProperties.PersonProperty;
import javafx.scene.shape.Arc;

public class PersonalDataGenerator implements Callable<JSONArray> {

	Map<String,JSONObject> input = new HashMap<String,JSONObject>();
	int rowcount;

	public PersonalDataGenerator(Map<String,JSONObject> map, int rowcount1) {

		input = map;
		rowcount = rowcount1;
	}
	
	
	public JSONArray call() {

		System.out.println("Generating Personal Columns...............");
		Fairy fairy = Fairy.create();

		Person name = null;
		JSONArray finarr = new JSONArray();
		
		boolean gender_property = false;
		
		/*Set Preference for Personal Data
		 * --------------------------------
		 * 1. get age Range
		 * 2. get male female ratio
		 * 
		 */
		WeightedRandomPicker genderPicker = new WeightedRandomPicker();
		
		WeightedRandomPicker mstatusPicker = new WeightedRandomPicker();
		
		PersonProperty[] p = new  PersonProperty[2];
		
		
		
		if(input.containsKey("Gender")) {
			
			if(input.get("Gender").has(InputConstants.additional_info)) {
				genderPicker.add(input.get("Gender").getJSONObject(InputConstants.additional_info).getInt(InputConstants.malepercent),"male" )
				.add(input.get("Gender").getJSONObject(InputConstants.additional_info).getInt(InputConstants.femalpercent),"female" );
				
				gender_property=true;
				
				
			}
			
		}
		
		if(input.containsKey("Age")) {
			
			if(input.get("Age").has(InputConstants.additional_info)) {
				
			p[0]=	PersonProperties.ageBetween(input.get("Age").getJSONObject(InputConstants.additional_info).getJSONObject("Range").getInt(InputConstants.min_range), 
						input.get("Age").getJSONObject(InputConstants.additional_info).getJSONObject("Range").getInt(InputConstants.max_range));
				
				
			}
			
		}
		
		
		if(input.containsKey("Marital_Status")) {
			
			mstatusPicker.add(50, "Married").add(50, "Single");
			
		}
		
	
		JSONArray personArray = new JSONArray();
		
		Set<String> allKeys = input.keySet();
		
		for(int i=0;i<rowcount;i++) {
		
		if(gender_property) {
			
			if(genderPicker.next().toString().equals("male")) {
				p[1]=PersonProperties.male();
				
			}else {
				
				p[1]=PersonProperties.female();
			}	
		}
		
		Person person = fairy.person(p);
		
		JSONObject personJSON = new JSONObject();
		
		
		
		if(allKeys.contains("First_Name")){
			
			personJSON.put(input.get("First_Name").getString("User Column Name"),person.getFirstName() );
			
		}
		if(allKeys.contains("Last_Name")) {
			personJSON.put(input.get("Last_Name").getString("User Column Name"),person.getLastName());
		}
		if(allKeys.contains("Full_Name")) {
			personJSON.put(input.get("Full_Name").getString("User Column Name"),person.getFullName() );
		}
		if(allKeys.contains("Age")) {
			personJSON.put(input.get("Age").getString("User Column Name"),person.getAge() );
		}
		
		if(allKeys.contains("Marital_Status")) {
			personJSON.put(input.get("Marital_Status").getString("User Column Name"),mstatusPicker.next().toString());
		}
		if(allKeys.contains("Work_Mail")) {
			personJSON.put(input.get("Work_Mail").getString("User Column Name"),person.getCompanyEmail());	
		}
		
		if(allKeys.contains("Gender")) {
			personJSON.put(input.get("Gender").getString("User Column Name"),person.getSex());
		}
		
		if(allKeys.contains("Occupation")) {
			
			
			personJSON.put(input.get("Occupation").getString("User Column Name"),DataDictionary.getRandomOccupation());
		}
		if(allKeys.contains("Company")){
			personJSON.put(input.get("Company").getString("User Column Name"),person.getCompany().getName());
		}  
		
		
		System.out.println("Generated JSON"+personJSON);
		
		
		
		personArray.put(personJSON);
		
		
		
	
		

		
		
		}
		
		
		
		
		return personArray;

	}

}
