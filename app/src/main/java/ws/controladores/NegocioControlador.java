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
import ws.dto.PopularidadDTO;
import ws.dto.RegistrarNegocioDTO;
import ws.dto.UbicacionActualDTO;
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

    @PostMapping("/recomendar-lugares")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> recomendarLugares(@Valid @RequestBody UbicacionActualDTO ubicacionActualDTO)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.recomendarLugares(ubicacionActualDTO.codigoUsuario(), ubicacionActualDTO.ubicacion())));
    }

    @GetMapping("/obtener-negocio/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<DetalleNegocioDTO>> obtenerNegocio(@PathVariable("codigoNegocio") String codigoNegocio)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.obtenerNegocio(codigoNegocio)));
    }


    @GetMapping("/listar-negocios-propietario/{codigoCliente}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> listarNegocioPropietario(@PathVariable("codigoCliente") String codigoCliente)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.listarNegocioPropietario(codigoCliente)));
    }

    @GetMapping("/listar-peticiones")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> listarPeticiones()throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.listarPeticiones()));
    }

    @PostMapping("/listar-negocios-favoritos")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> listarNegociosFavoritos(@Valid @RequestBody  UbicacionActualDTO ubicacionActualDTO)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.listarNegociosFavoritos(ubicacionActualDTO.codigoUsuario(), ubicacionActualDTO.ubicacion())));
    }

    @PutMapping("/agregar-puntuacion")
    public ResponseEntity<MensajeDTO<String>> agregarPuntuacion(@Valid @RequestBody PopularidadDTO popularidadDTO)throws Exception{
        negocioServicio.agregarPuntuacion(popularidadDTO.codigoNegocio(), popularidadDTO.puntuacion());
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Puntuacion agregada"));
    }

}
