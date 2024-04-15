package ws.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ws.model.documentos.Moderador;

@Repository
public interface ModeradorRepo extends MongoRepository<Moderador, String> {

}
