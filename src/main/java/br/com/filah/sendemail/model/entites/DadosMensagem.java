package br.com.filah.sendemail.model.entites;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DadosMensagem {

	@EqualsAndHashCode.Include
	private Long id;
	private String remetente;
	private String senha;
	private String destinatarios;
	private String assunto;
	private String mensagem;
	
	
	
}
