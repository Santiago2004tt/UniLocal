package ws.dto;

import jakarta.validation.constraints.NotBlank;

public record ItemComentarioDTO(

    @NotBlank String codigo,
    @NotBlank int calificacion,
    @NotBlank String mensaje
) {

}
