package ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ws.dto.DetalleNegocioDTO;
import ws.dto.ItemNegocioDTO;
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
        Ubicacion ubicacion = new Ubicacion(56, 31);
        Horario horario = new Horario(LocalTime.now(), LocalTime.now(), "Lunes");
        ArrayList<Horario> arrayList = new ArrayList<>();
        arrayList.add(horario);
        ArrayList<String> fotos = new ArrayList<>();
        fotos.add("Foto");
        ArrayList<String> telefonos = new ArrayList<>();
        telefonos.add("313141254");
        RegistrarNegocioDTO registrarNegocioDTO = new RegistrarNegocioDTO(ubicacion, "Cliente2", "Paramericana", "Vengan", arrayList, fotos, TipoNegocio.SUPERMERCADO, telefonos);

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

    @Test
    public void listarNegociosNombre(){
        try{
            List<ItemNegocioDTO> lista = negocioServicio.buscarNegocioNombre("Paramericana");
            assertEquals(lista.get(0).codigo(), "6612e9096923bd62d2d50936");
        }catch(Exception e){
            fail();
        }
    }

}
