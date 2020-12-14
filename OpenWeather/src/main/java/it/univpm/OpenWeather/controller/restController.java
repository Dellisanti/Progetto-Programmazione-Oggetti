package it.univpm.OpenWeather.controller;

import java.util.Vector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import it.univpm.OpenWeather.filters.StatsAndFilters;
import it.univpm.OpenWeather.model.City;
import it.univpm.OpenWeather.model.Orari;
import it.univpm.OpenWeather.service.jsonParse;

/**
 * Rappresenta la classe che gestisce tutte le chiamate al server
 * @author De Ritis Riccardo
 * @author francesco Dellisanti
 */

@RestController
public class restController {
	
	/**
	 * Stringa statica che contiene l'ApiKey per la chiamate della API di OpenWeather.
	 * 
	 */
	
	final String ApiKey="7d93f19f21077353e39f87032051beae";
	jsonParse parse = new jsonParse();
	City city = new City();
	StatsAndFilters SF = new StatsAndFilters();
	Vector<Orari> orariArray;
	
	/**
	 * Rotta di tipo GET che ricava i dati di orario per alba e tramonto 
	 * di un certo paese passato dall'utente.
	 * @param paese Tipo parametro che dichiara di quale paese si intende conoscere i dati.
	 * @return Ritornano i dati di alba e tramonto del paese scelto dall'utente.
	 */
	
	@GetMapping(value="/città/{paese}")
	public ResponseEntity<Object> VediCittà(@PathVariable("paese") String paese) {
		String url = "https://api.openweathermap.org/data/2.5/weather?q="+paese+"&units=metric&appid="+ApiKey;
		String data = parse.ApiData(url);
	    City c = parse.Parse(data, city, paese);
		parse.Save(c);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	
	/**
	 * Rotta di tipo GET che effettua il filtraggio dei dati in base al periodo scelto dall'utente.
	 * @param paese Tipo parametro che dichiara di quale paese si intende conoscere i dati.
	 * @param periodo Tipo parametro che dichiara di quale periodo si intende filtrare lo storico.
	 * @return Ritornano i dati filtrati dallo storico per il periodo indicato
	 */
	
	@GetMapping(value="/città/filters/{paese}/{periodo}")
	public ResponseEntity<Object> VediStorico(@PathVariable("paese") String paese, @PathVariable("periodo") int periodo){
		return new ResponseEntity<>(SF.ShowFilters(orariArray, city, paese, periodo),HttpStatus.OK);
	}
	
	/**
	 * Rotta di tipo GET che effettua le statistiche dei dati in base al periodo scelto dall'utente.
	 * @param paese Tipo parametro che dichiara di quale paese si intende conoscere i dati.
	 * @param periodo Tipo parametro che dichiara di quale periodo si intende fare statistiche.
	 * @return Ritornano le statistiche in base al periodo richiesto.
	 */
	
	@GetMapping(value="/città/stats/{paese}/{periodo}")
	public ResponseEntity<Object> VediStats(@PathVariable("paese") String paese, @PathVariable("periodo") int periodo){
		return new ResponseEntity<>(SF.ShowStats(orariArray, city, paese, periodo),HttpStatus.OK);
	}
	
}
