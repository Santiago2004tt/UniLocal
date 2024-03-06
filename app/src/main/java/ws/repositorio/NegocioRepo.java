package ws.repositorio;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import ws.model.documentos.Negocio;

@Document
public interface NegocioRepo extends MongoRepository<Negocio, String> {

}
