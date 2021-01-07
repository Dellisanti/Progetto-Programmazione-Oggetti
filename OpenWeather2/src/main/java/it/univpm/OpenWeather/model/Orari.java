package it.univpm.OpenWeather.model;

/**
 * Interfaccia con Get e Set degli orari di alba e tramonto
 * @author De Ritis Riccardo
 * @author francesco Dellisanti
 */

public interface Orari {
	
	public String getSunrise();
	public void setSunrise(String sunrise);
	public String getSunset();
	public void setSunset(String sunset);
	
}
