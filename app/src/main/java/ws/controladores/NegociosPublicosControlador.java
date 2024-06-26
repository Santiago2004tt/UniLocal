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
import ws.dto.BuscarNegocioDTO;
import ws.dto.DetalleNegocioDTO;
import ws.dto.ItemNegocioDTO;
import ws.dto.MensajeDTO;
import ws.model.entidades.Ubicacion;
import ws.servicios.interfaces.NegocioServicio;

@RestController
@RequestMapping("/api/negocios-publicos")
@RequiredArgsConstructor
public class NegociosPublicosControlador {

    private final NegocioServicio negocioServicio;
    
    @GetMapping("/ordenar-ubicacion")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> ordenarUbicacion(@Valid @RequestBody Ubicacion ubicacion, List<ItemNegocioDTO> listaNegocios)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.ordenarUbicacion(ubicacion, listaNegocios)));
    }

    @GetMapping("/ordenar-popularidad")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> ordenarPuntuacion(@Valid @RequestBody List<ItemNegocioDTO> listaNegocios)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.ordenarPuntuacion(listaNegocios)));
    }

    @GetMapping("/obtener-negocio-activo/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<DetalleNegocioDTO>> obtenerNegocioActivo(@PathVariable("codigoNegocio") String codigoNegocio) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.obtenerNegocioActivo(codigoNegocio)));
    }

    @PostMapping("/buscar-negocio")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> buscarNegocio(@Valid @RequestBody BuscarNegocioDTO buscarNegocioDTO) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.buscarNegocio(buscarNegocioDTO)));
    }

    @GetMapping("/verificar-abierto/{codigoNegocio}")
    public ResponseEntity<MensajeDTO<Boolean>> verificarAbierto(@PathVariable("codigoNegocio") String codigoNegocio)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.verificarAbierto(codigoNegocio)));
    }

    @PutMapping("/finalizar-tiempo-espera")
    public ResponseEntity<MensajeDTO<String>> finalizarTiempoEspera()throws Exception{
        negocioServicio.finalizarTiempoEspera();
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio finalizado"));
    }
}
