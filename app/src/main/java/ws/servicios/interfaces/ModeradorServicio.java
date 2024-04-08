package ws.servicios.interfaces;

import ws.dto.DetalleModerador;

public interface ModeradorServicio extends CuentaServicio {

    void aceptarPeticionNegocio(String codigoNegocio, String codigo);

    void rechazarPeticionNegocio(String codigoNegocio, String codigo);

    void aceptarPeticionComentario(String codigoNegocio, String codigo);

    void rechazarPeticionComentario(String codigoNegocio, String codigo);


    DetalleModerador obtenerModerador(String codigoModerador);
    
}
