package ws.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ws.dto.DetalleReporteDTO;
import ws.dto.ItemReporteDTO;
import ws.dto.MensajeDTO;
import ws.dto.RegistrarReporteDTO;
import ws.servicios.interfaces.ReporteServicio;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReportesControlador {

    private final ReporteServicio reporteServicio;

    @PostMapping("/registrar-reporte")
    public ResponseEntity<MensajeDTO<String>> generarReporte(@Valid @RequestBody RegistrarReporteDTO reporteDTO)throws Exception{
        reporteServicio.generarReporte(reporteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Reporte generado"));
    }

    @GetMapping("/obtener-reporte/{codigoReporte}")
    public ResponseEntity<MensajeDTO<DetalleReporteDTO>> obtenerReporte(@PathVariable("codigoReporte") String codigoReporte)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, reporteServicio.obtenerReporte(codigoReporte)));
    }

    @GetMapping("/listar-reportes")
    public ResponseEntity<MensajeDTO<List<ItemReporteDTO>>> listarReportes()throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, reporteServicio.listarReportes()));
    }
}
