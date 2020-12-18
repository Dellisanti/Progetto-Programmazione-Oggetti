package it.univpm.OpenWeather.service;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import it.univpm.OpenWeather.model.City;

public class Parse {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public City Parsing(String data, City city, String paese) {
		JSONObject obj = null;
		try {
			obj = (JSONObject)JSONValue.parseWithException(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject SRiseSSet= (JSONObject) obj.get("sys");
		city.setName(paese);
		long sunrise = (Long)SRiseSSet.get("sunrise");
		String dateSunrise = sdf.format(new java.util.Date(sunrise*1000));
		city.setSunrise(dateSunrise);
		long sunset = (Long)SRiseSSet.get("sunset");
		String dateSunset = sdf.format(new java.util.Date(sunset*1000));
		city.setSunset(dateSunset);
		return city;
	}
	
	public void Save(City city) {
		try {
			JSONObject obj = new JSONObject();
			FileWriter fileW = new FileWriter("Ancona.txt",true);
			obj.put("name", city.getName());
			obj.put("sunrise", city.getSunrise());
			obj.put("sunset", city.getSunset());
			fileW.write(obj.toJSONString()+"\n");
			fileW.close(); 
		}catch(IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"File non trovato");
		}
	}
}
