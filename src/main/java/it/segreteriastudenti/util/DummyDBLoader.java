package it.segreteriastudenti.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import it.segreteriastudenti.model.CorsoLaurea;
import it.segreteriastudenti.model.Studente;

@Component
public class DummyDBLoader implements CommandLineRunner {
	
	@Autowired
	private ApplicationContext ctx;

	@Override
	public void run(String... args) throws Exception {
		DummyDB db = ctx.getBean(DummyDB.class);
		valorizzaDB(db);
	}
	
	
	//Riempio il db
	private void valorizzaDB(DummyDB db) {
		//Valorizzo i Corsi
		CorsoLaurea c1 = new CorsoLaurea();
		c1.setCodice("a1");
		c1.setNome("Informatica");
		c1.setNEsami(10);
		c1.setIndirizzo("Informatico");
		db.aggiungiCorso(c1);
		
		CorsoLaurea c2 = new CorsoLaurea();
		c2.setCodice("a2");
		c2.setNome("Analisi");
		c2.setNEsami(11);
		c2.setIndirizzo("Ingegneria");
		db.aggiungiCorso(c2);
		
		CorsoLaurea c3 = new CorsoLaurea();
		c3.setCodice("a3");
		c3.setNome("Economia");
		c3.setNEsami(12);
		c3.setIndirizzo("Gestionale");
		db.aggiungiCorso(c3);	
		
		//Valorizzo gli Studenti
		Studente s1 = new Studente();
		s1.setMatricola("m1");
		s1.setNome("Mario");
		s1.setCognome("Rossi");
		s1.setIndirizzo("Via Roma");
		s1.setCittaResidenza("Roma");
		s1.setDataNascita("1991-01-01");
		s1.setEmail("mariorossi@gmail.com");
		s1.setCorso(c1);
		db.aggiungiStudente(s1);
		
		Studente s2 = new Studente();
		s2.setMatricola("m2");
		s2.setNome("Luigi");
		s2.setCognome("Verdi");
		s2.setIndirizzo("Via Dante");
		s2.setCittaResidenza("Cremona");
		s2.setDataNascita("1992-02-02");
		s2.setEmail("luigiverdi@gmail.com");
		s2.setCorso(c2);
		db.aggiungiStudente(s2);
		
		Studente s3 = new Studente();
		s3.setMatricola("m3");
		s3.setNome("Peach");
		s3.setCognome("Rosa");
		s3.setIndirizzo("Viale Europa");
		s3.setCittaResidenza("Torino");
		s3.setDataNascita("1993-03-03");
		s3.setEmail("peachrosa@libero.it");
		s3.setCorso(c1);
		db.aggiungiStudente(s3);
	}

}
