package ws.model.entidades;

import java.time.LocalDateTime;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Bloqueo {

    private String codigo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinal;
    private String codigoModerador;
    private String motivo;
}
