package com.dsl.dg.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import com.dsl.dg.DataGeneration.DataCategorization;
import com.dsl.dg.DataGeneration.DataDictionary;
import com.dsl.dg.process.DataGeneration_Coordinator;
import com.dsl.dg.workers.DemographicsDataGenerator;
import com.dsl.dg.workers.PersonalDataGenerator;

public class Datagenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Datagenerator.class);

	public Datagenerator() {

	}

	public void init(ServletConfig config) throws ServletException {

		logger.info("Initializing Data Obfuscator..");
		logger.info("Loading dictionaries...");

		ClassLoader classLoader = getClass().getClassLoader();
		DataDictionary.setOccupations(
				DataDictionary.load_dictionary(new File(classLoader.getResource("Occupations.csv").getFile())));

		System.out.println(  DataDictionary.occupations);
		
		logger.info("Dictionary Load completed");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader reader = request.getReader();
		String line = null;
		StringBuffer jb = new StringBuffer();

		while ((line = reader.readLine()) != null) {   
			jb.append(line);
		}

		JSONObject inputJSON = null;
		try {
			inputJSON = new JSONObject(jb.toString());
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		System.out.println("inputdata  " + inputJSON + "\n");

		JSONObject obj_job = inputJSON.getJSONObject("job information");

		String user_id = obj_job.getString("user_ID");
		String row_count = obj_job.get("number of rows to generate").toString();

		logger.info("Data Generation Request : " + inputJSON);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		int rowcount = Integer.parseInt(row_count);
		
		DataGeneration_Coordinator dg = new DataGeneration_Coordinator(inputJSON, rowcount);

		
		
		
		
		
		
		
		try {
			out.println(dg.coordinator());
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

}
