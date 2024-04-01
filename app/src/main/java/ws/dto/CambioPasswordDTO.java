package ws.dto;

import jakarta.validation.constraints.NotBlank;

public record CambioPasswordDTO(

    @NotBlank String codigoCuenta,
    @NotBlank String passwordNueva,
    @NotBlank String codigoRecuperacion
) {

}
