package ws.controladores;

import lombok.RequiredArgsConstructor;
import ws.dto.ActualizarClienteDTO;
import ws.dto.ClienteNegocioDTO;
import ws.dto.DetalleClienteDTO;
import ws.servicios.interfaces.ClienteServicio;
import ws.dto.MensajeDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteControlador {

    private final ClienteServicio clienteServicio;

    
    @PutMapping("/actualizar-perfil")
    public ResponseEntity<MensajeDTO<String>> actualizarPerfil(@Valid @RequestBody ActualizarClienteDTO actualizarClienteDTO) throws Exception{
        clienteServicio.actualizarPerfil(actualizarClienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Su cuenta se actualizo"));
    }
    
    @GetMapping("/obtener-cliente/{codigoCliente}")
    public ResponseEntity<MensajeDTO<DetalleClienteDTO>> obtenerCliente(@PathVariable("codigoCliente") String codigoCliente) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.obtenerCliente(codigoCliente) ) );
    }
    
    @PutMapping("/eliminar-perfil/{codigoCliente}")
    public ResponseEntity<MensajeDTO<String>> eliminarCliente(@PathVariable("codigoCliente") String codigoCliente) throws Exception{
        clienteServicio.eliminarCliente(codigoCliente);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se elimino su cuenta con exito"));
    }
    
    @PostMapping("/guardar-favorito")
    public ResponseEntity<MensajeDTO<String>> guardarFavorito(@Valid @RequestBody ClienteNegocioDTO clienteNegocioDTO)throws Exception{
        clienteServicio.guardarFavorito(clienteNegocioDTO.codigoCliente(), clienteNegocioDTO.codigoNegocio());
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se guardo en favoritos"));
    }
    
    @PutMapping("/quitar-favorito")
    public ResponseEntity<MensajeDTO<String>> quitarFavorito(@Valid @RequestBody ClienteNegocioDTO clienteNegocioDTO)throws Exception{
        clienteServicio.quitarFavorito(clienteNegocioDTO.codigoCliente(), clienteNegocioDTO.codigoNegocio());
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se elimino de favoritos"));
    }

    @PostMapping("/guardar-historial")
    public ResponseEntity<MensajeDTO<String>> guardarHistorial(@Valid @RequestBody ClienteNegocioDTO clienteNegocioDTO)throws Exception{
        clienteServicio.guardarHistorial(clienteNegocioDTO.codigoCliente(), clienteNegocioDTO.codigoNegocio());
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se agrego correctamente al historial"));
    }
    

}
