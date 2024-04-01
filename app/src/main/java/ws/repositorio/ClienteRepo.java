package ws.repositorio;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ws.model.documentos.Cliente;


@Repository
public interface ClienteRepo extends MongoRepository<Cliente, String> {

    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByNickname(String nickname);

}
