package it.univpm.OpenWeather;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.OpenWeather.model.City;

class CityTest {
	
	private City c = null;
	
	@BeforeEach
	void setUp() throws Exception{
		c = new City("", "", "");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		assertEquals("", c.getName());
		assertEquals("" ,c.getSunrise());
		assertEquals("", c.getSunset());
	}
	
	@Test
	void test1() {
		assertAll("valori", ()->assertEquals("", c.getName()),
				()->assertEquals("", c.getSunrise()),
				()->assertEquals("", c.getSunset()));
	}

}
