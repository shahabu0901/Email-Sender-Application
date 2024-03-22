package mail.sender.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
public class MailSenderController {
	@Autowired
	private JavaMailSender javaMailSender;

	@PostMapping("/mail-senders")
	public String SendMail(@RequestParam String email_Id) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setSubject("Its For Only Testing");
			helper.setText("Create This Api to Test Our Application");
			helper.setTo(email_Id);
			javaMailSender.send(message);
			return "Mail Has Been Sended";
		} catch (MessagingException e) {

			e.printStackTrace();
			return "Message Cannot Be Send Please Check";
		}

	}

}
