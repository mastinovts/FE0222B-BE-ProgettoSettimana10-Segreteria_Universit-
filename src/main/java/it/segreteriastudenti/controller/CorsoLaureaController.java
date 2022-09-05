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
import it.segreteriastudenti.util.DummyDB;

@Controller
public class CorsoLaureaController {
	
	@Autowired
	private ApplicationContext ctx;
	
	//Metodi
	private DummyDB getDummyDB() {
		return ctx.getBean(DummyDB.class);
	}
	
	@GetMapping("/index")
	public String getIndex() {
		return "redirect:/"; 
	}
	
	//Mostra tutti
	@GetMapping("/corsi")
	public ModelAndView getAllCorsi() {
		DummyDB db = getDummyDB();
		List<CorsoLaurea> corsi = db.getCorsi();
		return new ModelAndView("corsiView", "corsi", corsi);
	}
	
	//Elimina
	@GetMapping("/eliminaCorso/{codice}")
	public ModelAndView cancellaStudente(Model model, @PathVariable String codice) {
		DummyDB db = getDummyDB();
		db.eliminaCorso(codice);
		return getAllCorsi();
	}
	
	//Aggiorna	
	@GetMapping("/showFormAggiornaCorso/{codice}")
	public ModelAndView showFormAggiorna(Model model, @PathVariable String codice) {
		DummyDB db = getDummyDB();
		CorsoLaurea c = db.getCorsoByCodice(codice);
		ModelAndView mv = new ModelAndView("aggiornaCorso", "corso", new CorsoLaurea());
		mv.addObject("corso", c);
		return mv;
	}
	
	//mi porto a dieto il codice con la path
	@PostMapping("/aggiornaCorso/{codice}")
	public ModelAndView aggiornaStudente(@Valid @ModelAttribute("corso") CorsoLaurea corso, ModelMap model, BindingResult result, @PathVariable String codice) {
		DummyDB db = getDummyDB();
		if(result.hasErrors()) {
			String msg = "Errore nell'aggiorna CorsoLaurea";
			ModelAndView mv = new ModelAndView("error", "msg", msg);
			return mv;
		}
		corso.setCodice(codice);
		db.eliminaCorso(codice);
		db.aggiungiCorso(corso);
		return getAllCorsi();
	}
	
	//Inserisci
	@GetMapping("/showFormAggiungiCorso")
	public ModelAndView showFormAggiungiCorso() {
		ModelAndView mv = new ModelAndView("aggiungiCorso", "corso", new CorsoLaurea());
		return mv;
	}
	
	@PostMapping("/aggiungiCorso")
	public ModelAndView aggiungiCorso(@Valid @ModelAttribute("corso") CorsoLaurea corso, ModelMap model, BindingResult result) {
		DummyDB db = getDummyDB();
		if(result.hasErrors()) {
			String msg = "Errore nell'inserimento Corso";
			ModelAndView mv = new ModelAndView("error", "msg", msg);
			return mv;
		}
		CorsoLaurea c = db.getCorsoByCodice(corso.getCodice());
		if(c!=null) {
			if(c.getCodice().equals(corso.getCodice())) {
				String msg = "Errore nell'inserimento CorsoLaurea, corso gia' presente";
				ModelAndView mv = new ModelAndView("error", "msg", msg);
				return mv;
			}	
		}
		db.aggiungiCorso(corso);
		return getAllCorsi();
	}
}
