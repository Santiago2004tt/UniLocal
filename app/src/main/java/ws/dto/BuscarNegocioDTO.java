package ws.dto;

import ws.model.enums.TipoNegocio;

public record BuscarNegocioDTO(

    String nombre,
    TipoNegocio tipoNegocio

) {
} 
