package ws.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrarComentarioDTO(

        @NotNull int calificacion,
        @NotBlank String mensaje) {

}
