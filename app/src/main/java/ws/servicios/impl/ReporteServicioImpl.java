package ws.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ws.dto.DetalleReporteDTO;
import ws.dto.ItemReporteDTO;
import ws.dto.RegistrarReporteDTO;
import ws.model.documentos.Reporte;
import ws.model.enums.EstadoReporte;
import ws.repositorio.ReporteRepo;
import ws.servicios.interfaces.ComentarioServicio;
import ws.servicios.interfaces.ReporteServicio;

@Service
@RequiredArgsConstructor
@Transactional
public class ReporteServicioImpl implements ReporteServicio{
    
    private final ReporteRepo reporteRepo;

    @Autowired
    private final ComentarioServicio comentarioServicio;

    
    @Override
    public void generarReporte(RegistrarReporteDTO reporteDTO) throws Exception{
        
        comentarioServicio.obtenerComentario(reporteDTO.codigoComentario());
        Reporte reporte = new Reporte();
        reporte.setCodigoComentario(reporteDTO.codigoComentario());
        reporte.setEstadoReporte(EstadoReporte.PENDIENTE);
        reporte.setHoraInicio(reporteDTO.horaInicio());
        reporte.setMensaje(reporteDTO.mensaje());

        reporteRepo.save(reporte);
    }

    @Override
    public void cambiarEstado(String codigoReporte, EstadoReporte estadoReporte)throws Exception {
        Optional <Reporte> reporteOptional = reporteRepo.findById(codigoReporte);

        if(reporteOptional.isEmpty()){
            throw new Exception("El reporte no se a encontrado");
        }

        Reporte reporte = reporteOptional.get();

        List<Reporte> reportes = reporteRepo.findByCodigoComentario(reporte.getCodigoComentario());

        for (int i = 0; i < reportes.size(); i++) {
            Reporte reporteAux = reportes.get(i);
            reporteAux.setEstadoReporte(estadoReporte);
            reporteRepo.save(reporteAux);
        }
    }

    @Override
    public DetalleReporteDTO obtenerReporte(String codigoReporte)throws Exception {
        Optional <Reporte> reporteOptional = reporteRepo.findById(codigoReporte);

        if(reporteOptional.isEmpty()){
            throw new Exception("El reporte no se a encontrado");
        }

        Reporte reporte = reporteOptional.get();

        return new DetalleReporteDTO(codigoReporte, reporte.getHoraInicio(), reporte.getMensaje(), reporte.getCodigoComentario());
    }

    @Override
    public List<ItemReporteDTO> listarReportes()throws Exception {
        List<Reporte> listarReportes = reporteRepo.findAll();
        List<ItemReporteDTO> itemsReporteDTO = new ArrayList<>();
        if(listarReportes.isEmpty()){
            throw new Exception("No se encontraron reportes");
        }
        for (int i = 0; i < listarReportes.size(); i++) {
            ItemReporteDTO itemReporteDTO = new ItemReporteDTO(listarReportes.get(i).getCodigo(), listarReportes.get(i).getHoraInicio(), listarReportes.get(i).getMensaje());
            itemsReporteDTO.add(itemReporteDTO);
        }

        return itemsReporteDTO;
    }

}
