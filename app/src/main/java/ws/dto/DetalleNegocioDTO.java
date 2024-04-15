package ws.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ws.model.entidades.HistorialRevision;
import ws.model.entidades.Horario;
import ws.model.entidades.Ubicacion;
import ws.model.enums.TipoNegocio;

public record DetalleNegocioDTO(

        @NotBlank String codigoNegocio,
        @NotBlank String codigoCliente,
        @NotNull Ubicacion ubicacion,
        @NotBlank @Length(max = 50) String nombre,
        @NotBlank String descripcion,
        @NotEmpty ArrayList<Horario> horarios,
        @NotEmpty ArrayList<String> imagenes,
        @NotNull TipoNegocio tipoNegocio,
        List<HistorialRevision> historialRevisiones,
        @NotEmpty ArrayList<String> telefonos,
        @NotEmpty int puntuacion

) {

}
