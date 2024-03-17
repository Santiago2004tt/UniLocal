package ws.dto;

import jakarta.validation.constraints.NotBlank;

public record CambioPassword(

    @NotBlank String codigoCuenta,
    @NotBlank String passwordNueva,
    @NotBlank String codigoRecuperacion
) {

}
