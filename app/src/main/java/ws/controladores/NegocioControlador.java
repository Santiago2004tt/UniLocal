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
import ws.dto.ActualizarNegocioDTO;
import ws.dto.DetalleNegocioDTO;
import ws.dto.ItemNegocioDTO;
import ws.dto.MensajeDTO;
import ws.dto.RegistrarNegocioDTO;
import ws.model.entidades.Ubicacion;
import ws.servicios.interfaces.NegocioServicio;

@RestController
@RequestMapping("/api/negocios-privados")
@RequiredArgsConstructor
public class NegocioControlador {

    private final NegocioServicio negocioServicio;

    @PostMapping("/crear-negocio")
    public  ResponseEntity<MensajeDTO<String>> crearNegocio(@Valid @RequestBody RegistrarNegocioDTO registroNegocioDTO)throws Exception{
        negocioServicio.crearNegocio(registroNegocioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio registrado correctamente"));
    }

    @PutMapping("/actualizar-negocio")
    public ResponseEntity<MensajeDTO<String>> actualizarNegocio(@Valid @RequestBody ActualizarNegocioDTO actualizarNegocioDTO)throws Exception{
        negocioServicio.actualizarNegocio(actualizarNegocioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio actualizado"));
    }

    @PutMapping("/eliminar-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<String>> eliminarNegocio(@PathVariable("codigoNegocio") String codigoNegocio)throws Exception{
        negocioServicio.eliminarNegocio(codigoNegocio);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio eliminado"));
    }

    @GetMapping("/recomendar-lugares")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> recomendarLugares(@Valid @RequestBody String codigoUsuario, Ubicacion ubicacion)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.recomendarLugares(codigoUsuario, ubicacion)));
    }

    @GetMapping("/obtener-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<DetalleNegocioDTO>> obtenerNegocio(@PathVariable("codigoNegocio") String codigoNegocio)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.obtenerNegocio(codigoNegocio)));
    }


    @GetMapping("/listar-negocios-propietario")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> listarNegocioPropietario(@Valid @RequestBody String codigoCliente)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.listarNegocioPropietario(codigoCliente)));
    }

    @GetMapping("/listar-peticiones")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> listarPeticiones()throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.listarPeticiones()));
    }

    @PutMapping("/finalizar-tiempo-espera")
    public ResponseEntity<MensajeDTO<String>> finalizarTiempoEspera()throws Exception{
        negocioServicio.finalizarTiempoEspera();
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio finalizado"));
    }

    @GetMapping("/listar-negocios-favoritos")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> listarNegociosFavoritos(@Valid @RequestBody String codigoCliente, Ubicacion ubicacion)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.listarNegociosFavoritos(codigoCliente, ubicacion)));
    }

    @PutMapping("/agregar-puntuacion")
    public ResponseEntity<MensajeDTO<String>> agregarPuntuacion(@Valid @RequestBody String codigoNegocio, int puntuacion)throws Exception{
        negocioServicio.agregarPuntuacion(codigoNegocio, puntuacion);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Puntuacion agregada"));
    }

}
