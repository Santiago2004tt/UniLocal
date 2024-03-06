package ws.model.entidades;

import java.time.LocalDateTime;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Horario {
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private String dia;
}
