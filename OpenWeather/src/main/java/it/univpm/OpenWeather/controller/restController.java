package it.univpm.OpenWeather.controller;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import it.univpm.OpenWeather.model.City;
import it.univpm.OpenWeather.model.Orari;
import it.univpm.OpenWeather.service.jsonParse;

@RestController
public class restController {
	
	final String ApiKey="7d93f19f21077353e39f87032051beae";
	jsonParse parse = new jsonParse();
	City city = new City();
	
	@GetMapping(value="/città/{paese}")
	public City VediCittà(@PathVariable("paese") String paese) {
		String url = "https://api.openweathermap.org/data/2.5/weather?q="+paese+"&units=metric&appid="+ApiKey;
		String data = parse.ApiData(url);
	    City c = parse.Parse(data, city, paese);
	    city.CityViewToday();
		parse.Save(c);
		
		return c;
	}
	
	@GetMapping(value="/citta/{paese}/{periodo}")
	public Vector<Orari> VediStorico(@PathVariable("paese") String paese, @PathVariable("periodo") int periodo){
		
		return null;
	}
}
