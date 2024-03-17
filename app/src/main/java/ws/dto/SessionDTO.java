package ws.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SessionDTO(

        @NotBlank @Email String email,
        @NotBlank String password) {

}
