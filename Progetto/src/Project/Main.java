package Project;

import java.util.Scanner;
//import java.util.Vector;

import Project.model.*;
import Project.service.*;

public class Main {
	
	public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		//Vector<City> cityArray = new Vector<City>();
		City city = new City();
		ParseJsonClass parse = new ParseJsonClass();
		
		final String ApiKey="";
		
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
			
		int scelta;				
		do {
			System.out.println("Menu:");
			System.out.println("1 per vedere le statistiche degli ultimi 5 giorni");
			System.out.println("2 per vedere le statistiche degli ultimi 10 giorni");
			System.out.println("3 per vedere le statistiche degli ultimi 15 giorni");
			System.out.println("4 per vedere le statistiche degli ultimi 30 giorni");
			System.out.println("5 per scegliere il periodo delle statistiche");
			System.out.println("0 per uscire");
			scelta = input.nextInt();			
			switch (scelta){
			case 1:
				Filtri(5);
				break;		
			case 2:
		
				break;
			case 3:
			
				break;
			case 4:
			
				break;
			case 5:
				
				break;
			default:
				System.out.println("Scegli solo i valori corretti");
				break;				
			}
		}while(scelta != 0);
	}
	
}
