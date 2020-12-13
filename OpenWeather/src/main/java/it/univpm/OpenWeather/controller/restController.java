package it.univpm.OpenWeather.controller;

import java.util.Vector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import it.univpm.OpenWeather.filters.StatsAndFilters;
import it.univpm.OpenWeather.model.City;
import it.univpm.OpenWeather.model.Orari;
import it.univpm.OpenWeather.service.jsonParse;

@RestController
public class restController {
	
	final String ApiKey="7d93f19f21077353e39f87032051beae";
	jsonParse parse = new jsonParse();
	City city = new City();
	StatsAndFilters SF = new StatsAndFilters();
	Vector<Orari> orariArray;
	
	@GetMapping(value="/città/{paese}")
	public ResponseEntity<Object> VediCittà(@PathVariable("paese") String paese) {
		String url = "https://api.openweathermap.org/data/2.5/weather?q="+paese+"&units=metric&appid="+ApiKey;
		String data = parse.ApiData(url);
	    City c = parse.Parse(data, city, paese);
		parse.Save(c);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	
	@GetMapping(value="/città/filters/{paese}/{periodo}")
	public ResponseEntity<Object> VediStorico(@PathVariable("paese") String paese, @PathVariable("periodo") int periodo){
		return new ResponseEntity<>(SF.ShowFilters(orariArray, city, paese, periodo),HttpStatus.OK);
	}
	
	@GetMapping(value="/città/stats/{paese}/{periodo}")
	public ResponseEntity<Object> VediStats(@PathVariable("paese") String paese, @PathVariable("periodo") int periodo){
		return new ResponseEntity<>(SF.ShowStats(orariArray, city, paese, periodo),HttpStatus.OK);
	}
	
}
