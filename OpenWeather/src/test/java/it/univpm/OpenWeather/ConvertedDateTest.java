package it.univpm.OpenWeather;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.OpenWeather.model.City;
import it.univpm.OpenWeather.service.ConvertedDate;

class ConvertedDateTest {
	
	private City t = null;
	private City f = null;
	private ConvertedDate d = null;
	
	@BeforeEach
	void setUp() throws Exception{
		t = new City("", "", "");
		f = new City("Statistiche dell'orario dal 2021-01-01 al 2021-01-02 : ",
				"L'orario dell'alba sale di 0 minuti e 5 secondi ", 
				"L'orario del tramonto sale di 1 minuti e 10 secondi ");
		d = new ConvertedDate();
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("test conversione data")
	void test() {
		assertEquals(1609455600, d.ConvertDate("01-01-2021 00:00:00"));
	}
	
	@Test
	@DisplayName("test calcolo data")
	void test1() {
		t = d.calculateData("2021-01-01 07:30:20", "2021-01-02 07:30:25", "2021-01-01 16:55:00", "2021-01-02 16:56:10", 2);
		assertEquals(f, t);
	}

}
