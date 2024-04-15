package ws.servicios.interfaces;

import ws.dto.ActualizarReporteDTO;
import ws.dto.DetalleReporteDTO;
import ws.dto.ItemReporteDTO;
import ws.dto.RegistrarReporteDTO;
import ws.model.enums.EstadoReporte;

import java.util.List;

public interface ReporteServicio {
    
    void generarReporte(RegistrarReporteDTO reporteDTO);

    void actualizarReporte(ActualizarReporteDTO actualizarReporteDTO);
    
    void cambiarEstado(String codigoReporte, EstadoReporte estadoReporte);

    DetalleReporteDTO obtenerReporte(String codigoReporte);

    List<ItemReporteDTO> listarReportes();

}   
