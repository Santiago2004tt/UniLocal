package ws.servicios.interfaces;


import ws.dto.ActualizarClienteDTO;
import ws.dto.DetalleClienteDTO;
import ws.dto.RegistrarClienteDTO;
import ws.model.entidades.Bloqueo;


public interface ClienteServicio extends CuentaServicio{

    void registrarse(RegistrarClienteDTO registroClienteDTO) throws Exception;

    void actualizarPerfil(ActualizarClienteDTO actualizarClienteDTO) throws Exception;
    
    DetalleClienteDTO obtenerCliente(String codigoCliente) throws Exception;
    
    void eliminarCliente(String codigoCliente) throws Exception;
    
    void agregarBloqueo(String codigoCliente, Bloqueo bloqueo)throws Exception;
    
    void guardarFavorito(String codigoCliente, String codigoNegocio)throws Exception;
    
    void quitarFavorito(String codigoCliente, String codigoNegocio)throws Exception;

    void guardarHistorial(String codigoCliente, String codigoNegocio)throws Exception;

    boolean verificarBloqueo(String codigoCliente)throws Exception;
 
}
