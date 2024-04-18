package ws.dto;

import jakarta.validation.constraints.NotBlank;

public record PopularidadDTO(
    @NotBlank String codigoNegocio,
    @NotBlank int puntuacion 
) {
}
