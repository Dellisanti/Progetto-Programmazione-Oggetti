package Project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
		ParseJsonClass parse = new ParseJsonClass();
		
		final String ApiKey="7d93f19f21077353e39f87032051beae";
		
		//do {
			System.out.print("Inserisci il paese : ");
			String paese=input.nextLine();
			String url = "https://api.openweathermap.org/data/2.5/weather?q="+paese+"&units=metric&appid="+ApiKey;
			String data = parse.ApiData(url);
		    City c = parse.Parse(data, city, paese);
			city.CityViewToday(c);
			//city.save(c,cityArray);
			/*char scelta = input.next().charAt(0);
			if(scelta=='n')
				setOk(false);*/
		//}while(ok==true);
	}

	public static boolean setOk(boolean ok) {
		Main.ok = ok;
		return ok;
	}
	
	/*public static void readFile(Vector<City> cityArray) {
		String next;
		try {
			BufferedReader fileR = new BufferedReader(new FileReader("OrariAlbaTramonto"));
			BufferedWriter fileW = new BufferedWriter(new FileWriter("OrariAlbaTramonto"));
			
		}catch(IOException e) {
			System.out.println("File nonn trovato");
			System.out.println(e);
		}
	}*/
	
}
