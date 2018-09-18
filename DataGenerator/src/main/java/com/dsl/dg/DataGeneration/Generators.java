package com.dsl.dg.DataGeneration;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.joda.time.DateTime;
import org.json.JSONObject;

public class Generators {

	public static ArrayList<Integer> getserialnumber(int records) {
		ArrayList<Integer> number = new ArrayList<Integer>();
		int num = 1;
		for (int i = 0; i < records; i++) {

			number.add(num);
			num += 1;
		}
		return number;
	}

	// 9digit
	public static ArrayList<String> getuniqueid(String unique, int records, JSONObject range) {
		Random rnd = new Random();
		ArrayList<String> arr = new ArrayList<String>();
	
		int n = 0;
		if (unique.equals("true")) {
			while (records > arr.size()) {
				if (range.length() == 0) {
					n = 100000000 + rnd.nextInt(900000000);

					String kk = Integer.valueOf(n).toString();
					if (kk.length() == 9) {
						if (!arr.contains(kk)) {
							arr.add(kk);
						}
					}
				} else {
					int Min = range.getInt("Min");
					int Max = range.getInt("Max");
					n = Min + rnd.nextInt(Max);
					String kk = Integer.valueOf(n).toString();
					if (!arr.contains(kk)) {
						arr.add(kk);
					}
				}
			}
		} else {
			for (int i = 0; i < records; i++) {
				int Min = range.getInt("Min");
				int Max = range.getInt("Max");
				n = Min + rnd.nextInt(Max);
				String kk = Integer.valueOf(n).toString();
				arr.add(kk);
			}
		}
		return arr;
	}

	public static String getmonthnames(String val) {
		Map<String, String> map = new HashMap<String, String>(0);
		map.put("01", "Jan");
		map.put("02", "Feb");
		map.put("03", "Mar");
		map.put("04", "Apr");
		map.put("05", "May");
		map.put("06", "Jun");
		map.put("07", "Jul");
		map.put("08", "Aug");
		map.put("09", "Sep");
		map.put("10", "Oct");
		map.put("11", "Nov");
		map.put("12", "Dec");
		return map.get(val);
	}
	// for random data generaton
	public static ArrayList<String> getrandomdates(String format, JSONObject range,int rowcount) {
		ArrayList<String> arr = new ArrayList<String>();
 
		String Sdate = range.getString("Min");
		String Edate = range.getString("Max");
		DateTime start = DateTime.parse(Sdate);
		DateTime end = DateTime.parse(Edate);
		List<DateTime> between = getDateRange(start, end);
		for(int i=0;i<rowcount;i++){
		for (DateTime d : between) {
			String total = null;
			String date = d.toString().substring(0, 10);
			String year = date.substring(0, 4);
			String month = date.substring(5, 7);
			String day = date.substring(8, 10);
 
			// for d and m formats
			char day1 = 0;
			char month1 = 0;
			String dayt = null;
			String montht = null;

			char zero_day = day.charAt(0);
			char zero_mont = month.charAt(0);

			if (zero_day == '0') {
				day1 = day.charAt(1);
				dayt = Character.toString(day1);

			} else {
				dayt = day;
			}
			if (zero_mont == '0') {
				month1 = month.charAt(1);
				montht = Character.toString(month1);
			} else {
				montht = month;
			}
			if (format.equals("d/m/yyyy")) {
				total = dayt + "/" + montht + "/" + year;
			}
			if (format.equals("m/d/yyyy")) {
				total = montht + "/" + dayt + "/" + year;
			}
			if (format.equals("d.m.yyyy")) {
				total = dayt + "." + montht + "." + year;
			}
			if (format.equals("dd-Mon-yyyy")) {
				total = day + "-" + getmonthnames(month) + "-" + year;
			}
			if (format.equals("dd/mm/yyyy")) {
				total = day + "/" + month + "/" + year;
			}
			if (format.equals("dd.mm.yyyy")) {
				total = day + "." + month + "." + year;
			}
			if (format.equals("yyyy/mm/dd")) {
				total = year + "/" + month + "/" + day;
			}
			if (format.equals("yyyy-mm-dd")) {
				total = year + "-" + month + "-" + day;
			}
			if (format.equals("mm/dd/yyyy")) {
				total = month + "/" + day + "/" + year;
			}
			
			arr.add(total);
		}
	}
		return arr;
	}

	public static List<DateTime> getDateRange(DateTime start, DateTime end) {
		List<DateTime> dates = new ArrayList<DateTime>();
		DateTime tmp = start;
		while (tmp.isBefore(end) || tmp.equals(end)) {
			tmp = tmp.plusDays(1);
			dates.add(tmp);
		}
		dates = dates.subList(0, dates.size() - 1); // to get only in between the
												// range of dates
		return dates;
	}
}
