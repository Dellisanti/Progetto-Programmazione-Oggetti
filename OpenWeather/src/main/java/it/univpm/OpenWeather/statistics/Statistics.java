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
	Archive archive;
	@Autowired
	ConvertedDate data;
	
	public City ShowStats(RequestBodyClass body) throws ParseException {
		Vector<Orari> orari = new Vector<Orari>();
		orari.addAll(archive.setArchivie(body));
		if(body.getEnd()>orari.size()-1)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Periodo troppo lungo");
		if(body.getEnd()==0 && body.getStart()==0) {
			String Sunrise1 = orari.get(0).getSunrise();
			String Sunset1 = orari.get(0).getSunset();
			String Sunrise2 = orari.get(orari.size()-1).getSunrise();
			String Sunset2 = orari.get(orari.size()-1).getSunset();
			return data.calculateData(Sunrise1,Sunrise2,Sunset1,Sunset2,orari.size()-1);
		}
		String Sunrise1 = orari.get(body.getStart()).getSunrise();
		String Sunset1 = orari.get(body.getStart()).getSunset();
		String Sunrise2 = orari.get(body.getEnd()).getSunrise();
		String Sunset2 = orari.get(body.getEnd()).getSunset();
		return data.calculateData(Sunrise1,Sunrise2,Sunset1,Sunset2,body.getEnd()-body.getStart());
	}
	
}
