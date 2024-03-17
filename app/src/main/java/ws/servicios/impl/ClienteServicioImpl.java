package ws.servicios.impl;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import ws.dto.ActualizarClienteDTO;
import ws.dto.CambioPassword;
import ws.dto.DetalleClienteDTO;
import ws.dto.ItemClienteDTO;
import ws.dto.RegistroClienteDTO;
import ws.dto.SessionDTO;
import ws.model.documentos.Cliente;
import ws.model.enums.EstadoRegistro;
import ws.repositorio.ClienteRepo;
import ws.servicios.interfaces.ClienteServicio;

@Service
@Transactional
public class ClienteServicioImpl implements ClienteServicio{

    private final ClienteRepo clienteRepo;
    
    public ClienteServicioImpl(ClienteRepo clienteRepo){
        this.clienteRepo = clienteRepo;
    }

    @Override
    public void iniciarSesion(SessionDTO sessionDTO) throws Exception {
        
    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enviarLinkRecuperacion'");
    }

    @Override
    public void cambiarPassword(CambioPassword cambioPassword) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cambiarPassword'");
    }

    @Override
    public void registrarse(RegistroClienteDTO registroClienteDTO) throws Exception {
        
        if(verificarEmailExistente(registroClienteDTO.email())){
            throw new Exception("El email ya se encuentra registrado");
        }

        if(verificarNickNameExiste(registroClienteDTO.nickname())){
            throw new Exception("El nickname ya se encuentra registrado");
        }
        Cliente cliente = new Cliente();

        cliente.setNombre(registroClienteDTO.nombre());
        cliente.setNickname(registroClienteDTO.nickname());
        cliente.setCiudad(registroClienteDTO.ciudad());
        cliente.setFotoPerfil(registroClienteDTO.fotoPerfil());

        cliente.setEmail(registroClienteDTO.email());
        cliente.setPassword(registroClienteDTO.password());
        cliente.setEstadoRegistro(EstadoRegistro.ACTIVO);

    }

    private boolean verificarNickNameExiste(@NotBlank String nickname) {
        return clienteRepo.findByNickname(nickname).isPresent();
    }

    private boolean verificarEmailExistente(@NotBlank @Email String email) {
        
        return clienteRepo.findByEmail(email).isPresent();
    }

    @Override
    public void editarPerfil(ActualizarClienteDTO actualizarClienteDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editarPerfil'");
    }

    @Override
    public DetalleClienteDTO obtenerCliente(String codigo) throws Exception {
        Optional<Cliente> optionalCLiente = clienteRepo.findById(codigo);

        if(optionalCLiente.isEmpty()){
            throw new Exception("El codigo no se encuentra registrado");
        }

        Cliente cliente = optionalCLiente.get();

        return new DetalleClienteDTO(cliente.getNombre(), cliente.getPassword(), cliente.getEmail(), cliente.getFotoPerfil(),cliente.getNickname() , cliente.getCiudad(), cliente.getTelefonos());
    }

    @Override
    public void eliminarCliente(String codigo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarCliente'");
    }

    @Override
    public List<ItemClienteDTO> listarClientes() {
        List<Cliente> clientes = clienteRepo.findAll();

        List<ItemClienteDTO> items = new ArrayList<>();

        for (Cliente cliente: clientes){
            items.add(new ItemClienteDTO(cliente.getCodigo(), cliente.getNombre(), cliente.getFotoPerfil(), cliente.getNickname(), cliente.getCiudad()));

        }
        return items;    
    }

}
