package ws.dto;

import jakarta.validation.constraints.NotBlank;

public record RechazarComentarioDTO(
    @NotBlank String codigoComentario,
    @NotBlank String codigoModerador
) {
} 