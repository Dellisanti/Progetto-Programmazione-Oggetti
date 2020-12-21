package it.univpm.OpenWeather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univpm.OpenWeather.model.City;

@Service
public class OpenWeather {
	
	@Autowired
	Utils util;
	@Autowired
	Parse parse;
	@Autowired
	City city;
	
	public City getWeather(String paese) {
		String ApiKey = util.readApiKey();
		String url = "https://api.openweathermap.org/data/2.5/weather?q="+paese+"&appid="+ApiKey;
		String data = util.ApiData(url);
	    City c = parse.Parsing(data, city, paese);
		parse.Save(c);
		return c;
	}
	
}
