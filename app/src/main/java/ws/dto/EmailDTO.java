package ws.dto;

import jakarta.validation.constraints.NotBlank;

public record EmailDTO(
    @NotBlank String asunto,
    @NotBlank String cuerpo,
    @NotBlank String destinario
) {
} 