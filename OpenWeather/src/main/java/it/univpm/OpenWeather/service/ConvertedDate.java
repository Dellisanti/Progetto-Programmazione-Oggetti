package it.univpm.OpenWeather.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univpm.OpenWeather.model.City;

@Service
public class ConvertedDate {
	
	@Autowired
	City c;

	public City calculateData(long sunrise, long sunset, int giorni) {
		sunrise -= 86400*giorni;
		sunset -= 86400*giorni;
		long Ore1 = sunrise/3600;
		long Minuti1=(sunrise%3600)/60;
		long Secondi1=(sunrise%3600)%60;
		long Ore2 = sunset/3600;
		long Minuti2=(sunset%3600)/60;
		long Secondi2=(sunset%3600)%60;
		c.setName("Statistiche dell'orario su "+giorni+" giorni :");
		c.setSunrise("L'alba cambia di "+Ore1+":"+Minuti1+":"+Secondi1);
		c.setSunset("Il tramonto cambia di "+Ore2+":"+Minuti2+":"+Secondi2);
		return c;
	}
	
	public long ConvertToDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date convertedDate = new Date();
		try {
			convertedDate = sdf.parse(dateString);
		}catch(ParseException e) {
			e.printStackTrace();
		}
		long date = convertedDate.getTime()/1000;
		return date;
	}
	
}
