package geo.dgtid.backend.Services;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EnvioEmailService {
    // Importacion de la dependencia de JavaMailSender:
    public final JavaMailSender javaMailSender;

    public EnvioEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String toEmail,String subject,String body){
        var msg=new SimpleMailMessage();
        msg.setTo(toEmail);
        msg.setSubject(subject);
        msg.setText(body);
        javaMailSender.send(msg);
        System.out.println("Email Enviado Correctamente");
    }

}
