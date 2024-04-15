package ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ws.dto.BuscarNegocioDTO;
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
    public void crearNegocioFallo(){
        Ubicacion ubicacion = new Ubicacion(56, 31);
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        Horario horario = new Horario(LocalTime.now().getHour(), 7,  day);
        ArrayList<Horario> arrayList = new ArrayList<>();
        arrayList.add(horario);
        ArrayList<String> fotos = new ArrayList<>();
        fotos.add("Foto");
        ArrayList<String> telefonos = new ArrayList<>();
        telefonos.add("313141254");
        RegistrarNegocioDTO registrarNegocioDTO = new RegistrarNegocioDTO(ubicacion, "Cliente1", "Almuadas", "Vengan", arrayList, fotos, TipoNegocio.SUPERMERCADO, telefonos);

        try{
            negocioServicio.crearNegocio(registrarNegocioDTO);
        }catch(Exception e){

            assertEquals(e.getMessage(),"El codigo no se encuentra registrado" );
        }
    }


    @Test
    public void buscarNegocio(){
        try{
            DetalleNegocioDTO detalleNegocioDTO = negocioServicio.obtenerNegocio("Negocio1");
            assertEquals(detalleNegocioDTO.nombre(), "Las salchipapas");
        }catch(Exception e){
            fail();
        }
    }


    @Test
    public void buscarNegocioNew(){
        BuscarNegocioDTO buscarNegocioDTO = new BuscarNegocioDTO(null, TipoNegocio.CAFETERIA);

        try{
            List<ItemNegocioDTO> lista = negocioServicio.buscarNegocio(buscarNegocioDTO);
            assertEquals(lista.get(0).codigo(), "Negocio2");
        }catch(Exception e){
            fail(e);
        }
    }

}
