package Project;

import java.util.Scanner;
import java.util.Vector;

import Project.model.*;
import Project.service.*;

public class Main {
	
	public static Scanner input = new Scanner(System.in);
	private static boolean ok=true;
	
	public static void main(String[] args) {
		
		Vector<City> cityArray = new Vector<City>();
		City city = new City();
		ParseJsonClass parse;
		
		final String ApiKey="";
		
		do {
			System.out.print("Inserisci il paese : ");
			String paese=input.nextLine();
			String url = "https://api.openweathermap.org/data/2.5/weather?q="+paese+"&units=metric&appid="+ApiKey;
			parse = new ParseJsonClass();
			String data=parse.ApiData(url);
			cityArray.add(parse.Parse(data, city));
			city.CityViewToday(cityArray);
			char scelta = input.next().charAt(0);
			if(scelta=='n')
				setOk(false);
		}while(ok==true);
	}

	public static boolean setOk(boolean ok) {
		Main.ok = ok;
		return ok;
	}
	
}
