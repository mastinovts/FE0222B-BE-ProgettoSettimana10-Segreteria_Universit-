package it.segreteriastudenti.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Studente {
	
	@NotBlank
	private String matricola;
	@NotBlank
	private String nome;
	@NotBlank
	private String cognome;
	@NotBlank
	private String dataNascita;
	@NotBlank
	private String email;
	@NotBlank
	private String indirizzo;
	@NotBlank
	private String cittaResidenza;
	private CorsoLaurea corso;

}
