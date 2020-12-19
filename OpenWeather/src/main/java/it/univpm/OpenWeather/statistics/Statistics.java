package it.univpm.OpenWeather.statistics;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.univpm.OpenWeather.model.City;
import it.univpm.OpenWeather.model.Orari;
import it.univpm.OpenWeather.model.RequestBodyClass;
import it.univpm.OpenWeather.service.Archive;
import it.univpm.OpenWeather.service.ConvertedDate;
import it.univpm.OpenWeather.service.Utils;

@Service
public class Statistics {
	
	@Autowired
	ConvertedDate date;
	@Autowired
	Archive archive;
	@Autowired
	City stat;
	@Autowired
	ConvertedDate data;
	
	public City ShowStats(RequestBodyClass body) throws ParseException {
		if(body.getPeriod()==0)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Periodo non ammesso..");
		Vector<Orari> orari = new Vector<Orari>();
		orari.addAll(archive.setArchivie(body));
		if(body.getPeriod()>orari.size())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Periodo troppo lungo");
		String Sunrise1 = orari.get(0).getSunrise();
		String Sunset1 = orari.get(0).getSunset();
		String Sunrise2 = orari.get(body.getPeriod()-1).getSunrise();
		String Sunset2 = orari.get(body.getPeriod()-1).getSunset();
		long sunrise = date.ConvertToDate(Sunrise2) - date.ConvertToDate(Sunrise1);
		long sunset = date.ConvertToDate(Sunset2) - date.ConvertToDate(Sunset1);
		stat = data.calculateData(sunrise,sunset,body.getPeriod()-1);
		return stat;
	}
	
}
