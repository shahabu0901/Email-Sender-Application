package mail.sender.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class EmailConfiguration {
	private String Email;
	private String Subject;
	private String Text;

}
