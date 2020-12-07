package com.example.ProgettoProgrammazioneOggetti.service;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.ProgettoProgrammazioneOggetti.model.Prodotto;

import java.util.Collection;
import java.util.HashMap;

public class Servizi implements ProdottoServizio{
	
	private static Map<Integer,Prodotto> prodotti = new HashMap<>();
	
	@Override
	public void AggiungiProdotto(Prodotto prodotto) {
		if(prodotti.containsKey(prodotto.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Exsisting id...");
		}
		prodotti.put(prodotto.getId(), prodotto);
	}

	@Override
	public void ModificaProdotto(Integer id, Prodotto prodotto) {
		if(!prodotti.containsKey(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not Exsisting id...");
		}
		prodotti.remove(id);
		prodotto.setId(id);
		prodotti.put(id, prodotto);
	}

	@Override
	public void RimuoviProdotto(Integer id) {
		if(!prodotti.containsKey(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not Exsisting id...");
		}
		prodotti.remove(id);
	}

	@Override
	public Collection<Prodotto> VediProdotti() {
		return prodotti.values();
	}
	
}
