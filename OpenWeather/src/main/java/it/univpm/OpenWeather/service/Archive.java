package it.univpm.OpenWeather.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import it.univpm.OpenWeather.exception.InvalidBodyException;
import it.univpm.OpenWeather.model.City;
import it.univpm.OpenWeather.model.Orari;
import it.univpm.OpenWeather.model.RequestBodyClass;

@Service
public class Archive {


	public Vector<Orari> setArchivie(RequestBodyClass body) throws InvalidBodyException, ParseException{
		if(body.getStart()<0 || body.getEnd()<0) {
			String out = "Periodo negativo...";
			throw new InvalidBodyException(out); 
		}
		if(body.getEnd()<body.getStart()) {
			String out = "Periodo non ammesso...";
			throw new InvalidBodyException(out);
		}
		JSONObject obj = new JSONObject();
		Vector<Orari> orari = new Vector<Orari>();
		try {
			String next;
			BufferedReader fileR = new BufferedReader(new FileReader("doc/"+"Storico.txt"));
			do {
				next = fileR.readLine();
				if(next!=null) {
					obj = (JSONObject) JSONValue.parseWithException(next);
					if(body.getName().equalsIgnoreCase((String) obj.get("name"))) {
						City c = new City();
						c.setName(body.getName());
						c.setSunrise((String)obj.get("sunrise"));
						c.setSunset((String)obj.get("sunset"));
						orari.add(c);
					}
				} else if(orari.isEmpty()) {
					fileR.close();
					String out = "Città non presente nell'archivio...";
					throw new InvalidBodyException(out); 
				}
			}while(next!=null);
			fileR.close();
		}catch(IOException e) {
			String out = "File non trovato";
			throw new InvalidBodyException(out); 
		}
		if(body.getEnd()>orari.size()-1) {
			String out = "Periodo troppo lungo";
			throw new InvalidBodyException(out); 
		}
		return orari;
	}
	
}
