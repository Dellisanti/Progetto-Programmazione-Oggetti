package it.univpm.OpenWeather.filters;

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
import it.univpm.OpenWeather.model.Orari;

/**
 * Classe che implementa il filtraggio dei dati dello storico in base al periodo richiesto dall'utente.
 * @author De Ritis Riccardo
 * @author francesco Dellisanti
 */

public class StatsAndFilters {
	
	public Vector<City> ShowFilters(Vector<City> orari, City city, String paese, int periodo) throws ParseException {
		JSONObject obj = new JSONObject();
		orari = new Vector<City>();
		try {
			BufferedReader fileR = new BufferedReader(new FileReader("Ancona.txt"));
			for(int i=0;i<periodo;i++) {
				String next = fileR.readLine();
				if(next!=null) {
					obj = (JSONObject)JSONValue.parseWithException(next);
					JSONObject SRiseSSet = (JSONObject) obj.get("data");
					city.setName((String)SRiseSSet.get("name"));
					city.setSunrise((long)SRiseSSet.get("sunrise"));
					city.setSunset((long)SRiseSSet.get("sunset"));
					orari.add(city);
				}
				else
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Periodo troppo lungo...");
			}
		}catch(IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"File non trovato");
		}
		return orari;
	}
	
	public City ShowStats(City stat, int periodo) throws ParseException {
		JSONObject obj = new JSONObject();
		long sunrise = 0, sunrise1 = 0, sunset = 0, sunset1 = 0;
		try {
			BufferedReader fileR = new BufferedReader(new FileReader("Ancona.txt"));
			for(int i=0;i<periodo;i++) {
				String next = fileR.readLine();
				if(next!=null) {
					obj = (JSONObject)JSONValue.parseWithException(next);
					JSONObject SRiseSSet = (JSONObject) obj.get("data");
					if(i==0) {
						String name = (String)SRiseSSet.get("name");
						sunrise = (long)SRiseSSet.get("sunrise");
						sunset  = (long)SRiseSSet.get("sunset");
					}
					if(i==periodo-1) {
						sunrise1 = (long)SRiseSSet.get("sunrise");
						sunset1  = (long)SRiseSSet.get("sunset");
					}
				}
				else
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Periodo troppo lungo...");
			}
			stat.setName("Differenze in secondi di "+periodo+" giorni");
			stat.setSunrise((sunrise1-sunrise)/1000);
			stat.setSunset((sunset1-sunset)/1000);
		}catch(IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"File non trovato");
		}
		return stat;
	}
	
}