package Project.model;
//import java.util.Vector;

public class City {
	private String name;
	private String DescriptionTemp;
	private String sunrise;
	private String sunset;
	
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
	public String getDescriptionTemp() {
		return DescriptionTemp;
	}
	public void setDescriptionTemp(String descriptionTemp) {
		DescriptionTemp = descriptionTemp;
	}
	
	public void CityViewToday(City city) {
			System.out.println("L'orario dell'alba è : "+city.getSunrise());
			System.out.println("L'orario del tramonto è : "+city.getSunset());
	}
	
	/*public void save(City c, Vector<City> cityArray) {
		if(!cityArray.contains(c.getSunrise()))
			if(!cityArray.contains(c.getSunset()))
				cityArray.add(c);
	}*/
	
}
