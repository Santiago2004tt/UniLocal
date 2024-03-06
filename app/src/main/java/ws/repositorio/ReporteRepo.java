package ws.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ws.model.documentos.Reporte;

@Repository
public interface ReporteRepo extends MongoRepository<Reporte, String> {

}
