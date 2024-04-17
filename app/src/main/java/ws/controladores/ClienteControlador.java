package ws.controladores;

import lombok.RequiredArgsConstructor;
import ws.dto.ActualizarClienteDTO;
import ws.dto.DetalleClienteDTO;
import ws.dto.RegistrarClienteDTO;
import ws.model.entidades.Bloqueo;
import ws.servicios.interfaces.ClienteServicio;
import ws.dto.MensajeDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/registrar-cliente")
    public ResponseEntity<MensajeDTO<String>> registrarse(@Valid @RequestBody RegistrarClienteDTO registroClienteDTO) throws Exception{
        clienteServicio.registrarse(registroClienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cliente registrado correctamente"));
    }

    @PutMapping("/actualizar-perfil")
    public ResponseEntity<MensajeDTO<String>> actualizarPerfil(@Valid @RequestBody ActualizarClienteDTO actualizarClienteDTO) throws Exception{
        clienteServicio.actualizarPerfil(actualizarClienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Su cuenta se actualizo"));
    }
    
    @GetMapping("/obtener-cliente/{codigo}")
    public ResponseEntity<MensajeDTO<DetalleClienteDTO>> obtenerCliente(@Valid @RequestBody String codigoCliente) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.obtenerCliente(codigoCliente) ) );
    }
    
    @PutMapping("/eliminar-perfil")
    public ResponseEntity<MensajeDTO<String>> eliminarCliente(@Valid @RequestBody String codigoCliente) throws Exception{
        clienteServicio.eliminarCliente(codigoCliente);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se elimino su cuenta con exito"));
    }
    
    @PostMapping("/agregar-bloqueo")
    public ResponseEntity<MensajeDTO<String>> agregarBloqueo(@Valid @RequestBody String codigoCliente, Bloqueo bloqueo)throws Exception{
        clienteServicio.agregarBloqueo(codigoCliente, bloqueo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se agrego un bloqueo a la cuenta"));
    }
    
    @PostMapping("/guardar-favorito")
    public ResponseEntity<MensajeDTO<String>> guardarFavorito(@Valid @RequestBody String codigoCliente, String codigoNegocio)throws Exception{
        clienteServicio.guardarFavorito(codigoCliente, codigoNegocio);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se guardo en favoritos"));
    }
    
    @PutMapping("/quitar-favorito")
    public ResponseEntity<MensajeDTO<String>> quitarFavorito(@Valid @RequestBody String codigoCliente, String codigoNegocio)throws Exception{
        clienteServicio.quitarFavorito(codigoCliente, codigoNegocio);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se elimino de favoritos"));
    }

    @PostMapping("/guardar-historial")
    public ResponseEntity<MensajeDTO<String>> guardarHistorial(@Valid @RequestBody String codigoCliente, String codigoNegocio)throws Exception{
        clienteServicio.guardarHistorial(codigoCliente, codigoNegocio);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Se agrego correctamente al historial"));
    }

    @GetMapping("/verificar-bloqueo/{codigo}")
    public ResponseEntity<MensajeDTO<Boolean>> verificarBloqueo(@Valid @RequestBody String codigoCliente)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.verificarBloqueo(codigoCliente) ) );
    }

}
