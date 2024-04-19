package ws.controladores;

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
import ws.dto.AceptarNegociosDTO;
import ws.dto.DetalleModerador;
import ws.dto.MensajeDTO;
import ws.dto.RechazarComentarioDTO;
import ws.dto.RechazarNegociosDTO;
import ws.dto.RegistrarBloqueoDTO;
import ws.servicios.interfaces.ClienteServicio;
import ws.servicios.interfaces.ModeradorServicio;

@RestController
@RequestMapping("/api/moderadores")
@RequiredArgsConstructor
public class ModeradorControlador {

    private final ModeradorServicio moderadorServicio;
    private final ClienteServicio clienteServicio;

    @PutMapping("/aceptar-peticion-negocio")
    public ResponseEntity<MensajeDTO<String>> aceptarPeticionNegocio(@Valid @RequestBody AceptarNegociosDTO aceptarNegociosDTO)throws Exception{
        moderadorServicio.aceptarPeticionNegocio(aceptarNegociosDTO.codigoNegocio(),aceptarNegociosDTO.codigoModerador());
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio aceptado"));
    }

    @PutMapping("/rechazar-peticion-negocio")
    public ResponseEntity<MensajeDTO<String>> rechazarPeticionNegocio(@Valid @RequestBody RechazarNegociosDTO rechazarNegociosDTO)throws Exception{
        moderadorServicio.rechazarPeticionNegocio(rechazarNegociosDTO.codigoNegocio(), rechazarNegociosDTO.codigoModerador(), rechazarNegociosDTO.mensaje());
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio rechazado"));
    }

    @PutMapping("/aceptar-peticion-comentario")
    public ResponseEntity<MensajeDTO<String>> aceptarPeticionComentario(@Valid @RequestBody RegistrarBloqueoDTO registrarBloqueoDTO )throws Exception{
        moderadorServicio.aceptarPeticionComentario(registrarBloqueoDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario reportado"));
    }

    @PutMapping("/rechazar-peticion-comentario")
    public ResponseEntity<MensajeDTO<String>> rechazarPeticionComentario(@Valid @RequestBody RechazarComentarioDTO rechazarComentarioDTO)throws Exception{
        moderadorServicio.rechazarPeticionComentario(rechazarComentarioDTO.codigoComentario(), rechazarComentarioDTO.codigoModerador());
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario rechazado"));
    }

    @GetMapping("/obtener-moderador/{codigoModerador}")
    public ResponseEntity<MensajeDTO<DetalleModerador>> obtenerModerador(@PathVariable("codigoModerador") String codigoModerador)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, moderadorServicio.obtenerModerador(codigoModerador) ));
    }

    
}
