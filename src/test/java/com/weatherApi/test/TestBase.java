package com.weatherApi.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.junit.runner.Request;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	 Properties prop=new Properties();
	public static RequestSpecification req;
	
	public TestBase() {
		
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public RequestSpecification  buildApiRequest() throws FileNotFoundException {
		PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
		req=new RequestSpecBuilder().setBaseUri(prop.getProperty("api.url")).setBasePath("api.path")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
		
		return req;
		
	}
	
	public  String getGlobalValue(String key) throws IOException
	{
		return prop.getProperty(key);
	}

}
