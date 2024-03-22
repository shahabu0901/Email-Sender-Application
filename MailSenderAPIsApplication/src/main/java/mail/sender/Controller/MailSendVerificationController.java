package mail.sender.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class MailSendVerificationController {
	@Autowired
	private JavaMailSender javaMailSender;
	@Value(value = "shahabu")
	private String token;

	@PostMapping("/mail-sender")
	public String SendMailVerification(HttpServletRequest request, @RequestParam String email_Id) {
		String siteUrl = request.getRequestURL().toString();
		String url = siteUrl.replace(request.getServletPath(), "/verify") + "?token=" + token;
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(email_Id);
			helper.setSubject("Acound Verification");
			helper.setText(url);
			javaMailSender.send(message);
			return "Mail Has Beed Sended";
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Mail Cannot Be Send";
		}
	}

	@GetMapping("/verify")
	public String Verify(@RequestParam String token) {
		if (this.token.equals(token))
			return "Verification Succesfull";
		else
			return "Cannot Verified";
	}
}
