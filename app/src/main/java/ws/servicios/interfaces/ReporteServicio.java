package ws.servicios.interfaces;

import ws.dto.DetalleReporteDTO;
import ws.dto.ItemReporteDTO;
import ws.dto.RegistrarReporteDTO;
import ws.model.enums.EstadoReporte;

import java.util.List;

public interface ReporteServicio {
    
    void generarReporte(RegistrarReporteDTO reporteDTO)throws Exception;
    
    void cambiarEstado(String codigoReporte, EstadoReporte estadoReporte)throws Exception;

    DetalleReporteDTO obtenerReporte(String codigoReporte)throws Exception;

    List<ItemReporteDTO> listarReportes()throws Exception;

}   
