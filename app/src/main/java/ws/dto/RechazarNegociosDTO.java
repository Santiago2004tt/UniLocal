package ws.dto;

import jakarta.validation.constraints.NotBlank;

public record RechazarNegociosDTO(
    @NotBlank String codigoNegocio,
    @NotBlank String codigoModerador,
    @NotBlank String mensaje
) {
}
