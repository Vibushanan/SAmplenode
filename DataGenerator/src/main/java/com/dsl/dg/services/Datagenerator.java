package com.dsl.dg.services;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;




public class Datagenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Datagenerator.class);

    public Datagenerator() {
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		logger.info("Data Generation Request : " + inputJSON);
		
		
		
		
	}

}
