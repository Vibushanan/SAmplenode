package com.dsl.dg.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Datagenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Datagenerator.class);

	public Datagenerator() {

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
		JSONObject obj_job = inputJSON.getJSONObject("job information");

		String user_id = obj_job.getString("user_ID");
		String row_count = obj_job.get("number of rows to generate").toString();

		JSONArray data = inputJSON.getJSONArray("Data");
		logger.info("Data Generation Request : " + inputJSON);

		response.setContentType("aapplication/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
	
		//out.println(datacategorization.columnfilter(data));
	}

}
