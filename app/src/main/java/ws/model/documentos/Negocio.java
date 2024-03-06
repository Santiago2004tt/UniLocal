package ws.model.documentos;

import java.util.ArrayList;

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
import ws.model.enums.EstadoRegistro;
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

    private Ubicacion Ubicacion;
    private String nombre;
    private String descripcion;
    private ArrayList<Horario> horarios = new ArrayList<>();
    private EstadoRegistro estadoRegistro;
    private ArrayList<String> imagenes = new ArrayList<>();
    private ArrayList<HistorialRevision> historialRevisiones = new ArrayList<>();
    private String codigoCliente;
    private TipoNegocio tipoNegocio;
    private ArrayList<String> telefonos;
}
