package ws.servicios.interfaces;

import ws.dto.CambioPasswordDTO;


public interface CuentaServicio {

    String enviarCodigoRecuperacion(String email) throws Exception;

    void cambiarPassword(CambioPasswordDTO cambioPassword) throws Exception;

}
