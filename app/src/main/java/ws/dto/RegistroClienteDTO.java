package ws.dto;

import java.util.ArrayList;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record RegistroClienteDTO(

        @NotBlank @Length(max = 50) String nombre,
        @NotBlank @Length(min = 8) String password,
        @NotBlank @Email String email,
        @NotBlank String fotoPerfil,
        @NotBlank String nickname,
        @NotBlank String ciudad,
        @NotEmpty ArrayList<String> telefonos) {

}
