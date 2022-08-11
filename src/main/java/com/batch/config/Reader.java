
package com.batch.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class Reader implements ItemReader<String> {


		/*private String[] messages = { "javainuse.com",
				"Welcome to Spring Batch Example",
				"We use H2 Database for this example" };*/

		private int count = 0;

		@Override
		public String read() throws Exception, UnexpectedInputException,
				ParseException, NonTransientResourceException {

		String file = "src/main/resources/10000SalesRecords.csv"; 
		List<String> list = new ArrayList<>();
		 // load the data from file
		list= Files.readAllLines(Paths.get(file));      
	   
	    // convert arraylist to array
	    String[] array = list.toArray(new String[0]);

	    if (count < array.length) {
			return array[count++];
		} else {
			count = 0;
		}
		return null;
	}

/*@Override public String read() throws Exception, UnexpectedInputException,
  ParseException, NonTransientResourceException {
  
  String file = "src/main/resources/demo2.csv"; // System.out.println(json);
  
  //return new String(Files.readString(Paths.get(file)));
  
  List<String> list = new ArrayList<>(); 
  try { BufferedReader br =
  Files.newBufferedReader(Paths.get(file)); 
  //br returns as stream and convert  it into a 
  
  List list = br.lines().collect(Collectors.toList());
  
  
  } catch (IOException e) { e.printStackTrace(); }
  
  return new String(Files.readString(Paths.get(file))); 
  }*/

}
