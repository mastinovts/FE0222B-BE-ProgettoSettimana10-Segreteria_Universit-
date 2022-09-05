package it.segreteriastudenti.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.segreteriastudenti.model.CorsoLaurea;

@Component
public class CorsoDiLaureaConverter implements Converter<String, CorsoLaurea> {
	
	@Autowired
	private ApplicationContext ctx;

	//Converto il codice di un corso nel suo corrispondente corso di laurea
	@Override
	public CorsoLaurea convert(String codice) {
		DummyDB db =ctx.getBean(DummyDB.class);
		return db.getCorsoByCodice(codice);
	}

}
