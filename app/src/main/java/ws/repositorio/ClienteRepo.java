package ws.repositorio;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import ws.model.documentos.Cliente;
import java.util.List;

@Repository
public interface ClienteRepo extends MongoRepository<Cliente, String> {

    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByNickname(String nickname);

}
