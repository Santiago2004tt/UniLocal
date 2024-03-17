package ws.servicios.interfaces;

import ws.dto.ActualizarNegocioDTO;
import ws.dto.RegistroNegocioDTO;
import ws.model.enums.EstadoNegocio;
import ws.model.enums.TipoNegocio;

public interface NegocioServicio {

    void crearNegocio(RegistroNegocioDTO registroNegocioDTO);

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO);

    void eliminarNegocio(String codigoNegocio);

    void buscarNegocio(String nombreNegocio);

    void filtrarPorEstado(TipoNegocio tipoNegocio);

    void listarNegocioPropietario(String codigoCliente);

    void cambiarEstado(EstadoNegocio estadoNegocio);
}
