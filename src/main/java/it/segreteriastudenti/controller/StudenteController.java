package it.segreteriastudenti.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import it.segreteriastudenti.model.CorsoLaurea;
import it.segreteriastudenti.model.Studente;
import it.segreteriastudenti.util.DummyDB;


@Controller
public class StudenteController {
	
	@Autowired
	private ApplicationContext ctx;
	
	//Metodi
	private DummyDB getDummyDB() {
		return ctx.getBean(DummyDB.class);
	}
	
	@GetMapping("/studenti")
	public ModelAndView getAllStudenti() {
		DummyDB db = getDummyDB();
		return new ModelAndView("studentiView", "dummydb", db);
	}
	
	@GetMapping("/eliminaStudente/{matricola}")
	public ModelAndView cancellaStudente(Model model, @PathVariable String matricola) {
		DummyDB db = getDummyDB();
		db.eliminaStudente(matricola);
		return getAllStudenti();
	}
	
	@GetMapping("/showFormAggiornaStudente/{matricola}")
	public ModelAndView showFormAggiorna(Model model, @PathVariable String matricola) {
		DummyDB db = getDummyDB();
		Studente s = db.getStudenteByMatricola(matricola);
		List<CorsoLaurea> corsi = db.getCorsi();
		ModelAndView mv = new ModelAndView("aggiornaStudente", "studente", new Studente());
		mv.addObject("studente", s);
		mv.addObject("corsi", corsi);
		return mv;
	}
	
	@PostMapping("/aggiornaStudente/{matricola}")
	public ModelAndView aggiornaStudente(@Valid @ModelAttribute("studente") Studente studente, ModelMap model, BindingResult result, @PathVariable String matricola) {
		DummyDB db = getDummyDB();
		if(result.hasErrors()) {
			String msg = "Errore nell'aggiorna Studente";
			ModelAndView mv = new ModelAndView("error", "msg", msg);
			return mv;
		}
		studente.setMatricola(matricola);
		db.eliminaStudente(studente.getMatricola());
		db.aggiungiStudente(studente);
		return getAllStudenti();
	}
	
	@GetMapping("/showFormAggiungiStudente")
	public ModelAndView showFormAggiungi() {
		DummyDB db = getDummyDB();
		List<CorsoLaurea> corsi = db.getCorsi();
		ModelAndView mv = new ModelAndView("aggiungiStudente", "studente", new Studente());
		mv.addObject("corsi", corsi);
		return mv;
	}
	
	@PostMapping("/aggiungiStudente")
	public ModelAndView aggiungiStudente(@Valid @ModelAttribute("studente") Studente studente, ModelMap model, BindingResult result) {
		DummyDB db = getDummyDB();
		if(result.hasErrors()) {
			String msg = "Errore nell'inserimento Studente";
			ModelAndView mv = new ModelAndView("error", "msg", msg);
			return mv;
		}
		Studente s = db.getStudenteByMatricola(studente.getMatricola());
		if(s!=null) {
			if(s.getMatricola().equals(studente.getMatricola())) {
				String msg = "Errore nell'inserimento Studente, matricola gia' presente";
				ModelAndView mv = new ModelAndView("error", "msg", msg);
				return mv;
			}	
		}
		db.aggiungiStudente(studente);
		return getAllStudenti();
	}
	
}
