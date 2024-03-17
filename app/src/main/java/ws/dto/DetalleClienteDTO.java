package ws.dto;

import org.hibernate.validator.constraints.Length;
import java.util.ArrayList;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record DetalleClienteDTO(

        @NotBlank  String codigo,
        @NotBlank @Length(max = 50) String nombre,
        @NotBlank @Email String email,
        @NotBlank String fotoPerfil,
        @NotBlank String nickname,
        @NotBlank String ciudad,
        @NotEmpty ArrayList<String> telefonos) {
} 
