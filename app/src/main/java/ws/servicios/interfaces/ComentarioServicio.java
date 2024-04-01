package ws.servicios.interfaces;

import java.util.List;
import ws.dto.ActualizarComentarioDTO;
import ws.dto.DetalleComentarioDTO;
import ws.dto.ItemComentarioDTO;
import ws.dto.RegistrarComentarioDTO;

public interface ComentarioServicio {

    void crearComentario(RegistrarComentarioDTO RegistrarComentarioDTO)throws Exception;
    
    void eliminarComentario(String codigoComentario) throws Exception;

    String responderComentario(ActualizarComentarioDTO actualizarComentarioDTO) throws Exception;

    List<ItemComentarioDTO> listarComentarios(String codigoNegocio)throws Exception;

    DetalleComentarioDTO obtenerComentario(String CodigoComentario)throws Exception;
}
