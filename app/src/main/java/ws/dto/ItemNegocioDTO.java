package ws.dto;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ws.model.entidades.Ubicacion;
import ws.model.enums.TipoNegocio;

public record ItemNegocioDTO(
    @NotBlank String codigo,
    @NotNull Ubicacion ubicacion,
    @NotBlank @Length(max = 50) String nombre,
    @NotBlank String descripcion,
    @NotBlank String fotoPrimera,
    @NotBlank TipoNegocio tipoNegocio,
    @NotBlank int puntuacion
) {
} 