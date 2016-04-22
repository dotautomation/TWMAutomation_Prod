package com.totalwine.test.global;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.totalwine.test.config.ConfigurationFunctions;

public class RedirectURL {
	
	@Test
	public void RedirectURLTest () throws InterruptedException, IOException, BiffException {
		/*URLConnection con = new URL("http://bugfix.totalwine.com/eng/categories/wine/canada%7Cdessert-fortified-wine").openConnection();
		System.out.println( "orignal url: " + con.getURL() );
		con.connect();
		System.out.println( "connected url: " + con.getURL() );
		InputStream is = con.getInputStream();
		System.out.println( "redirected url: " + con.getURL() );
		is.close();*/
		BufferedWriter writer;
		Workbook inputWorkbook;
		String baseURL = "";
		String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())+".csv";
		File logFile=new File(timeLog);
		
		//Instantiate output file
		writer = new BufferedWriter(new FileWriter(logFile));
		writer.write("Base URL,Redirected URL,Response Code,Expected Result,P/F");
		writer.newLine();
		
		inputWorkbook = Workbook.getWorkbook(new File("URL1.xls"));
	    Sheet inputSheet = inputWorkbook.getSheet(0);
	    int rowCount = inputSheet.getRows();
		String redirectURL;
		String expRedirectURL;
	    for (int i=1;i<rowCount;i++) { //Consider title row
	    	redirectURL = inputSheet.getCell(0,i).getContents();
	    	expRedirectURL = inputSheet.getCell(1,i).getContents();
	    	HttpURLConnection con = (HttpURLConnection)(new URL(baseURL+redirectURL).openConnection());
			con.setInstanceFollowRedirects(false);
			con.connect();
			int responseCode = con.getResponseCode();
			String location = con.getHeaderField("Location");
			System.out.println(baseURL+redirectURL+"\t"+responseCode+"\t"+location);
			writer.write(baseURL+redirectURL+","+location+","+responseCode+","+baseURL+expRedirectURL+",");
			if (location.equals(baseURL+expRedirectURL))
				writer.write("P");
			else
				writer.write("F");
			writer.newLine();
	    }
		writer.close();
		/*
		HttpURLConnection con = (HttpURLConnection)(new URL("http://bugfix.totalwine.com/eng/categories/wine/canada%7Cdessert-fortified-wine").openConnection());
		con.setInstanceFollowRedirects( false );
		con.connect();
		int responseCode = con.getResponseCode();
		System.out.println( responseCode );
		String location = con.getHeaderField( "Location" );
		System.out.println( location );*/
	}
}
