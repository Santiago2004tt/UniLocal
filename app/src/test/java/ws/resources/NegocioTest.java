package ws.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ws.dto.DetalleNegocioDTO;
import ws.dto.RegistrarNegocioDTO;
import ws.model.entidades.Horario;
import ws.model.entidades.Ubicacion;
import ws.model.enums.TipoNegocio;
import ws.repositorio.NegocioRepo;
import ws.servicios.interfaces.NegocioServicio;

@SpringBootTest
public class NegocioTest {

    @Autowired
    NegocioRepo negocioRepo;

    @Autowired
    NegocioServicio negocioServicio;


    @Test
    public void crearNegocio(){
        Ubicacion ubicacion = new Ubicacion(20, 40);
        Horario horario = new Horario(LocalTime.now(), LocalTime.now(), "Lunes");
        ArrayList<Horario> arrayList = new ArrayList<>();
        arrayList.add(horario);
        ArrayList<String> fotos = new ArrayList<>();
        fotos.add("Foto");
        ArrayList<String> telefonos = new ArrayList<>();
        telefonos.add("313141254");
        RegistrarNegocioDTO registrarNegocioDTO = new RegistrarNegocioDTO(ubicacion, "Cliente1", "Las salchipapas", "Ricas", arrayList, fotos, TipoNegocio.CAFETERIA, telefonos);

        try{
            negocioServicio.crearNegocio(registrarNegocioDTO);
        }catch(Exception e){
            assertEquals(e.getMessage(), "hola");
        }
    }


    @Test
    public void buscarNegocio(){
        try{
            DetalleNegocioDTO detalleNegocioDTO = negocioServicio.obtenerNegocio("6612191a5f70451d310f368d");
            assertEquals(detalleNegocioDTO.nombre(), "Las salchipapas");
        }catch(Exception e){
            fail();
        }
    }

}
