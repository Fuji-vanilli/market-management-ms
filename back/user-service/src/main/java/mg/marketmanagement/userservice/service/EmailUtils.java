package mg.marketmanagement.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailUtils {

    private final JavaMailSender mailSender;
    public void sendMail(String to, String subject, String text){
        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom("cefomarmadagascar@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }
}
