package ws.servicios.impl;

import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import ws.dto.EmailDTO;
import ws.servicios.interfaces.EmailServicio;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
@RequiredArgsConstructor
public class EmailServicioImpl implements EmailServicio {
    private final JavaMailSender javaMailSender;

    @Override
    public void enviarCorreo(EmailDTO emailDto) throws Exception {
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);
        helper.setSubject(emailDto.asunto());
        helper.setText(emailDto.cuerpo(), true);
        helper.setTo(emailDto.destinario());
        helper.setFrom("no_reply@dominio.com");
        javaMailSender.send(mensaje);
    }
}




    

