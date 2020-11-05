package com.example.Users.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.example.Users.entity.Users;

public class Util {

	public static List<Users> convertCsvtoList(InputStream is) throws ParseException{
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		        CSVParser csvParser = new CSVParser(fileReader,
		            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

		      List<Users> usersList = new ArrayList<Users>();

		      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

		      for (CSVRecord csvRecord : csvRecords) {
		    	    Users users = new Users();
		    	    users.setId(csvRecord.get("id"));
		    	    users.setLoginId(csvRecord.get("login"));
		    	    users.setEmpName(csvRecord.get("name"));
		    	    users.setSalary(Double.parseDouble("123.00"));
		    	    users.setSalary(Double.parseDouble(csvRecord.get("salary").trim()));
		    	    
		    	   //String dateString= csvRecord.get("startDate");
		    	 //  Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(dateString);  
		    	    users.setStartDate(extractDate(csvRecord.get("startDate")));
		    	    usersList.add(users);
		      }

		      return usersList;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		    }
	}
	
	static Date extractDate(String strDate){
	    final List<String> dateFormats = Arrays.asList("yyyy-MMM-dd", "dd-MM-yyyy");    

	    for(String format: dateFormats){
	        SimpleDateFormat sdf = new SimpleDateFormat(format);
	        try{
	            return sdf.parse(strDate);
	        } catch (ParseException e) {
	             //intentionally empty
	        }
	    }
	        throw new IllegalArgumentException("Invalid input for date. Given '"+strDate+"', expecting format yyyy-MM-dd HH:mm:ss.SSS or yyyy-MM-dd.");

	}
	
}
