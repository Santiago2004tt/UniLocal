package ws.model.entidades;

import java.time.DayOfWeek;



import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Horario {
    private int horaInicio;
    private int horaFin;
    private DayOfWeek dia;
}
