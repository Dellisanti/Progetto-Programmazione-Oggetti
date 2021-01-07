package it.univpm.OpenWeather.model;

public class RequestBodyClass {
	private int start;
	private int end;
	private String name;
	private String type;
	 
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
