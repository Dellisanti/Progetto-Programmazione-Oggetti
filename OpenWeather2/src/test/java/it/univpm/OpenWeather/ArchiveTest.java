package it.univpm.OpenWeather;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import it.univpm.OpenWeather.exception.InvalidBodyException;
import it.univpm.OpenWeather.model.Orari;
import it.univpm.OpenWeather.model.RequestBodyClass;
import it.univpm.OpenWeather.service.Archive;

class ArchiveTest {

	private Archive archive = new Archive();
	private RequestBodyClass body = null;
	private RequestBodyClass body1 = null;
	private RequestBodyClass body2 = null;
	private RequestBodyClass body3 = null;
	
	@BeforeEach
	void setUp() throws Exception{
		body = new RequestBodyClass(0, 10, "torino", "");
		body1 = new RequestBodyClass(-1, 10, "torino", "");
		body2 = new RequestBodyClass(5, 2, "torino", "");
		body3 = new RequestBodyClass(5, 456, "torino", "");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("test archivio")
	void test() throws InvalidBodyException, ParseException {
		Vector<Orari> o = null;
		
		o = archive.setArchivie(body);
		assertEquals(o.size(), 21);
		assertEquals(body.getStart(), 0);
		assertEquals(body.getEnd(), 10);
		assertEquals(body.getName(), "torino");
	}
	
	@Test
	@DisplayName("test eccezioni")
	void test1() {
		ResponseStatusException e;
		
		e = assertThrows(ResponseStatusException.class, ()->{archive.setArchivie(body1);});
		assertTrue(e.getMessage().contains("Periodo negativo..."));
		
		e = assertThrows(ResponseStatusException.class, ()->{archive.setArchivie(body2);});
		assertTrue(e.getMessage().contains("Periodo non ammesso..."));
		
		e = assertThrows(ResponseStatusException.class, ()->{archive.setArchivie(body3);});
		assertTrue(e.getMessage().contains("Periodo troppo lungo"));
		
	}

}
