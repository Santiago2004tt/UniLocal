package ws.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ws.dto.DetalleModerador;
import ws.dto.MensajeDTO;
import ws.dto.RegistrarBloqueoDTO;
import ws.servicios.interfaces.ModeradorServicio;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ModeradorControlador {

    private final ModeradorServicio moderadorServicio;

    @PutMapping("/aceptar-peticion-negocio")
    public ResponseEntity<MensajeDTO<String>> aceptarPeticionNegocio(@Valid @RequestBody String codigoNegocio, String codigo)throws Exception{
        moderadorServicio.aceptarPeticionNegocio(codigoNegocio,codigo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio aceptado"));
    }

    @PutMapping("/rechazar-peticion-negocio")
    public ResponseEntity<MensajeDTO<String>> rechazarPeticionNegocio(@Valid @RequestBody String codigoNegocio, String codigo, String mensaje)throws Exception{
        moderadorServicio.rechazarPeticionNegocio(codigoNegocio, codigo, mensaje);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio rechazado"));
    }

    @PutMapping("/aceptar-peticion-comentario")
    public ResponseEntity<MensajeDTO<String>> aceptarPeticionComentario(@Valid @RequestBody RegistrarBloqueoDTO registrarBloqueoDTO )throws Exception{
        moderadorServicio.aceptarPeticionComentario(registrarBloqueoDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario reportado"));
    }

    @PutMapping("/rechazar-peticion-comentario")
    public ResponseEntity<MensajeDTO<String>> rechazarPeticionComentario(@Valid @RequestBody String codigoNegocio, String codigo)throws Exception{
        moderadorServicio.rechazarPeticionComentario(codigoNegocio, codigo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario rechazado"));
    }

    @GetMapping("/obtener-moderador")
    public ResponseEntity<MensajeDTO<DetalleModerador>> obtenerModerador(@Valid @RequestBody String codigoModerador)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, moderadorServicio.obtenerModerador(codigoModerador) ));
    }
}
