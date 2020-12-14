package it.univpm.OpenWeather.model;

/**
 * Classe che modella i dati del Paese.
 * @author De Ritis Riccardo
 * @author francesco Dellisanti
 */

public class City implements Orari{
	private String name;
	private long sunrise;
	private long sunset;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public long getSunrise() {
		return sunrise;
	}
	public void setSunrise(long sunrise) {
		this.sunrise = sunrise;
	}
	public long getSunset() {
		return sunset;
	}
	public void setSunset(long sunset) {
		this.sunset = sunset;
	}

}
