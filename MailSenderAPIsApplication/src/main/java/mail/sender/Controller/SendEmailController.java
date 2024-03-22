package mail.sender.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mail.sender.Service.SendEmailService;
import mail.sender.dto.EmailConfiguration;

@RestController

public class SendEmailController {
	@Autowired
	private SendEmailService emailService;
	@Autowired
	private EmailConfiguration configuration;

	@PostMapping("/sendmail")
	public String sendEmail(String email_Id) {
		configuration.setEmail(email_Id);
		configuration.setSubject("Testing java mail sender");
		configuration.setText("We are sending this mail to test our mail sending API");
		return emailService.sendEmail(configuration);
	}
}
