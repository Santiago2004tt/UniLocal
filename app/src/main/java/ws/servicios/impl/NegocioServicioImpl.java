package ws.servicios.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import ws.dto.ActualizarNegocioDTO;
import ws.dto.DetalleNegocioDTO;
import ws.dto.ItemNegocioDTO;
import ws.dto.RegistrarNegocioDTO;
import ws.model.documentos.Negocio;
import ws.model.entidades.Ubicacion;
import ws.model.enums.EstadoNegocio;
import ws.model.enums.TipoNegocio;
import ws.repositorio.NegocioRepo;
import ws.servicios.interfaces.ClienteServicio;
import ws.servicios.interfaces.NegocioServicio;

@Service
@RequiredArgsConstructor
@Transactional
public class NegocioServicioImpl implements NegocioServicio {

    private final NegocioRepo negocioRepo;

    @Autowired
    private final ClienteServicio clienteServicio;

    @Override
    public void crearNegocio(RegistrarNegocioDTO registroNegocioDTO) throws Exception {
        
        clienteServicio.obtenerCliente(registroNegocioDTO.codigoUsuario());

        Negocio negocio = new Negocio();
        negocio.setUbicacion(registroNegocioDTO.ubicacion());
        negocio.setCodigoCliente(registroNegocioDTO.codigoUsuario());
        negocio.setNombre(registroNegocioDTO.nombre());
        negocio.setDescripcion(registroNegocioDTO.descripcion());
        negocio.setHorarios(registroNegocioDTO.horarios());
        negocio.setImagenes(registroNegocioDTO.imagenes());
        negocio.setTipoNegocio(registroNegocioDTO.tipoNegocio());
        negocio.setTelefonos(registroNegocioDTO.telefonos());
        negocio.setEstadoNegocio(EstadoNegocio.PENDIENTE);


        negocioRepo.save(negocio);
    }

    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarNegocio'");
    }

    @Override
    public void eliminarNegocio(String codigoNegocio) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarNegocio'");
    }

    @Override
    public DetalleNegocioDTO buscarNegocio(String nombreNegocio) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarNegocio'");
    }

    @Override
    public DetalleNegocioDTO obtenerNegocio(String codigoNegocio) throws Exception {
        Optional<Negocio> negocioOptional = negocioRepo.findById(codigoNegocio);
        
        if(negocioOptional.isEmpty()){
            throw new Exception("No se a encontrado el negocio");
        }
        Negocio negocio = negocioOptional.get();

        return new DetalleNegocioDTO(negocio.getCodigo(), negocio.getCodigoCliente(),negocio.getUbicacion(), negocio.getNombre(), negocio.getDescripcion(), negocio.getHorarios(), negocio.getImagenes(), negocio.getTipoNegocio(), negocio.getHistorialRevisiones(),negocio.getTelefonos());
    }

    @Override
    public void listarNegocioPropietario(String codigoCliente) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarNegocioPropietario'");
    }

    @Override
    public void cambiarEstado(EstadoNegocio estadoNegocio) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cambiarEstado'");
    }

    @Override
    public List<ItemNegocioDTO> recomendarLugares(String codigoUsuario) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recomendarLugares'");
    }

    @Override
    public List<ItemNegocioDTO> filtrarNegociosCategoria(TipoNegocio tipoNegocio, Ubicacion ubicacion)
            throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filtrarNegociosCategoria'");
    }

    @Override
    public List<ItemNegocioDTO> filtrarPopularidad(int popularidad) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filtrarPopularidad'");
    }

    @Override
    public List<ItemNegocioDTO> filtrarDistancia(Ubicacion ubicacion) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filtrarDistancia'");
    }

    @Override
    public List<ItemNegocioDTO> listarNegocios() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarNegocios'");
    }

    @Override
    public List<ItemNegocioDTO> listarNegociosPropios(String codigoCliente) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarNegociosPropios'");
    }

    @Override
    public List<ItemNegocioDTO> listarPeticiones() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarPeticiones'");
    }

    @Override
    public int calcularPuntuacion(String codigoNegocio, int puntuacion) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularPuntuacion'");
    }

    @Override
    public String verificarAbierto(String codigoNegocio) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verificarAbierto'");
    }

    @Override
    public void finalizarTiempoEspera() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finalizarTiempoEspera'");
    }

    @Override
    public void generarHistorial(String codigoNegocio) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generarHistorial'");
    }

    @Override
    public List<ItemNegocioDTO> listarNegociosFavoritos(String codigoCliente) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarNegociosFavoritos'");
    }


}
