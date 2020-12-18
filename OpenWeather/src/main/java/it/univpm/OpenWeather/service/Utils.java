package it.univpm.OpenWeather.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class Utils {
		
	public String ApiData (String url) {
		String data = "";
		String line = "";
		try {
			URLConnection openConnection = new URL(url).openConnection();
			InputStream in = openConnection.getInputStream();
			try {
				BufferedReader buf = new BufferedReader(new InputStreamReader(in));
				while((line=buf.readLine())!=null)
					data+=line;
			}finally {
				in.close();
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Città non trovata");
		}
		return data;
	}
	
	public String readApiKey() {
		String ApiKey;
		try {
			BufferedReader fileR = new BufferedReader(new FileReader("ApiKey.txt"));
			ApiKey = fileR.readLine();
			if(ApiKey==null) {
				fileR.close();
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Inserire prima l'ApiKey nel file..");
			}
			fileR.close();
		}catch(IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"File non trovato..");
		}
		return ApiKey;
	}
	
}
