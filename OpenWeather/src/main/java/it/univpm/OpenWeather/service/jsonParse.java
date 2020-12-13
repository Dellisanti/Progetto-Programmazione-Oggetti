package it.univpm.OpenWeather.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import it.univpm.OpenWeather.model.*;

public class jsonParse {
	
	private BufferedWriter fileW;
	
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
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Città non trovata");
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
		city.setName(paese);
		long sunrise = (Long)SRiseSSet.get("sunrise");
		city.setSunrise(sunrise);
		long sunset = (Long)SRiseSSet.get("sunset");
		city.setSunset(sunset);
		return city;
	}
	
	public void Save(City city) {
		try {
			fileW = new BufferedWriter(new FileWriter("Ancona.txt", true));		
			fileW.write(city.getSunrise()+","+city.getSunset()+"\n");
			fileW.close(); 
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	
}
