package ws.dto;

import jakarta.validation.constraints.NotBlank;

public record DetalleModerador(
    @NotBlank String codigo,
    @NotBlank String email,
    @NotBlank String nombre
) {

}
