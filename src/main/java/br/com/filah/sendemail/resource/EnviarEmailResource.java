package br.com.filah.sendemail.resource;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.filah.sendemail.model.entites.DadosMensagem;
import br.com.filah.sendemail.model.service.EnviarEmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
@RequestMapping("/enviaremail")
public class EnviarEmailResource {
	
	private final EnviarEmailService enviarEmailService;

	
	@GetMapping
	public ModelAndView oppenView() {
		log.info("Rendenrizando para EnviarEmail.html");
		ModelAndView mv = new ModelAndView("EnviarEmail.html");
		mv.addObject(new DadosMensagem());
		return mv;
	}

	@PostMapping
	public void enviarEmai(DadosMensagem dadosMensagem) {
		try {
			log.info("Iniciando a sessao");
		Session session = 	enviarEmailService.inciarSessao(dadosMensagem.getRemetente(), dadosMensagem.getSenha(), null);
		log.info("Enviando email para: "+dadosMensagem.getDestinatarios());
		enviarEmailService.enviarMensagemEmail(dadosMensagem, session);
		}catch(RuntimeException e) {
			e.printStackTrace();
		}
	}
}
