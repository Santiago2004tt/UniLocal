package ws.servicios.impl;
import lombok.RequiredArgsConstructor;
import ws.dto.SessionDTO;
import ws.dto.TokenDTO;
import ws.model.documentos.Cliente;
import ws.repositorio.ClienteRepo;
import ws.servicios.interfaces.AutenticacionServicio;
import ws.utils.JWTUtils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {

    private final ClienteRepo clienteRepo;
    private final JWTUtils jwtUtils;
    @Override
    public TokenDTO iniciarSesionCliente(SessionDTO sessionDTO) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findByEmail(sessionDTO.email());
        if (clienteOptional.isEmpty()) {
            throw new Exception("El correo no se encuentra registrado");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Cliente cliente = clienteOptional.get();
        if( !passwordEncoder.matches(sessionDTO.password(), cliente.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }
            
        Map<String, Object> map = new HashMap<>();
        map.put("rol", "CLIENTE");
        map.put("nombre", cliente.getNombre());
        map.put("id", cliente.getCodigo());
        return new TokenDTO( jwtUtils.generarToken(cliente.getEmail(), map) );
    }
    
}