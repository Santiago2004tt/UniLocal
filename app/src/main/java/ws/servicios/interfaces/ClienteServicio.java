package ws.servicios.interfaces;

import java.util.List;

import ws.dto.ActualizarClienteDTO;
import ws.dto.DetalleClienteDTO;
import ws.dto.ItemClienteDTO;
import ws.dto.RegistroClienteDTO;


public interface ClienteServicio extends CuentaServicio{

    void registrarse(RegistroClienteDTO registroClienteDTO) throws Exception;
    void editarPerfil(ActualizarClienteDTO actualizarClienteDTO) throws Exception;
    DetalleClienteDTO obtenerCliente(String codigo) throws Exception;
    void eliminarCliente(String codigo) throws Exception;
    List<ItemClienteDTO> listarClientes();

}
