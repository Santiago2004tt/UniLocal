package ws;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import com.mongodb.assertions.Assertions;
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
    public void registrarCliente() {

        Cliente cliente = new Cliente("1", "Juan", "123", "juan@", "null", "juanito", "Armenia", EstadoRegistro.ACTIVO,
                new ArrayList<String>(), new ArrayList<Bloqueo>(), new ArrayList<String>(), null);

        Cliente registro = clienteRepo.save(cliente);

        Assertions.assertNotNull(registro);
    }

    @Test
    public void actualizarCliente() {

        Cliente cliente = new Cliente("1", "Juan", "123", "juan@", "null", "juanito", "Armenia", EstadoRegistro.ACTIVO,
                new ArrayList<String>(), new ArrayList<Bloqueo>(), new ArrayList<String>(), null);

        cliente.setNombre("Jere");
        Cliente registro = clienteRepo.save(cliente);

        Assertions.assertNotNull(registro);
    }

    @Test
    public void registrarClienteVacio() throws Exception {

        Cliente cliente = new Cliente();
        cliente.setCodigo("65e7d15e5795ba394144e1a7");
        cliente.setNombre("Pedro");

        clienteRepo.deleteById(cliente.getCodigo());

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
