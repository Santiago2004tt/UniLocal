package ws.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ws.model.documentos.Reporte;

@Repository
public interface ReporteRepo extends MongoRepository<Reporte, String> {

    List<Reporte> findByCodigoComentario(String codigoComentario);

}
