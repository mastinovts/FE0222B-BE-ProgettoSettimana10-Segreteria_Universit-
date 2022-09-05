package it.segreteriastudenti.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.segreteriastudenti.model.CorsoLaurea;
import it.segreteriastudenti.model.Studente;
import lombok.Getter;

//Classe che simula il database
@Component
@Getter
public class DummyDB {
	
	private List<Studente> studenti = new ArrayList<>();
	private List<CorsoLaurea> corsi = new ArrayList<>();
	
	
	public boolean aggiungiCorso(CorsoLaurea c) {
		if(corsi.contains(c))
			return false;
		corsi.add(c);
		return true;
	}
	
	public boolean aggiungiStudente(Studente s) {
		if(studenti.contains(s))
			return false;
		studenti.add(s);
		return true;
	}
	
	public boolean eliminaStudente(String matricola) {
		for(int i =0; i<studenti.size(); i++) {
			if(studenti.get(i).getMatricola().equals(matricola)) {
				studenti.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean eliminaCorso(String codice) {
		for(int i =0; i<corsi.size(); i++) {
			if(corsi.get(i).getCodice().equals(codice)) {
				corsi.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public Studente getStudenteByMatricola(String matricola) {
		for(Studente s: studenti) {
			if(s.getMatricola().equals(matricola))
				return s;
		}
		return null;
	}
	
	public CorsoLaurea getCorsoByCodice(String codice) {
		for(CorsoLaurea c: corsi) {
			if(c.getCodice().equals(codice))
				return c;
		}
		return null;
	}


}
