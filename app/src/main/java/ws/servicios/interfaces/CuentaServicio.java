package ws.servicios.interfaces;

import ws.dto.CambioPasswordDTO;
import ws.dto.SessionDTO;

public interface CuentaServicio {

    void iniciarSesion(SessionDTO SessionDTO) throws Exception;

    String enviarCodigoRecuperacion(String email) throws Exception;

    void cambiarPassword(CambioPasswordDTO cambioPassword) throws Exception;

}
