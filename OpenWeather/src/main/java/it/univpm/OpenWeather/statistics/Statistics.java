package it.univpm.OpenWeather.statistics;

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
		long sunrise = date.ConvertDate(Sunrise2) - date.ConvertDate(Sunrise1);
		long sunset = date.ConvertDate(Sunset2) - date.ConvertDate(Sunset1);
		stat = data.calculateData(sunrise,sunset,body.getPeriod());
		return stat;
	}
	
}
