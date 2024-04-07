package ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ws.dto.ActualizarComentarioDTO;
import ws.dto.ItemComentarioDTO;
import ws.dto.RegistrarComentarioDTO;
import ws.repositorio.ComentarioRepo;
import ws.servicios.interfaces.ComentarioServicio;

@SpringBootTest
public class ComentarioTest {

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private ComentarioServicio comentarioServicioImpl;

    @Test
    public void listarComentarios() throws Exception{
        
        List<ItemComentarioDTO> lista= comentarioServicioImpl.listarComentarios("6612191a5f70451d310f368d");
        
        assertEquals(lista.size(), 1);
    }
    
    @Test
    public void agregarComentario(){
        RegistrarComentarioDTO registrarComentarioDTO= new RegistrarComentarioDTO(4, "Hola", "Cliente1", "6612191a5f70451d310f368d");
        try{
            comentarioServicioImpl.crearComentario(registrarComentarioDTO);
        }catch(Exception e){
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void agregarComentarioErrorExistencia(){
        RegistrarComentarioDTO comentarioDTO = new RegistrarComentarioDTO(4, "Hola", "ClienteX", "Negocio1");

        try{
            comentarioServicioImpl.crearComentario(comentarioDTO);
            fail();
        }catch(Exception e){
            assertEquals(e.getMessage(), "El codigo no se encuentra registrado");
        }

    }

    @Test
    public void responderComentarioOtro(){        
        ActualizarComentarioDTO actualizarComentarioDTO = new ActualizarComentarioDTO("comentario1", "Cliente2", "Gracias");
        
        try{
            comentarioServicioImpl.responderComentario(actualizarComentarioDTO);
            fail();
        }catch(Exception e){
            System.out.println(e.getMessage());
            assertEquals(e.getMessage(), "El comentario solo puede ser responddido por el dueño de la publicacion");
        }

    }

}
