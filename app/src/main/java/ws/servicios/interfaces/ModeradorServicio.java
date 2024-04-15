package ws.servicios.interfaces;

import ws.dto.DetalleModerador;
import ws.dto.RegistrarBloqueoDTO;

public interface ModeradorServicio extends CuentaServicio {

    void aceptarPeticionNegocio(String codigoNegocio, String codigo)throws Exception;

    void rechazarPeticionNegocio(String codigoNegocio, String codigo, String mensaje)throws Exception;

    void aceptarPeticionComentario(RegistrarBloqueoDTO registrarBloqueoDTO )throws Exception;

    void rechazarPeticionComentario(String codigoNegocio, String codigo)throws Exception;

    DetalleModerador obtenerModerador(String codigoModerador)throws Exception;
    
}
