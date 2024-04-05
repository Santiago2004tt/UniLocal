package ws.dto;

import jakarta.validation.constraints.NotBlank;

public record DetalleComentarioDTO(

    @NotBlank String codigo,
    @NotBlank int calificacion,
    @NotBlank String codigoCliente,
    @NotBlank String codigoNegocio,
    @NotBlank String mensaje,
    @NotBlank String respuesta

) {

}
