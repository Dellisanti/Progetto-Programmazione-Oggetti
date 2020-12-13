package it.univpm.OpenWeather.filters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import it.univpm.OpenWeather.model.City;
import it.univpm.OpenWeather.model.Orari;

public class StatsAndFilters {
	
	City c;
	
	public Vector<Orari> ShowFilters(Vector<Orari> orari, City city, String paese, int periodo) {
		orari = new Vector<Orari>();
		String next;
		try {
			BufferedReader fileR = new BufferedReader(new FileReader("Ancona.txt"));
			for(int i=0;i<periodo;i++) {
				next=fileR.readLine();
				if(next!=null) {
					String[] s = next.split(",");
					city = new City();
					city.setName(paese);
					city.setSunrise(Long.valueOf(s[0]));
					city.setSunset(Long.valueOf(s[1]));
					orari.add(city);
				}
				else {
					fileR.close();
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Attenzione!.. periodo troppo lungo..");
				}
			}
			fileR.close();
		}catch(IOException e) {
			System.out.println("File non trovato");
			System.out.println(e);
		}
		return orari;
	}
	
	public String ShowStats(Vector<Orari> orari, City city, String paese, int periodo) {
		orari = new Vector<Orari>();
		String next;
		try {
			BufferedReader fileR = new BufferedReader(new FileReader("Ancona.txt"));
			for(int i=0;i<periodo;i++) {
				next=fileR.readLine();
				if(next!=null) {
					String[] s = next.split(",");
					if(i==0) {
						c = new City();
						c.setSunrise(Long.valueOf(s[0]));
						c.setSunset(Long.valueOf(s[1]));
					}
					if(i==periodo-1) {
						city = new City();
						city.setSunrise(Long.valueOf(s[0]));
						city.setSunset(Long.valueOf(s[1]));
					}
				}
				else {
					fileR.close();
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Periodo troppo lungo..");
				}
			}
			fileR.close();
		}catch(IOException e) {
			System.out.println("File non trovato");
			System.out.println(e);
		}
		long sunrise = city.getSunrise()-c.getSunrise();
		long sunset = city.getSunset()-c.getSunset();
		return "Negli ultimi "+periodo+" giorni : \n"+"L'alba cambia di "+sunrise/1000+" sec\nIl tramonto cambia di "+sunset/1000+" sec";
	}
	
}