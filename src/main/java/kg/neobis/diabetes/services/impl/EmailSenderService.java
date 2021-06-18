package kg.neobis.diabetes.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String username;

    public void sendEmailToConfirmEmail(String email, String codeToConfirm) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setSubject("Подтверждение почты! TEAM#1");
        simpleMailMessage.setText("Здравствуйте," + "!\nДля подтверждение почты пожалуйста введите следующий код: " + codeToConfirm);
        javaMailSender.send(simpleMailMessage);
    }

    public void sendEmailToRestorePassword(String email, String codeToConfirm) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setSubject("Восстановление пароля! TEAM#1");
        simpleMailMessage.setText("Здравствуйте," + "!\nДля восстановление пароля пожалуйста введите следующий код: " + codeToConfirm);
        javaMailSender.send(simpleMailMessage);
    }
}
