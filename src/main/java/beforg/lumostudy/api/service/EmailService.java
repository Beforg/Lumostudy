package beforg.lumostudy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String cod) {
        String subject = "Ativação da Conta";
        String text =
                "Bem-vindo ao Lumostudy! clique no link para ativar sua conta: http://localhost:8080/auth/activate/" + cod;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);

    }

    public void recuperarSenha(String to, String resetToken) {
        String subject = "Recuperação de Senha";
        String text = "Clique no link para redefinir sua senha: http://localhost:4200/auth/reset-password/" + resetToken;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
