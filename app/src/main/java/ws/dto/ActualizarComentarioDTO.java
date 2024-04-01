package ws.dto;

import jakarta.validation.constraints.NotBlank;

public record ActualizarComentarioDTO(

    @NotBlank String codigo,
    @NotBlank String respuesta

) {
}