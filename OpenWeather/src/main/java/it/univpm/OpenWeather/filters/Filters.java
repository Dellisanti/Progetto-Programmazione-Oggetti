package it.univpm.OpenWeather.filters;

import java.util.Vector;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import it.univpm.OpenWeather.model.Orari;
import it.univpm.OpenWeather.model.RequestBodyClass;
import it.univpm.OpenWeather.service.Archive;

/**
 * Classe che implementa il filtraggio dei dati dello storico in base al periodo richiesto dall'utente.
 * @author De Ritis Riccardo
 * @author francesco Dellisanti
 */

public class Filters {
	
	public Vector<Orari> ShowFilters(RequestBodyClass body) throws ParseException {
		Vector<Orari> orari = new Vector<Orari>();
		Archive archive = new Archive();
		Vector<Orari> filters = new Vector<Orari>();
		filters.addAll(archive.setArchivie(body));
		if(body.getPeriod()==0)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Periodo non ammesso");
		if(filters.size()<body.getPeriod())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Periodo troppo lungo..");
		for(int i=filters.size()-1;i>=filters.size()-body.getPeriod();i--)
			orari.add(filters.get(i));
		return orari;
	}

	public Vector<Orari> ShowAllFilters(RequestBodyClass body) throws ParseException {
		Archive archive = new Archive();
		Vector<Orari> orari = new Vector<Orari>();
		Vector<Orari> filters = new Vector<Orari>();
		filters.addAll(archive.setArchivie(body));
		for(int i=filters.size()-1;i>=0;i--)
			orari.add(filters.get(i));
		return orari;
	}
	
}