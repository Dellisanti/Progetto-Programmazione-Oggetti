package it.univpm.OpenWeather.service;

import java.util.Vector;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.univpm.OpenWeather.exception.InvalidBodyException;
import it.univpm.OpenWeather.model.City;
import it.univpm.OpenWeather.model.Orari;
import it.univpm.OpenWeather.model.RequestBodyClass;

@Service
public class Variances {
	
	@Autowired
	City city;
	@Autowired
	Archive archive;
	@Autowired
	ConvertedDate data;
	
	public City ShowVariances(RequestBodyClass body) throws ParseException, InvalidBodyException {
		if(body.getType()==null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"type is null..."); 
		else if(body.getType().equals("max"))
			return ShowMaxVariances(body);
		else if(body.getType().equals("min"))
			return ShowMinVariances(body);
		else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tipo non ammesso..");
	}
	
	public City ShowMaxVariances(RequestBodyClass body) throws ParseException, InvalidBodyException {
		long maxSunrise=0, maxSunset=0;
		int giornoMaxSunset = 0, giornoMaxSunrise = 0;
		Vector<Orari> orari = new Vector<Orari>();
		orari.addAll(archive.setArchivie(body));
		if(body.getStart()==0 && body.getEnd()==0) {
			for(int i=1;i<orari.size();i++) {
				long sunrise = data.ConvertDate(orari.get(i).getSunrise()) - data.ConvertDate(orari.get(i-1).getSunrise()) - 86400;
				long sunset = data.ConvertDate(orari.get(i).getSunset()) - data.ConvertDate(orari.get(i-1).getSunset()) - 86400;
				if(maxSunrise<sunrise) {
					maxSunrise=sunrise;
					giornoMaxSunrise = i;
				}
				if(maxSunset<sunset) {
					maxSunset=sunset;
					giornoMaxSunset = i;
				}
			}
			city.setName("Varianza massima dal "+orari.get(0).getSunrise().substring(0,10)+
					" al "+orari.get(orari.size()-1).getSunrise().substring(0,10));
			long minSunrise = (maxSunrise%3600)/60;
			long minSunset = (maxSunset%3600)/60;
			long secSunrise = (maxSunrise%3600)%60;
			long secSunset = (maxSunset%3600)%60;
			city.setSunrise("Il giorno "+orari.get(giornoMaxSunrise).getSunrise().substring(0,10)+
				" con "+minSunrise+" minuti e "+secSunrise+" secondi");
			city.setSunset("Il giorno "+orari.get(giornoMaxSunset).getSunset().substring(0,10)+
				" con "+minSunset+" minuti e "+secSunset+" secondi");
			return city;
		}
		else {
			for(int i=body.getStart();i<body.getEnd();i++) {
				if(body.getStart()==0)
					i++;
				long sunrise = data.ConvertDate(orari.get(i).getSunrise()) - data.ConvertDate(orari.get(i-1).getSunrise()) - 86400;
				long sunset = data.ConvertDate(orari.get(i).getSunset()) - data.ConvertDate(orari.get(i-1).getSunset()) - 86400;
				if(maxSunrise<sunrise) {
					maxSunrise=sunrise;
					giornoMaxSunrise = i;
				}
				if(maxSunset<sunset) {
					maxSunset=sunset;
					giornoMaxSunset = i;
				}
			}
			city.setName("Varianza massima dal "+orari.get(body.getStart()).getSunrise().substring(0,10)+
				" al "+orari.get(body.getEnd()).getSunrise().substring(0,10));
			long minSunrise = (maxSunrise%3600)/60;
			long minSunset = (maxSunset%3600)/60;
			long secSunrise = (maxSunrise%3600)%60;
			long secSunset = (maxSunset%3600)%60;
			city.setSunrise("Il giorno "+orari.get(giornoMaxSunrise).getSunrise().substring(0,10)+
				" con "+minSunrise+" minuti e "+secSunrise+" secondi");
			city.setSunset("Il giorno "+orari.get(giornoMaxSunset).getSunset().substring(0,10)+
				" con "+minSunset+" minuti e "+secSunset+" secondi");
			return city;
		}
			
	}
	
	public City ShowMinVariances(RequestBodyClass body) throws ParseException, InvalidBodyException {
		long minSunrise=1000, minSunset=1000;
		int giornoMinSunset = 0, giornoMinSunrise = 0;
		Vector<Orari> orari = new Vector<Orari>();
		orari.addAll(archive.setArchivie(body));
		if(body.getStart()==0 && body.getEnd()==0) {
			for(int i=1;i<orari.size();i++) {
				long sunrise = data.ConvertDate(orari.get(i).getSunrise()) - data.ConvertDate(orari.get(i-1).getSunrise()) - 86400;
				long sunset = data.ConvertDate(orari.get(i).getSunset()) - data.ConvertDate(orari.get(i-1).getSunset()) - 86400;
				if(minSunrise>sunrise) {
					minSunrise=sunrise;
					giornoMinSunrise = i;
				}
				if(minSunset>sunset) {
					minSunset=sunset;
					giornoMinSunset = i;
				}
			}
			city.setName("Varianza minima dal "+orari.get(0).getSunrise().substring(0,10)+
					" al "+orari.get(orari.size()-1).getSunrise().substring(0,10));
			long secSunrise = (minSunrise%3600)%60;
			long secSunset = (minSunset%3600)%60;
			city.setSunrise("Il giorno "+orari.get(giornoMinSunrise).getSunrise().substring(0,10)+" con "+secSunrise+" secondi");
			city.setSunset("Il giorno "+orari.get(giornoMinSunset).getSunset().substring(0,10)+" con "+secSunset+" secondi");
			return city;
		}
		else {
			for(int i=body.getStart();i<body.getEnd();i++) {
				if(body.getStart()==0)
					i++;
				long sunrise = data.ConvertDate(orari.get(i).getSunrise()) - data.ConvertDate(orari.get(i-1).getSunrise()) - 86400;
				long sunset = data.ConvertDate(orari.get(i).getSunset()) - data.ConvertDate(orari.get(i-1).getSunset()) - 86400;
				if(minSunrise>sunrise) {
					minSunrise=sunrise;
					giornoMinSunrise = i;
				}
				if(minSunset>sunset) {
					minSunset=sunset;
					giornoMinSunset = i;
				}
			}
			city.setName("Varianza minima dal "+orari.get(body.getStart()).getSunrise().substring(0,10)+
					" al "+orari.get(body.getEnd()).getSunrise().substring(0,10));
			long secSunrise = (minSunrise%3600)%60;
			long secSunset = (minSunset%3600)%60;
			city.setSunrise("Il giorno "+orari.get(giornoMinSunrise).getSunrise().substring(0,10)+" con "+secSunrise+" secondi");
			city.setSunset("Il giorno "+orari.get(giornoMinSunset).getSunset().substring(0,10)+" con "+secSunset+" secondi");
			return city;
		}
	}
	
}
