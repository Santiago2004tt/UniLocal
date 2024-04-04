package ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import com.mongodb.assertions.Assertions;

import ws.dto.ActualizarClienteDTO;
import ws.dto.RegistrarClienteDTO;
import ws.model.documentos.Cliente;
import ws.model.entidades.Bloqueo;
import ws.model.enums.EstadoRegistro;
import ws.repositorio.ClienteRepo;
import ws.servicios.impl.ClienteServicioImpl;

@SpringBootTest
public class ClienteTest {

    @Autowired
    private ClienteRepo clienteRepo;

    

    @Test
    public void actualizarCliente() {
        ClienteServicioImpl clienteServicioImpl = new ClienteServicioImpl(clienteRepo);

        try{
        ActualizarClienteDTO actualizarClienteDTO = new ActualizarClienteDTO("Cliente3", "Jere", "mi foto", "Jeremias", "armenia");
        clienteServicioImpl.actualizarPerfil(actualizarClienteDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Optional<Cliente> clienteActualizadoOptional = clienteRepo.findById("Cliente3");
        Cliente clienteActualizado = clienteActualizadoOptional.get();
        
        assertEquals("Jere", clienteActualizado.getNombre());
    }

    @Test
    public void registrarClienteFallo() throws Exception {
        ClienteServicioImpl clienteServicioImpl = new ClienteServicioImpl(clienteRepo);
        ArrayList<String> telefonos = new ArrayList<>();
        telefonos.add("3134125124");
        telefonos.add("3154115134");
        RegistrarClienteDTO registrarClienteDTO = new RegistrarClienteDTO("Pepe", "pepito", "juan@email.com", "mi foto", "pepito", "armenia", telefonos);
        
        try{
            clienteServicioImpl.registrarse(registrarClienteDTO);
            fail("Se esperaba un error");
        }catch(Exception e){
            assertEquals("El email ya se encuentra registrado", e.getMessage());
        }
        
    }

    @Test
    public void eliminarCliente() throws Exception{
        ClienteServicioImpl clienteServicioImpl = new ClienteServicioImpl(clienteRepo);
    
        clienteServicioImpl.eliminarCliente("Cliente1");
        Optional<Cliente> clienteOptional = clienteRepo.findById("Cliente1");
        Cliente cliente = clienteOptional.get();
        assertEquals(cliente.getEstadoRegistro(), EstadoRegistro.INACTIVO);
    }
}
