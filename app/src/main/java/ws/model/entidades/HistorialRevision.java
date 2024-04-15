package ws.model.entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ws.model.enums.EstadoNegocio;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HistorialRevision {
    private String descripcion;
    private EstadoNegocio estadoNegocio;
    private LocalDateTime fecha;
    private List<String> codigoModerador = new ArrayList<>();
}
