package com.bachcoder.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
/*
 * 1. need a method let convert json to string
 * 2. need a method to do mapper model with string cut by method 1
 */
public class HttpUtil {
	private String value;
	
	
	public HttpUtil(String value) {
		this.value = value;
	}
	
	//method mapper data with models
	public <T> T toMapper(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(value, tClass);//auto map
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//convert json to String
	public static HttpUtil of(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			String line;
		    while ((line = reader.readLine()) != null) {
		        sb.append(line);
		    }
		} catch (IOException e) {
			System.out.print(e.getMessage());
		}
		return new HttpUtil(sb.toString());
	}
}
