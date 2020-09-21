package br.com.filah.sendemail.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.filah.sendemail.model.entites.DadosMensagem;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
@RequestMapping("/enviaremail")
public class EnviarEmailResource {

	@GetMapping
	public ModelAndView enviarEmail() {
		log.info("Rendenrizando para EnviarEmail.html");
		ModelAndView mv = new ModelAndView("EnviarEmail.html");
		mv.addObject(new DadosMensagem());
		return mv;
	}
	
}
