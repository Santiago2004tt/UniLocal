package ws.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ws.model.documentos.Cliente;

@Repository
public interface ClienteRepo extends MongoRepository<Cliente, String> {

}
