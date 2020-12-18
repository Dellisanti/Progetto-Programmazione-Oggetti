package it.univpm.OpenWeather.filters;

import java.util.Vector;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import it.univpm.OpenWeather.model.City;
import it.univpm.OpenWeather.model.RequestBodyClass;
import it.univpm.OpenWeather.service.Archivie;

/**
 * Classe che implementa il filtraggio dei dati dello storico in base al periodo richiesto dall'utente.
 * @author De Ritis Riccardo
 * @author francesco Dellisanti
 */

public class Filters {
	
	protected Archivie archivie = new Archivie();
	
	public Vector<City> ShowFilters(RequestBodyClass body) throws ParseException {
		Vector<City> orari = new Vector<City>();
		Vector<City> filters = new Vector<City>();
		filters.addAll(archivie.setArchivie(body));
		if(body.getPeriod()==0)
			return filters;
		if(filters.size()<body.getPeriod())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Periodo troppo lungo..");
		for(int i=filters.size()-1;i>=filters.size()-body.getPeriod();i--)
			orari.add(filters.get(i));
		return orari;
	}

	/*public Vector<City> ShowAllFilters(Vector<City> orari, City city, String paese) throws ParseException {
		JSONObject obj = new JSONObject();
		orari = new Vector<City>();
		String next;
		try {
			BufferedReader fileR = new BufferedReader(new FileReader("Ancona.txt"));
			do {
				next = fileR.readLine();
				if(next!=null) {
					obj = (JSONObject)JSONValue.parseWithException(next);
					if(paese.equals(obj.get("name"))) {
						city = new City();
						city.setName((String)obj.get("name"));
						city.setSunrise((String)obj.get("sunrise"));
						city.setSunset((String)obj.get("sunset"));
						orari.add(city);
					}
				}
				else 
					fileR.close();
			}while(next!=null); 
			fileR.close();
		}catch(IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"File non trovato");
		}
		if(orari.isEmpty())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Città non presente nell'archivio");
		else
			return orari;
	}*/
		
	
}