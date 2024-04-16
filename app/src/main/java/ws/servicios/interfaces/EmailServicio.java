package ws.servicios.interfaces;
import ws.dto.EmailDTO;

public interface EmailServicio {
    void enviarCorreo(EmailDTO emailDto)throws Exception;
}
