package it.univpm.OpenWeather.filters;

import java.util.Vector;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import it.univpm.OpenWeather.exception.InvalidBodyException;
import it.univpm.OpenWeather.model.Orari;
import it.univpm.OpenWeather.model.RequestBodyClass;
import it.univpm.OpenWeather.service.Archive;

/**
 * Classe che implementa il filtraggio dei dati dello storico in base al periodo richiesto dall'utente.
 * @author De Ritis Riccardo
 * @author Dellisanti Francesco
 */

@Service
public class Filters {
	
	public Vector<Orari> ShowFilters(RequestBodyClass body) throws ParseException, InvalidBodyException {
		Vector<Orari> orari = new Vector<Orari>();
		Archive archive = new Archive();
		Vector<Orari> filters = new Vector<Orari>();
		filters.addAll(archive.setArchivie(body));
		if(body.getStart()==0 && body.getEnd()==0) { 
			for(int i=filters.size()-1;i>=0;i--) //aggiunge gli elementi dall'alto verso il basso
				orari.add(filters.get(i));
			return orari;
		}
		for(int i=body.getEnd();i>=body.getStart();i--)
			orari.add(filters.get(i));
		return orari;
	}
	
}