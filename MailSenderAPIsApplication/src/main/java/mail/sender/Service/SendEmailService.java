package mail.sender.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import mail.sender.dto.EmailConfiguration;

@Service
public class SendEmailService {
	@Autowired
	JavaMailSender javaMailSender;

	public String sendEmail(EmailConfiguration configuration) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setText(configuration.getText());
			helper.setTo(configuration.getEmail());
			helper.setSubject(configuration.getSubject());
			javaMailSender.send(message);
			return "Mail Sent Succesfully";
		} catch (MessagingException e) {
			return "Unable to Send Email ";
		}
	}

}
