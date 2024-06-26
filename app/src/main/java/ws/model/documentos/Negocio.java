package ws.model.documentos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ws.model.entidades.HistorialRevision;
import ws.model.entidades.Horario;
import ws.model.entidades.Ubicacion;
import ws.model.enums.EstadoNegocio;
import ws.model.enums.TipoNegocio;

@Document("Negocios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Negocio {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String nombre;
    private String descripcion;
    private Ubicacion Ubicacion;
    private String codigoCliente;
    private TipoNegocio tipoNegocio;
    private EstadoNegocio estadoNegocio;
    private ArrayList<String> telefonos;
    private List <HistorialRevision> historialRevisiones = new ArrayList<>();
    private ArrayList<Horario> horarios = new ArrayList<>();
    private ArrayList<String> imagenes = new ArrayList<>();
    private int popularidad;
}
