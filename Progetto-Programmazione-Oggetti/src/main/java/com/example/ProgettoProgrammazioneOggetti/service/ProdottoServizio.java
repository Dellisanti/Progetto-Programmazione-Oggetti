package com.example.ProgettoProgrammazioneOggetti.service;
import java.util.Collection	;

import com.example.ProgettoProgrammazioneOggetti.model.Prodotto;



public interface ProdottoServizio {
	
	public void AggiungiProdotto(Prodotto prodotto);
	public void ModificaProdotto(Integer id, Prodotto prodotto);
	public void RimuoviProdotto(Integer id);
	public Collection<Prodotto> VediProdotti();
	
}
