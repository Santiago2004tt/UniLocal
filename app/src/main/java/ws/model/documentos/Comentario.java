package ws.model.documentos;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ws.model.enums.EstadoComentario;

@Document("Comentarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private LocalDateTime fecha;
    private int calificacion;
    private String codigoCliente;
    private String codigoNegocio;
    private String mensaje;
    private String respuesta;
    private EstadoComentario estadoComentario;

}
