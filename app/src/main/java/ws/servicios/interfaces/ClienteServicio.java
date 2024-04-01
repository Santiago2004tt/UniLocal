package ws.servicios.interfaces;

import java.util.List;

import ws.dto.ActualizarClienteDTO;
import ws.dto.DetalleClienteDTO;
import ws.dto.ItemClienteDTO;
import ws.dto.RegistrarClienteDTO;


public interface ClienteServicio extends CuentaServicio{

    void registrarse(RegistrarClienteDTO registroClienteDTO) throws Exception;

    void actualizarPerfil(ActualizarClienteDTO actualizarClienteDTO) throws Exception;
    
    DetalleClienteDTO obtenerCliente(String codigoCliente) throws Exception;
    
    void eliminarCliente(String codigoCliente) throws Exception;
    
    List<ItemClienteDTO> listarClientes()throws Exception;
    
    void guardarFavorito(String codigoCliente, String codigoNegocio)throws Exception;
    
    void quitarFavorito(String codigoCliente, String codigoNegocio)throws Exception;

    boolean verificarBloqueo(String codigoCliente);
 
}
