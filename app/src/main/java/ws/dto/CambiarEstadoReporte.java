package ws.dto;

import jakarta.validation.constraints.NotBlank;
import ws.model.enums.EstadoReporte;

public record CambiarEstadoReporte(
    @NotBlank String codigoReporte,
    @NotBlank EstadoReporte estadoReporte
) {
}
