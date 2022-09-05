package it.segreteriastudenti.model;



import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CorsoLaurea {
	
	@NotBlank
	private String codice;
	@NotBlank
	private String nome;
	private String indirizzo;
	@Min(10)
	private Integer nEsami;

}
