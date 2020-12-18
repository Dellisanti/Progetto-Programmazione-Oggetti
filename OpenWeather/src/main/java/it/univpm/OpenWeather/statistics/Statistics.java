package it.univpm.OpenWeather.statistics;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import it.univpm.OpenWeather.model.City;
import it.univpm.OpenWeather.model.Orari;
import it.univpm.OpenWeather.model.RequestBodyClass;
import it.univpm.OpenWeather.service.Archive;
import it.univpm.OpenWeather.service.Utils;

@Service
public class Statistics {
	
	SimpleDateFormat sdf = new SimpleDateFormat();
	
	public City ShowStats(RequestBodyClass body) throws ParseException {
		Archive archive = new Archive();
		Vector<Orari> orari = new Vector<Orari>();
		City stat = new City();
		orari.addAll(archive.setArchivie(body));
		String Sunrise2 = orari.get(0).getSunrise();
		String Sunset2 = orari.get(0).getSunset();
		String Sunrise1 = orari.get(body.getPeriod()-1).getSunrise();
		String Sunset1 = orari.get(body.getPeriod()-1).getSunset();
		
		System.out.println(sdf.format(new java.util.Date(Sunrise2)));
		stat.setName("Differenze in secondi di "+body.getPeriod()+" giorni");
		
		return stat;
	}
	
}
