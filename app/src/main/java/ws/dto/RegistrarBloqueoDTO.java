package ws.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrarBloqueoDTO(

        @NotNull LocalDateTime fechaInicio,
        @NotNull LocalDateTime fechaFinal,
        @NotBlank String motivo,
        @NotBlank String codigoComentario,
        @NotBlank String codigoCliente,
        @NotBlank String codigoModerador,
        @NotBlank String codigoReporte
        ) {

}
