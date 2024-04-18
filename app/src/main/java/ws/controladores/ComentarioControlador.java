package ws.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ws.dto.ActualizarComentarioDTO;
import ws.dto.DetalleComentarioDTO;
import ws.dto.RegistrarComentarioDTO;
import ws.servicios.interfaces.ComentarioServicio;
import ws.dto.ItemComentarioDTO;
import ws.dto.MensajeDTO;

@RestController
@RequestMapping("/api/comentarios")
@RequiredArgsConstructor
public class ComentarioControlador {

    private final ComentarioServicio comentarioServicio;

    @PostMapping("/crear-comentario")
    public ResponseEntity<MensajeDTO<String>> crearComentario(@Valid @RequestBody RegistrarComentarioDTO registrarComentarioDTO)throws Exception{
        comentarioServicio.crearComentario(registrarComentarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario registrado correctamente"));
    }
    
    @PutMapping("/eliminar-comentario")
    public ResponseEntity<MensajeDTO<String>> eliminarComentario(@Valid @RequestBody String codigoComentario) throws Exception{
        comentarioServicio.eliminarComentario(codigoComentario);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario eliminado correctamente"));
    }

    @PutMapping("/responder-comentario")
    public ResponseEntity<MensajeDTO<String>> responderComentario(@Valid @RequestBody ActualizarComentarioDTO actualizarComentarioDTO) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.responderComentario(actualizarComentarioDTO)));
    }

    @GetMapping("/listar-comentarios")
    public ResponseEntity<MensajeDTO<List<ItemComentarioDTO>>> listarComentarios(@Valid @RequestBody String codigoNegocio)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.listarComentarios(codigoNegocio)));
    }

    @GetMapping("/obtener-comentario/{codigoComentario}")
    public ResponseEntity<MensajeDTO<DetalleComentarioDTO>> obtenerComentario(@PathVariable("codigoComentario") String codigoComentario)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.obtenerComentario(codigoComentario)));

    }

    @GetMapping("/calcular-puntuacion")
    public ResponseEntity<MensajeDTO<Integer>> calcularPuntuacion(@Valid @RequestBody String codigoNegocio)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.calcularPuntuacion(codigoNegocio)));
    }
}
