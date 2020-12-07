package com.example.ProgettoProgrammazioneOggetti.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ProgettoProgrammazioneOggetti.model.Prodotto;
import com.example.ProgettoProgrammazioneOggetti.service.ProdottoServizio;

@RestController
public class ControllerRest {
	
	@Autowired
	ProdottoServizio prodottoservizio;
	
	@RequestMapping(value="/prodotti", method=RequestMethod.GET)
	public ResponseEntity<Object> VediProdotti(){
		return new ResponseEntity<>(prodottoservizio.VediProdotti(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/prodotti/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> ModificaProdotto(@PathVariable("id") Integer id,@RequestBody Prodotto prodotto){
		prodottoservizio.ModificaProdotto(id,prodotto);
		return  new ResponseEntity<>("Il prodotto è stato modificato",HttpStatus.OK);
	}
	
	@RequestMapping(value="/prodotti/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> RimuoviProdotto(@PathVariable("id") Integer id){
		prodottoservizio.RimuoviProdotto(id);
		return new ResponseEntity<>("Prodotto eliminato",HttpStatus.OK);
	}
	
	@RequestMapping(value="/prodotti", method=RequestMethod.POST)
	public ResponseEntity<Object> AggiungiProdotto(@RequestBody Prodotto body){
		prodottoservizio.AggiungiProdotto(body);
		return new ResponseEntity<>("Il prodotto è stato aggiunto",HttpStatus.OK);
	}
	
}
