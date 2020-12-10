package Project.model;

public class City {
	private String name;
	private String DescriptionTemp;
	private double tempMin;
	private double tempMed;
	private double tempMax;
	private String sunrise;
	private String sunset;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getTempMin() {
		return tempMin;
	}
	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
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
	public double getTempMax() {
		return tempMax;
	}
	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}
	public double getTempMed() {
		return tempMed;
	}
	public void setTempMed(double tempMed) {
		this.tempMed = tempMed;
	}
	public String getDescriptionTemp() {
		return DescriptionTemp;
	}
	public void setDescriptionTemp(String descriptionTemp) {
		DescriptionTemp = descriptionTemp;
	}
	
	public void CityViewToday(City city) {
			System.out.println("Oggi : ");
			System.out.println("La temperatura massima è : "+city.getTempMax()+"C°");
			System.out.println("La temperatura minima è : "+city.getTempMin()+"C°");
			System.out.println("La temperatura media è : "+city.getTempMed()+"C°");
			System.out.println("L'orario dell'alba è : "+city.getSunrise());
			System.out.println("L'orario del tramonto è : "+city.getSunset());
	}
	
}
