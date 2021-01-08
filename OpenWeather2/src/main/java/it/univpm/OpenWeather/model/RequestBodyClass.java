package it.univpm.OpenWeather.model;

/**
 * Classe utilizzata per inserire i dati e ottenere filtri, statistiche e varianze.
 * @author De Ritis Riccardo
 * @author Dellisanti Francesco
 */

public class RequestBodyClass {
	private int start;
	private int end;
	private String name;
	private String type;
	
	
	public RequestBodyClass(int start, int end, String name, String type) {
		this.start = start;
		this.end = end;
		this.name = name;
		this.type = type;
	}
	 
	/**
	 * Metodi Get di name,start,end e Type
	 */
	public String getName() {
		return name;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public String getType() {
		return type;
	}

}
