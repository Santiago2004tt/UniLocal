package ws.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrarBloqueo(

        @NotNull LocalDate fechaInicio,
        @NotNull LocalDate fechaFinal,
        @NotBlank String motivo) {

}
