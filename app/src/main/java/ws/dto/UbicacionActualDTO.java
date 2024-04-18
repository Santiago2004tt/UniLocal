package ws.dto;

import jakarta.validation.constraints.NotBlank;
import ws.model.entidades.Ubicacion;

public record UbicacionActualDTO(
    @NotBlank String codigoUsuario,
    @NotBlank Ubicacion ubicacion
) {
} 
