package ws.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ws.model.documentos.Comentario;

@Repository
public interface ComentarioRepo extends MongoRepository<Comentario, String> {

    List<Comentario> findByCodigoNegocio(String codigoNegocios);

}
