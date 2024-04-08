package ws.servicios.interfaces;

import java.util.List;

import ws.dto.ActualizarNegocioDTO;
import ws.dto.BuscarNegocioDTO;
import ws.dto.DetalleNegocioDTO;
import ws.dto.ItemNegocioDTO;
import ws.dto.RegistrarNegocioDTO;
import ws.model.entidades.Ubicacion;

public interface NegocioServicio {

    void crearNegocio(RegistrarNegocioDTO registroNegocioDTO)throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO)throws Exception;

    void eliminarNegocio(String codigoNegocio)throws Exception;

    List<ItemNegocioDTO> recomendarLugares(String codigoUsuario, Ubicacion ubicacion)throws Exception;

    DetalleNegocioDTO obtenerNegocio(String codigoNegocio)throws Exception; 

    DetalleNegocioDTO obtenerNegocioActivo(String codigoNegocio) throws Exception;

    List<ItemNegocioDTO> listarNegocioPropietario(String codigoCliente)throws Exception;

    List<ItemNegocioDTO> buscarNegocio(BuscarNegocioDTO buscarNegocioDTO) throws Exception;

    List<ItemNegocioDTO> listarPeticiones()throws Exception;

    boolean verificarAbierto(String codigoNegocio)throws Exception;

    void finalizarTiempoEspera()throws Exception;

    List<ItemNegocioDTO> listarHistorial(String codigoCliente, Ubicacion ubicacion) throws Exception;

    List<ItemNegocioDTO> listarNegociosFavoritos(String codigoCliente, Ubicacion ubicacion)throws Exception;

    void agregarPuntuacion(String codigoNegocio, int puntuacion);

    List<ItemNegocioDTO> ordenarUbicacion(List<ItemNegocioDTO> listaNegocios);

    List<ItemNegocioDTO> ordenarPuntuacion(List<ItemNegocioDTO> listaNegocios);
}
