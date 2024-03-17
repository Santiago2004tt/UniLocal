package ws.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrarComentario(

        @NotNull int calificacion,
        @NotBlank String mensaje) {

}
