package ws.dto;


public record MensajeDTO<T>(
boolean error,
T respuesta
) {
}