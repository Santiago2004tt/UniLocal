package ws.servicios.interfaces;

import ws.dto.ActualizarClienteDTO;
import ws.dto.RegistroClienteDTO;

public interface ClienteServicio extends CuentaServicio{

    void Registrarse(RegistroClienteDTO registroClienteDTO);
    void editarPerfil(ActualizarClienteDTO actualizarClienteDTO);

}
