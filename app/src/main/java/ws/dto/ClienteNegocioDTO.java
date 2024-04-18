package ws.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteNegocioDTO (
    @NotBlank String codigoCliente,
    @NotBlank String codigoNegocio
){

}
