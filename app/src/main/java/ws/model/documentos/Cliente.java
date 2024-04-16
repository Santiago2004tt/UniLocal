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
import ws.model.entidades.Bloqueo;
import ws.model.enums.EstadoRegistro;

@Document("Clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    
    private String nombre;
    private String password;
    private String email;
    private String fotoPerfil;
    private String nickname;
    private String ciudad;
    private EstadoRegistro estadoRegistro;
    private String codigoRecuperacion;
    private ArrayList<String> telefonos = new ArrayList<>();
    private ArrayList<Bloqueo> bloqueos = new ArrayList<>();
    private ArrayList<String> favortios = new ArrayList<>();
    private ArrayList<String> historial = new ArrayList<>();

}
