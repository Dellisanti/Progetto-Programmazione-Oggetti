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
import java.util.Vector;

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
	
	public void readFile(Vector<Orari> orariArray, City city) {
		String next;
		int i=0;
		try {
			BufferedReader fileR = new BufferedReader(new FileReader("Ancona.txt"));
			do {
				next=fileR.readLine();
				if(next!=null) {
					String[] s = next.split(",");
					city = new City();
					if(orariArray.isEmpty()) {
						city.setSunrise(Long.valueOf(s[0]));
						city.setSunset(Long.valueOf(s[1]));
						orariArray.add(city);
					} else {
						if(orariArray.get(i).getSunrise()==Long.valueOf(s[0]) && orariArray.get(i).getSunset()==Long.valueOf(s[1])) {
							city.setSunrise(0);
							city.setSunset(0);
							orariArray.add(city);
						} else {
							city.setSunrise(Long.valueOf(s[0]));
							city.setSunset(Long.valueOf(s[1]));
							orariArray.add(city);
							i=orariArray.indexOf(city);
						}
					}
				}
			}while(next!=null);
			fileR.close();
		}catch(IOException e) {
			System.out.println("File non trovato");
			System.out.println(e);
		}
		for(i=0;i<orariArray.size();i++) {
			if(orariArray.get(i)!=null) {
				System.out.println("Alba: "+orariArray.get(i).getSunrise());
				System.out.println("Tramonto: "+orariArray.get(i).getSunset());
			} else {
				System.out.println("null");
			}
		}
	}
	
}
