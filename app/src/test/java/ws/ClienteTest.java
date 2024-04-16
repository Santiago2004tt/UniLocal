package ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import ws.dto.ActualizarClienteDTO;
import ws.dto.RegistrarClienteDTO;
import ws.dto.SessionDTO;
import ws.model.documentos.Cliente;
import ws.model.entidades.Bloqueo;
import ws.model.enums.EstadoRegistro;
import ws.repositorio.ClienteRepo;
import ws.servicios.interfaces.ClienteServicio;

@SpringBootTest
public class ClienteTest {

    @Autowired
    private ClienteRepo clienteRepo;
    
    @Autowired
    private ClienteServicio clienteServicioImpl;
    


    @Test
    public void crearCliente(){
        ArrayList<String> telefonos = new ArrayList<>();
        telefonos.add("3134125124");
        telefonos.add("3154115134");
        RegistrarClienteDTO registrarClienteDTO = new RegistrarClienteDTO("Pepe", "pepito", "santisbb2004@gmail.com", "mi foto", "pepito", "armenia", telefonos);
        try{
            clienteServicioImpl.registrarse(registrarClienteDTO);
            
        }catch(Exception e){
            fail(e);
        }
    }

    @Test
    public void actualizarCliente() {
        

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
    
        clienteServicioImpl.eliminarCliente("Cliente1");
        Optional<Cliente> clienteOptional = clienteRepo.findById("Cliente1");
        Cliente cliente = clienteOptional.get();
        assertEquals(cliente.getEstadoRegistro(), EstadoRegistro.INACTIVO);
    }


    @Test
    public void guardarHistorial() throws Exception{
        
        for(int i = 0; i<16; i++){
            clienteServicioImpl.guardarHistorial("Cliente1", i+"");
        }
        clienteServicioImpl.eliminarCliente("Cliente1");
        Optional<Cliente> clienteOptional = clienteRepo.findById("Cliente1");
        Cliente cliente = clienteOptional.get();

        assertEquals(cliente.getHistorial().size(), 10);
    }

    @Test
    public void verificarBloqueoActual() throws Exception{

        Bloqueo bloqueo = new Bloqueo();
        LocalDateTime fechaInicio =  LocalDateTime.of(2024, 4, 2, 15, 30, 0);
        LocalDateTime fechaFinal =  LocalDateTime.of(2024, 4, 7, 15, 30, 0);
        bloqueo.setFechaInicio(fechaInicio);
        bloqueo.setFechaFinal(fechaFinal);
        bloqueo.setCodigoModerador("moderador1");
        bloqueo.setMotivo("Hablar de mala manera");
    
        Optional<Cliente> clienteOptional = clienteRepo.findById("Cliente1");
        Cliente cliente = clienteOptional.get();
        cliente.getBloqueos().add(bloqueo);
        clienteRepo.save(cliente);

        boolean bloqueado = clienteServicioImpl.verificarBloqueo("Cliente1");
        
        assertEquals(bloqueado, true);
    }

    @Test
    public void verificarBloqueoSinBloqueos() throws Exception{

        boolean bloqueado=clienteServicioImpl.verificarBloqueo("Cliente3");

        assertEquals(bloqueado, false);
    }

    @Test
    public void enviarCodigoRecuperacion(){
        try{
            String numero = clienteServicioImpl.enviarCodigoRecuperacion("santisbb2004@gmail.com");
            Optional<Cliente> clienteOptional = clienteRepo.findByEmail("santisbb2004@gmail.com");
            Cliente cliente = clienteOptional.get();
            assertEquals(numero, cliente.getCodigoRecuperacion());
        }catch(Exception e){
            fail(e);
        }
    }

    @Test
    public void iniciarSesion(){
        try{
            SessionDTO sessionDTO = new SessionDTO("santisbb2004@gmail.com", "pepito");
            clienteServicioImpl.iniciarSesion(sessionDTO);
        }catch(Exception e){
            fail(e);
        }
    }

    @Test
    public void iniciarSesionFallido(){
        try{
            SessionDTO sessionDTO = new SessionDTO("santisbb2004@gmail.com", "pepitoo");
            clienteServicioImpl.iniciarSesion(sessionDTO);
            fail();
        }catch(Exception e){
            assertEquals(e.getMessage(), "La contraseña es incorrecta");
        }
    }
}
