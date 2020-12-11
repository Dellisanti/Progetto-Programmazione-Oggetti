package it.univpm.OpenWeather.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import it.univpm.OpenWeather.service.*;

public class Main {
	
	public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		City city = new City();
		
		Vector<Orari> orariCity = new Vector<Orari>();
		
		jsonParse parse = new jsonParse();
		
		final String ApiKey="7d93f19f21077353e39f87032051beae";
		
		System.out.print("Inserisci il paese : ");
		String paese=input.nextLine();
		String url = "https://api.openweathermap.org/data/2.5/weather?q="+paese+"&units=metric&appid="+ApiKey;
		String data = parse.ApiData(url);
	    City c = parse.Parse(data, city, paese);
	    city.CityViewToday();
		parse.Save(c);
		readFile(orariCity, city);
	}
	
	public static void readFile(Vector<Orari> orariArray, City city) {
		String next;
		int i=0;
		try {
			BufferedReader fileR = new BufferedReader(new FileReader("Ancona.txt"));
			do {
				next=fileR.readLine();
				if(next!=null) {
					String[] s = next.split(",");
					city = new City();
					if(orariArray.isEmpty()) {
						city.setSunrise(Long.valueOf(s[0]));
						city.setSunset(Long.valueOf(s[1]));
						orariArray.add(city);
					} else {
						if(orariArray.get(i).getSunrise()==Long.valueOf(s[0]) && orariArray.get(i).getSunset()==Long.valueOf(s[1])) {
							city.setSunrise(0);
							city.setSunset(0);
							orariArray.add(city);
						} else {
							city.setSunrise(Long.valueOf(s[0]));
							city.setSunset(Long.valueOf(s[1]));
							orariArray.add(city);
							i=orariArray.indexOf(city);
						}
					}
				}
			}while(next!=null);
			fileR.close();
		}catch(IOException e) {
			System.out.println("File non trovato");
			System.out.println(e);
		}
		for(i=0;i<orariArray.size();i++) {
			if(orariArray.get(i)!=null) {
				System.out.println("Alba: "+orariArray.get(i).getSunrise());
				System.out.println("Tramonto: "+orariArray.get(i).getSunset());
			} else {
				System.out.println("null");
			}
		}
	}
	
}

