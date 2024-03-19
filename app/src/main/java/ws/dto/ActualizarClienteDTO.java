package ws.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record ActualizarClienteDTO(

            @NotBlank String codigo,
            @NotBlank @Length(max = 50) String nombre,
            @NotBlank String fotoPerfil,
            @NotBlank String nickname,
            @NotBlank String ciudad) {
}
