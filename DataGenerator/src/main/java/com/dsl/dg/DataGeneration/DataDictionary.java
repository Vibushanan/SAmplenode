package com.dsl.dg.DataGeneration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataDictionary {

	public static List<String> occupations = new ArrayList<String>();
	public static int occupations_count = occupations.size();

	public static List<String> getOccupations() {
		return occupations;
	}

	public static void setOccupations(List<String> occupations) {
		DataDictionary.occupations = occupations;
	}

	/*
	 * Loads the given file into a dictionary
	 */
	public static List<String> load_dictionary(File dict) {

		List<String> dictionary = new ArrayList<String>();

		BufferedReader br = null;
		FileReader fr = null;
		try {

			fr = new FileReader(dict);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				dictionary.add(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

		return dictionary;
	}

	public static void main(String[] args) {

	}

}
