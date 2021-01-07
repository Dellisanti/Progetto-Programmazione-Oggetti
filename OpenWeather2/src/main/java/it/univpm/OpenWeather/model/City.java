package it.univpm.OpenWeather.model;

import org.springframework.stereotype.Service;

/**
 * Classe che modella i dati del Paese.
 * @author De Ritis Riccardo
 * @author francesco Dellisanti
 */

@Service
public class City implements Orari{
	private String name;
	private String sunrise;
	private String sunset;
	
	public City(String name, String sunrise, String sunset) {
		this.name = name;
		this.sunrise = sunrise;
		this.sunset = sunset;
	}
	
	public City() {
		this.name = null;
		this.sunrise = null;
		this.sunset = null;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSunrise() {
		return sunrise;
	}
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}
	public String getSunset() {
		return sunset;
	}
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}

}
