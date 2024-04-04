package ws;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ws.dto.RegistrarComentarioDTO;
import ws.repositorio.ClienteRepo;
import ws.repositorio.ComentarioRepo;
import ws.servicios.impl.ComentarioServicioImpl;
import ws.servicios.interfaces.ComentarioServicio;

@SpringBootTest
public class ComentarioTest {

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Test
    public void crearComentario(){
        ComentarioServicioImpl comentarioServicioImpl = new ComentarioServicioImpl(comentarioRepo);
        RegistrarComentarioDTO comentarioDTO = new RegistrarComentarioDTO(4, "Hola", "Cliente1", "Negocio1");
        
    }

}
