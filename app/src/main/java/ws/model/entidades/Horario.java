package ws.model.entidades;

import java.time.LocalTime;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Horario {
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String dia;
}
