package it.univpm.OpenWeather.statistics;

import java.util.Vector;
import org.json.simple.parser.ParseException;
import it.univpm.OpenWeather.model.City;
import it.univpm.OpenWeather.model.RequestBodyClass;
import it.univpm.OpenWeather.service.Utils;

public class Statistics {
	
	protected Utils util = new Utils();
	
	/*public City ShowStats(RequestBodyClass body) throws ParseException {
		Vector<City> orari = new Vector<City>();
		City stat = new City();
		orari.addAll(util.setArchivie(body));
		String Sunrise2 = orari.get(0).getSunrise();
		String Sunset2 = orari.get(0).getSunset();
		String Sunrise1 = orari.get(1).getSunrise();
		String Sunset1 = orari.get(1).getSunset();
		stat.setName("Differenze in secondi di "+body.getPeriod()+" giorni");
		return null;
		//stat.setSunrise(sunrise);
	}*/
	
}
