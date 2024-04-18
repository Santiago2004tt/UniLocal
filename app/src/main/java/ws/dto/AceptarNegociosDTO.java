package ws.dto;

import jakarta.validation.constraints.NotBlank;

public record AceptarNegociosDTO(
    @NotBlank String codigoNegocio,
    @NotBlank String codigoModerador
) {


}
