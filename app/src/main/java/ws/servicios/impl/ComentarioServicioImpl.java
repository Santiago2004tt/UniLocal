package ws.servicios.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ws.dto.ActualizarComentarioDTO;
import ws.dto.DetalleComentarioDTO;
import ws.dto.ItemComentarioDTO;
import ws.dto.RegistrarComentarioDTO;
import ws.model.documentos.Comentario;
import ws.model.enums.EstadoComentario;
import ws.repositorio.ComentarioRepo;
import ws.servicios.interfaces.ClienteServicio;
import ws.servicios.interfaces.ComentarioServicio;

@Service
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio{
    private final ComentarioRepo comentarioRepo;
    private final ClienteServicio clienteServicio;


    /**
     * Metodo que registra un comentario
     */
    @Override
    public void crearComentario(RegistrarComentarioDTO registrarComentarioDTO) throws Exception {
        Comentario comentario = new Comentario();

        clienteServicio.obtenerCliente(registrarComentarioDTO.codigoCliente());

        comentario.setCalificacion(registrarComentarioDTO.calificacion());
        comentario.setCodigoCliente(registrarComentarioDTO.codigoCliente());
        comentario.setMensaje(registrarComentarioDTO.mensaje());
        comentario.setCodigoNegocio(registrarComentarioDTO.codigoNegocio());
        comentario.setFecha(LocalDateTime.now());
        comentario.setEstadoComentario(EstadoComentario.ACTIVO);
        comentario.setRespuesta("");

        comentarioRepo.save(comentario);
        
    }


    @Override
    public void eliminarComentario(String codigoComentario) throws Exception {
        Optional<Comentario> comentarioOptional = comentarioRepo.findById(codigoComentario);
        Comentario comentario = comentarioOptional.get();

        comentario.setEstadoComentario(EstadoComentario.OCULTO);

        comentarioRepo.save(comentario);
    }

    @Override
    public String responderComentario(ActualizarComentarioDTO actualizarComentarioDTO) throws Exception {
        Optional<Comentario> comentarioOptional = comentarioRepo.findById(actualizarComentarioDTO.codigo());
        Comentario comentario = comentarioOptional.get();
        comentario.setRespuesta(actualizarComentarioDTO.respuesta());

        comentarioRepo.save(comentario);

        return actualizarComentarioDTO.respuesta();
    }

    @Override
    public List<ItemComentarioDTO> listarComentarios(String codigoNegocio) throws Exception {
        List<ItemComentarioDTO> listaComentario = new ArrayList<>();
        List<Comentario> listaComentarioOptional;
        listaComentarioOptional = comentarioRepo.findByCodigoNegocio(codigoNegocio);

        for (int i = 0; i < listaComentarioOptional.size(); i++) {
            Comentario comentarioAux = listaComentarioOptional.get(i);
            listaComentario.add(new ItemComentarioDTO(comentarioAux.getCodigo(), comentarioAux.getCalificacion(), comentarioAux.getMensaje()));
        }

        return listaComentario;
    }

    @Override
    public DetalleComentarioDTO obtenerComentario(String codigoComentario) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerComentario'");
    }
}
