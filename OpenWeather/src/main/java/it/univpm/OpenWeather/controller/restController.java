package it.univpm.OpenWeather.controller;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import it.univpm.OpenWeather.filters.Filters;
import it.univpm.OpenWeather.model.RequestBodyClass;
import it.univpm.OpenWeather.service.OpenWeather;
import it.univpm.OpenWeather.statistics.Statistics;

/**
 * Rappresenta la classe che gestisce tutte le chiamate al server
 * @author De Ritis Riccardo
 * @author francesco Dellisanti
 */

@RestController
public class restController {
	
	@Autowired
	OpenWeather weather;
	@Autowired
	Filters filters;
	@Autowired
	Statistics stats;
	
	/**
	 * Rotta di tipo GET che ricava i dati di orario per alba e tramonto 
	 * di un certo paese passato dall'utente.
	 * @param paese Tipo parametro che dichiara di quale paese si intende conoscere i dati.
	 * @return Ritornano i dati di alba e tramonto del paese scelto dall'utente.
	 */
	
	@GetMapping(value="/weather/{paese}")
	public ResponseEntity<Object> ShowWeather(@PathVariable("paese") String paese) {
		return new ResponseEntity<>(weather.getWeather(paese), HttpStatus.OK);
	}
	
	/**
	 * Rotta di tipo GET che effettua il filtraggio dei dati in base al periodo scelto dall'utente.
	 * @param paese Tipo parametro che dichiara di quale paese si intende conoscere i dati.
	 * @param periodo Tipo parametro che dichiara di quale periodo si intende filtrare lo storico.
	 * @return Ritornano i dati filtrati dallo storico per il periodo indicato
	 * @throws ParseException 
	 */
	
	@PostMapping(value="/history")
	public ResponseEntity<Object> ShowHistory(@RequestBody RequestBodyClass body) throws ParseException{
		return new ResponseEntity<>(filters.ShowFilters(body),HttpStatus.OK);
	}
	
	@PostMapping(value="/historyAll")
	public ResponseEntity<Object> ShowAllHistory(@RequestBody RequestBodyClass body) throws ParseException{
		return new ResponseEntity<>(filters.ShowAllFilters(body),HttpStatus.OK);
	}
	
	/**
	 * Rotta di tipo GET che effettua le statistiche dei dati in base al periodo scelto dall'utente.
	 * @param paese Tipo parametro che dichiara di quale paese si intende conoscere i dati.
	 * @param periodo Tipo parametro che dichiara di quale periodo si intende fare statistiche.
	 * @return Ritornano le statistiche in base al periodo richiesto.
	 * @throws ParseException 
	 */
	
	@PostMapping(value="/stats")
	public ResponseEntity<Object> ShowStatistics(@RequestBody RequestBodyClass body) throws ParseException{
		return new ResponseEntity<>(stats.ShowStats(body),HttpStatus.OK);
	}
	
}
