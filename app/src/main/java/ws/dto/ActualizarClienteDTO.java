package ws.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record ActualizarClienteDTO(


    @NotBlank @Length(max=50) String nombre,
    @NotBlank String fotoPerfil,
    @NotBlank String nickname,
    @NotBlank String ciudad

) {
} 
