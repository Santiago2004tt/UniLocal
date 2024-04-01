package ws.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public record RegistrarReporteDTO(

    @NotBlank LocalDateTime horaInicio,
    @NotBlank String mensaje,
    @NotBlank String codigoComentario

) {

}
