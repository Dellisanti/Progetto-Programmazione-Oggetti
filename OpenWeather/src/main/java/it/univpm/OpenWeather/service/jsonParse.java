package it.univpm.OpenWeather.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import it.univpm.OpenWeather.model.*;

public class jsonParse {
	
	private BufferedWriter fileW;
	private BufferedReader fileR;
	
	public String ApiData (String url) {
		String data = "";
		String line = "";
		try {
			URLConnection openConnection = new URL(url).openConnection();
			InputStream in = openConnection.getInputStream();
			try {
				BufferedReader buf = new BufferedReader(new InputStreamReader(in));
				while((line=buf.readLine())!=null) {
					data+=line;
				}
			}finally {
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public City Parse(String data, City city, String paese) {
		
		JSONObject obj = null;
		try {
			obj = (JSONObject)JSONValue.parseWithException(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject SRiseSSet= (JSONObject) obj.get("sys");
		JSONObject temp = (JSONObject) obj.get("main");
		city.setName(paese);
		long sunrise = (Long)SRiseSSet.get("sunrise");
		city.setSunrise(sunrise);
		long sunset = (Long)SRiseSSet.get("sunset");
		city.setSunset(sunset);
		double tempMax = (double)temp.get("temp_max");

		double tempMin = (double)temp.get("temp_min");
		
		System.out.println("Oggi : ");
		System.out.println("La temperatura minima è : "+temp.get("temp_min")+"C°");
		System.out.println("La temperatura massima è : "+temp.get("temp_max")+"C°");
		return city;
	}
	
	public void Save(City city) {
		
		String next;
		try {
			fileR = new BufferedReader(new FileReader("Ancona.txt"));
			fileW = new BufferedWriter(new FileWriter("Ancona.txt", true));
			do {
				next=fileR.readLine();
				if(next==null)
					fileW.write(city.getSunrise()+","+city.getSunset()+"\n");
			}while(next!=null);
			fileR.close();
			fileW.close(); 
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	
}
