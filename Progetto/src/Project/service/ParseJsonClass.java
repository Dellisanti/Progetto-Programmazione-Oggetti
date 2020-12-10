package Project.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
//import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import Project.model.City;

public class ParseJsonClass {
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		JSONObject obj = null;
		try {
			obj = (JSONObject)JSONValue.parseWithException(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject SRiseSSet= (JSONObject) obj.get("sys");
		JSONObject temp = (JSONObject) obj.get("main");
		//JSONArray Jarray = (JSONArray) obj.get("weather");
		city.setName(paese);
		long sunrise = (Long)SRiseSSet.get("sunrise");
		String SunRise = sdf.format(new java.util.Date(sunrise*1000));
		city.setSunrise(SunRise);
		long sunset = (Long)SRiseSSet.get("sunset");
		String SunSet = sdf.format(new java.util.Date(sunset*1000));
		city.setSunset(SunSet);
		
		String next;
		try {
			fileR = new BufferedReader(new FileReader("OrariAlbaTramonto.txt"));
			fileW = new BufferedWriter(new FileWriter("OrariAlbaTramonto.txt"));
			do {
				next=fileR.readLine();
				if(next!=null)
					fileW.write(SunRise+" , "+SunSet);
			}while(next!=null);
			fileR.close();
			fileW.close();
		}catch(IOException e) {
			System.out.println(e);
		}
		
		
		System.out.println("Oggi : ");
		System.out.println("La temperatura minima � : "+temp.get("temp_min")+"C�");
		System.out.println("La temperatura massima � : "+temp.get("temp_max")+"C�");
		return city;
	}
	
}
