package ws.servicios.interfaces;

import ws.dto.CambioPassword;
import ws.dto.SessionDTO;

public interface CuentaServicio {

    void iniciarSesion(SessionDTO SessionDTO) throws Exception;

    void enviarLinkRecuperacion(String email) throws Exception;

    void cambiarPassword(CambioPassword cambioPassword) throws Exception;

}
