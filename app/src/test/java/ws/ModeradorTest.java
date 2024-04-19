package ws;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ws.model.documentos.Moderador;
import ws.model.enums.EstadoRegistro;
import ws.repositorio.ModeradorRepo;

@SpringBootTest
public class ModeradorTest {

    @Autowired
    ModeradorRepo moderadorRepo;

    @Test
    public void crearModerador(){
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode( "password");
        Moderador moderador = new Moderador();
        moderador.setCodigo("Moderador1");
        moderador.setEmail("moderador@gmail.com");
        moderador.setEstadoRegistro(EstadoRegistro.ACTIVO);
        moderador.setNombre("Jere");
        moderador.setPassword(passwordEncriptada);

        moderadorRepo.save(moderador);
    }
}
