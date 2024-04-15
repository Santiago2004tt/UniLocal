package ws.servicios.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ws.dto.ActualizarNegocioDTO;
import ws.dto.CambioPasswordDTO;
import ws.dto.DetalleClienteDTO;
import ws.dto.DetalleComentarioDTO;
import ws.dto.DetalleModerador;
import ws.dto.DetalleNegocioDTO;
import ws.dto.RegistrarBloqueoDTO;
import ws.dto.SessionDTO;
import ws.model.entidades.Bloqueo;
import ws.model.entidades.HistorialRevision;
import ws.model.enums.EstadoNegocio;
import ws.model.enums.EstadoReporte;
import ws.repositorio.ModeradorRepo;
import ws.servicios.interfaces.ClienteServicio;
import ws.servicios.interfaces.ComentarioServicio;
import ws.servicios.interfaces.ModeradorServicio;
import ws.servicios.interfaces.NegocioServicio;
import ws.servicios.interfaces.ReporteServicio;
import ws.model.documentos.Moderador;

@Service
@RequiredArgsConstructor
@Transactional
public class ModeradorServicioImpl implements ModeradorServicio {

    private final ModeradorRepo moderadorRepo;

    @Autowired
    private final ClienteServicio clienteServicio;

    @Autowired
    private final ComentarioServicio comentarioServicio;

    @Autowired
    private final NegocioServicio negocioServicio;

    @Autowired
    private final ReporteServicio reporteServicio;
    
    @Override
    public void iniciarSesion(SessionDTO SessionDTO) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iniciarSesion'");
    }

    @Override
    public String enviarCodigoRecuperacion(String email) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enviarCodigoRecuperacion'");
    }

    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPassword) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cambiarPassword'");
    }

    @Override
    public void aceptarPeticionNegocio(String codigoNegocio, String codigo)throws Exception {
        
        DetalleNegocioDTO detalleNegocioDTO = negocioServicio.obtenerNegocio(codigoNegocio);
        HistorialRevision historialRevision = new HistorialRevision();
        historialRevision.setDescripcion("Aceptada");
        historialRevision.setEstadoNegocio(EstadoNegocio.APROBADO);
        historialRevision.setFecha(LocalDateTime.now());
        historialRevision.getCodigoModerador().add(codigo);
        List<HistorialRevision> historial = detalleNegocioDTO.historialRevisiones();
        historial.add(historialRevision);
        ActualizarNegocioDTO actualizarNegocioDTO = new ActualizarNegocioDTO(codigoNegocio, detalleNegocioDTO.ubicacion(), detalleNegocioDTO.codigoCliente(), detalleNegocioDTO.nombre(), detalleNegocioDTO.descripcion(), detalleNegocioDTO.horarios(), detalleNegocioDTO.imagenes(), detalleNegocioDTO.tipoNegocio(), detalleNegocioDTO.telefonos(), historialRevision.getEstadoNegocio(), historial);
        negocioServicio.actualizarNegocio(actualizarNegocioDTO);

    }

    @Override
    public void rechazarPeticionNegocio(String codigoNegocio, String codigo, String mensaje)throws Exception {
        DetalleNegocioDTO detalleNegocioDTO = negocioServicio.obtenerNegocio(codigoNegocio);
        HistorialRevision historialRevision = new HistorialRevision();
        historialRevision.setDescripcion("mensaje");
        historialRevision.setEstadoNegocio(EstadoNegocio.RECHAZADO);
        historialRevision.setFecha(LocalDateTime.now());
        historialRevision.getCodigoModerador().add(codigo);
        List<HistorialRevision> historial = detalleNegocioDTO.historialRevisiones();
        historial.add(historialRevision);
        ActualizarNegocioDTO actualizarNegocioDTO = new ActualizarNegocioDTO(codigoNegocio, detalleNegocioDTO.ubicacion(), detalleNegocioDTO.codigoCliente(), detalleNegocioDTO.nombre(), detalleNegocioDTO.descripcion(), detalleNegocioDTO.horarios(), detalleNegocioDTO.imagenes(), detalleNegocioDTO.tipoNegocio(), detalleNegocioDTO.telefonos(), historialRevision.getEstadoNegocio(), historial);
        negocioServicio.actualizarNegocio(actualizarNegocioDTO);
}

    @Override
    public void aceptarPeticionComentario(RegistrarBloqueoDTO registrarBloqueoDTO )throws Exception {
        DetalleComentarioDTO detalleComentarioDTO = comentarioServicio.obtenerComentario(registrarBloqueoDTO.codigoComentario());
        DetalleClienteDTO detalleClienteDTO = clienteServicio.obtenerCliente(detalleComentarioDTO.codigoCliente());
        Bloqueo bloqueo = new Bloqueo();
        bloqueo.setCodigoModerador(registrarBloqueoDTO.codigoModerado());
        bloqueo.setFechaInicio(registrarBloqueoDTO.fechaInicio());
        bloqueo.setMotivo(registrarBloqueoDTO.motivo());
        bloqueo.setFechaFinal(registrarBloqueoDTO.fechaFinal());
        
        
        reporteServicio.cambiarEstado(registrarBloqueoDTO.codigoReporte(), EstadoReporte.APROBADO);


        clienteServicio.agregarBloqueo(detalleClienteDTO.codigo(), bloqueo);
    }

    @Override
    public void rechazarPeticionComentario(String codigoComentario, String codigo)throws Exception {

        reporteServicio.cambiarEstado(codigo, EstadoReporte.RECHAZADO);
    }

    @Override
    public DetalleModerador obtenerModerador(String codigoModerador)throws Exception {
        Optional<Moderador> moderadorOptional = moderadorRepo.findById(codigoModerador);
        
        if(moderadorOptional.isEmpty()){
            throw new Exception("El moderador no existe");
        }

        Moderador moderador = moderadorOptional.get();
        
        return new DetalleModerador(moderador.getCodigo(), moderador.getEmail(), moderador.getNombre());
    }

}