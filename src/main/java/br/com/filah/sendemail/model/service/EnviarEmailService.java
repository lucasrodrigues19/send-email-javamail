package br.com.filah.sendemail.model.service;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import br.com.filah.sendemail.model.entites.DadosMensagem;

@Service
public class EnviarEmailService {

	public Session inciarSessao(String user, String senha, Properties props) {
		Session session = null;
		try {
			session = Session.getDefaultInstance(getProperties(), new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, senha);
				}
			});

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return session;
	}

	public void enviarMensagemEmail(DadosMensagem dadosMensagem, Session session) {
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(dadosMensagem.getRemetente()));
			// Remetente

			Address[] toUser = InternetAddress // Destinatário(s)
					.parse(dadosMensagem.getDestinatarios());

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(dadosMensagem.getAssunto());// Assunto
			message.setText(dadosMensagem.getMensagem());
			/** Método para enviar a mensagem criada */
			Transport.send(message);

			System.out.println("Feito!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private Properties getProperties() {
		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.debug", "true");
		return props;
	}
}
