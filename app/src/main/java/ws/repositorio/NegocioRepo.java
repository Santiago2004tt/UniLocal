package ws.repositorio;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import ws.model.documentos.Negocio;
import ws.model.enums.EstadoNegocio;
import ws.model.enums.TipoNegocio;


@Document
public interface NegocioRepo extends MongoRepository<Negocio, String> {

    List<Negocio> findByNombre(String nombreNegocio);

    List<Negocio> findByCodigoCliente(String codigoCliente);

    List<Negocio> findByTipoNegocio(TipoNegocio tipoNegocio);

    List<Negocio> findByPopularidad(int popularidad);

    List<Negocio> findByEstadoNegocio(EstadoNegocio pendiente);
}
