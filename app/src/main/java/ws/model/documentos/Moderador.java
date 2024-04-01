package ws.model.documentos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ws.model.enums.EstadoRegistro;

@Document("Moderadores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Moderador {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String nombre;
    private String password;
    private String email;
    private EstadoRegistro estadoRegistro;
}
