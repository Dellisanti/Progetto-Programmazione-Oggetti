package it.univpm.OpenWeather;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.OpenWeather.service.ConvertedDate;

class ConvertedDateTest {
	
	private ConvertedDate c = null;
	//String a = 1609498800;
	
	@BeforeEach
	void setUp() throws Exception{
		c = new ConvertedDate();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		//assertEquals(c.ConvertDate(a), "01-01-2021 12:00:00");
	}

}
