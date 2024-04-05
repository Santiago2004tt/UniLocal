package ws.dto;

import jakarta.validation.constraints.NotBlank;

public record ActualizarComentarioDTO(

    @NotBlank String codigo,
    @NotBlank String codigoCliente,
    @NotBlank String respuesta

) {
}