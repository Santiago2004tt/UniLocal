package ws.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

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

    @Query("{$or:[{'nombre': ?0}, {'tipoNegocio':?1}], $and:[{'estadoNegocio':'APROBADO'}]}")
    List<Negocio> findBusiness(String nombre, TipoNegocio tipoNegocio);

    @Query("{$and:[{'codigo': ?0}, {'estadoNegocio':'PENDIENTE'}]}")
    Optional<Negocio> findByNegocioModerador(String codigoNegocio);

    @Query("{or:[{'nombre':?0}, {'tipoNegocio': ?1},{'nombre':?2}, {'tipoNegocio':?3} ,{'nombre':?4} ]}")
    List<Negocio> findByRecomendar(String nombre, TipoNegocio tipoNegocio, String nombre1, TipoNegocio tipoNegocio1,
            String nombre2);
}
