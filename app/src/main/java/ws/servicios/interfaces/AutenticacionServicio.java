package ws.servicios.interfaces;

import ws.dto.SessionDTO;
import ws.dto.TokenDTO;

public interface AutenticacionServicio {

    public TokenDTO iniciarSesionCliente(SessionDTO sessionDTO) throws Exception;

    public TokenDTO iniciarSesionModerador(SessionDTO sessionDTO) throws Exception;
    
}
