package ws.servicios.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import ws.dto.ActualizarComentarioDTO;
import ws.dto.DetalleComentarioDTO;
import ws.dto.DetalleNegocioDTO;
import ws.dto.ItemComentarioDTO;
import ws.dto.RegistrarComentarioDTO;
import ws.model.documentos.Comentario;
import ws.model.enums.EstadoComentario;
import ws.repositorio.ComentarioRepo;
import ws.servicios.interfaces.ClienteServicio;
import ws.servicios.interfaces.ComentarioServicio;
import ws.servicios.interfaces.NegocioServicio;

@Service
@RequiredArgsConstructor
@Transactional
public class ComentarioServicioImpl implements ComentarioServicio{
    private final ComentarioRepo comentarioRepo;

    @Autowired
    private final ClienteServicio clienteServicio;
    @Autowired
    private final NegocioServicio negocioServicio;
    
    

    /**
     * Metodo que registra un comentario
     */
    @Override
    public void crearComentario(RegistrarComentarioDTO registrarComentarioDTO) throws Exception {
        Comentario comentario = new Comentario();

        clienteServicio.obtenerCliente(registrarComentarioDTO.codigoCliente());
        
        negocioServicio.obtenerNegocio(registrarComentarioDTO.codigoNegocio());

        if(registrarComentarioDTO.calificacion()>5 || registrarComentarioDTO.calificacion()<0){
            throw new Exception("La calificacion debe ser entre 0 a 5");
        }
        
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

        obtenerComentario(codigoComentario);

        Optional<Comentario> comentarioOptional = comentarioRepo.findById(codigoComentario);
        Comentario comentario = comentarioOptional.get();

        comentario.setEstadoComentario(EstadoComentario.OCULTO);

        comentarioRepo.save(comentario);
    }

    @Override
    public String responderComentario(ActualizarComentarioDTO actualizarComentarioDTO) throws Exception {

        clienteServicio.obtenerCliente(actualizarComentarioDTO.codigoCliente());

        Optional<Comentario> comentarioOptional = comentarioRepo.findById(actualizarComentarioDTO.codigo());
        
        if(comentarioOptional.isEmpty()){
            throw new Exception("El comentario no existe");
        }
        Comentario comentario = comentarioOptional.get();

        if(verificarPropietario(actualizarComentarioDTO, comentario.getCodigoNegocio())){
            comentario.setRespuesta(actualizarComentarioDTO.respuesta());
            
            comentarioRepo.save(comentario);
        }else{
            throw new Exception("El comentario solo puede ser responddido por el dueño de la publicacion");
        }
        

        return actualizarComentarioDTO.respuesta();
    }

    private boolean verificarPropietario(ActualizarComentarioDTO actualizarComentarioDTO, String codigoNegocio) throws Exception {
        DetalleNegocioDTO detalleNegocio = negocioServicio.obtenerNegocio(codigoNegocio);
        
        if(!detalleNegocio.codigoCliente().equals(actualizarComentarioDTO.codigoCliente())){
            return false;
        }
        return true;
    }


    @Override
    public List<ItemComentarioDTO> listarComentarios(String codigoNegocio) throws Exception {

        negocioServicio.obtenerNegocio(codigoNegocio);

        List<ItemComentarioDTO> listaComentario = new ArrayList<>();
        List<Comentario> listaComentarioOptional;
        listaComentarioOptional = comentarioRepo.findByCodigoNegocio(codigoNegocio);

        for (int i = 0; i < listaComentarioOptional.size(); i++) {
            Comentario comentarioAux = listaComentarioOptional.get(i);
            listaComentario.add(new ItemComentarioDTO(comentarioAux.getCodigo(), comentarioAux.getCalificacion(), comentarioAux.getMensaje(), comentarioAux.getRespuesta()));
        }

        return listaComentario;
    }

    @Override
    public DetalleComentarioDTO obtenerComentario(String codigoComentario) throws Exception {
        Optional<Comentario> comentarioOptional = comentarioRepo.findById(codigoComentario);

        if(comentarioOptional.isEmpty()){
            throw new Exception("El comentario no esta registrado");
        }
        Comentario comentario = comentarioOptional.get();

        return new DetalleComentarioDTO(comentario.getCodigo(), comentario.getCalificacion(), comentario.getCodigoCliente(), comentario.getCodigoNegocio(), comentario.getMensaje(), comentario.getRespuesta());
    }


    @Override
    public int calcularPuntuacion(String codigoNegocio) throws Exception {
        List<Comentario> comentarios = comentarioRepo.findByCodigoNegocio(codigoNegocio);
        
        int valor = calcularPromedio(comentarios);
        negocioServicio.agregarPuntuacion(codigoNegocio, valor);
        return valor;
    }


    private int calcularPromedio(List<Comentario> comentarios) {
        int suma = 0;
        for (int i = 0; i < comentarios.size(); i++) {
            suma += comentarios.get(i).getCalificacion();
        }
        suma = suma/comentarios.size();
        return suma;
    }
}
