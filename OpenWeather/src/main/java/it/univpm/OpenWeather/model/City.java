package it.univpm.OpenWeather.model;
import java.text.SimpleDateFormat;

public class City implements Orari{
	private String name;
	private String DescriptionTemp;
	private long sunrise;
	private long sunset;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescriptionTemp() {
		return DescriptionTemp;
	}
	public void setDescriptionTemp(String descriptionTemp) {
		DescriptionTemp = descriptionTemp;
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
	
	public void CityViewToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String SunRise = sdf.format(new java.util.Date(getSunrise()*1000));
		String SunSet = sdf.format(new java.util.Date(getSunset()*1000));
		System.out.println("L'orario dell'alba è : "+SunRise);
		System.out.println("L'orario del tramonto è : "+SunSet);
	}
	
}
