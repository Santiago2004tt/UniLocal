package ws.servicios.interfaces;

import java.util.List;

import ws.dto.ActualizarNegocioDTO;
import ws.dto.DetalleNegocioDTO;
import ws.dto.ItemNegocioDTO;
import ws.dto.RegistrarNegocioDTO;
import ws.model.entidades.Ubicacion;
import ws.model.enums.EstadoNegocio;
import ws.model.enums.TipoNegocio;

public interface NegocioServicio {

    void crearNegocio(RegistrarNegocioDTO registroNegocioDTO)throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO)throws Exception;

    void eliminarNegocio(String codigoNegocio)throws Exception;

    List<ItemNegocioDTO> buscarNegocioNombre(String nombreNegocio, Ubicacion ubicacion)throws Exception;

    DetalleNegocioDTO obtenerNegocio(String codigoNegocio)throws Exception;

    List<ItemNegocioDTO> listarNegocioPropietario(String codigoCliente)throws Exception;

    void cambiarEstado(EstadoNegocio estadoNegocio, String codigoNegocio)throws Exception;

    List<ItemNegocioDTO> recomendarLugares(String codigoUsuario, Ubicacion ubicacion)throws Exception;

    List<ItemNegocioDTO> filtrarNegociosCategoria(TipoNegocio tipoNegocio, Ubicacion ubicacion)throws Exception;
    
    List<ItemNegocioDTO> filtrarPopularidad(int popularidad,Ubicacion ubicacion)throws Exception;
    
    List<ItemNegocioDTO> listarNegocios()throws Exception;

    List<ItemNegocioDTO> listarPeticiones()throws Exception;

    //int calcularPuntuacion(String codigoNegocio, int puntuacion)throws Exception; //comentario

    boolean verificarAbierto(String codigoNegocio)throws Exception;

    void finalizarTiempoEspera()throws Exception;

    void generarHistorial(String codigoNegocio) throws Exception;

    List<ItemNegocioDTO> listarNegociosFavoritos(String codigoCliente, Ubicacion ubicacion)throws Exception;
}
