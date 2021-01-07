package it.univpm.OpenWeather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@SuppressWarnings("serial")
public class InvalidBodyException extends Exception{
	
	public InvalidBodyException(String out) {
		super();
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,out);
	}
	
}
