package ws.servicios.interfaces;

import java.util.List;
import ws.dto.ItemModeradorDTO;
import ws.dto.DetalleModerador;

public interface ModeradorServicio extends CuentaServicio {

    void aceptarPeticion(String codigoNegocio, String codigo);

    void rechazarPeticion(String codigoNegocio, String codigo);

    DetalleModerador obtenerModerador(String codigoModerador);

    List<ItemModeradorDTO> listarModeradores();
    
}
