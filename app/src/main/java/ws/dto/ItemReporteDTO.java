package ws.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;

public record ItemReporteDTO(
    @NotBlank String codigo,
    @NotBlank LocalDateTime horaInicio,
    @NotBlank String mensaje
) {
}