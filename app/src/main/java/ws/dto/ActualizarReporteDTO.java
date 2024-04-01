package ws.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public record ActualizarReporteDTO(

    @NotBlank String codigo,
    @NotBlank LocalDateTime horaInicio,
    @NotBlank String mensaje,
    @NotBlank String codigoComentario
) {

}
