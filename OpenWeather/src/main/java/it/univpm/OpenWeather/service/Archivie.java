package it.univpm.OpenWeather.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import it.univpm.OpenWeather.model.City;
import it.univpm.OpenWeather.model.RequestBodyClass;

public class Archivie {

	public Vector<City> setArchivie(RequestBodyClass body) throws ParseException {
		JSONObject obj = new JSONObject();
		Vector<City> orari = new Vector<City>();
		int i=0;
		try {
			String next;
			BufferedReader fileR = new BufferedReader(new FileReader("Ancona.txt"));
			do {
				next = fileR.readLine();
				if(next!=null) {
					obj = (JSONObject) JSONValue.parseWithException(next);
					if(body.getName().equals(obj.get("name"))) {
						City c = new City();
						c.setName(body.getName());
						c.setSunrise((String)obj.get("sunrise"));
						c.setSunset((String)obj.get("sunset"));
						orari.add(i,c); i++;
					}
				} else if(orari.isEmpty()) {
					fileR.close();
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Città non presente nell'archivio..");
				}
			}while(next!=null);
			fileR.close();
		}catch(IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"File non trovato..");
		}
		return orari;
	}
	
}
