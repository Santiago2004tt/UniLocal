package ws.dto;

import jakarta.validation.constraints.NotNull;
import ws.model.entidades.Ubicacion;
import ws.model.enums.TipoNegocio;

public record BuscarNegocioDTO(

    String nombre,
    TipoNegocio tipoNegocio,
    int popularidad,
    @NotNull Ubicacion ubicacion
) {
} 
