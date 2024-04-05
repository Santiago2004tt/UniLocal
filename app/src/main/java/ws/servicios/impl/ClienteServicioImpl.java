package ws.servicios.impl;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import ws.dto.ActualizarClienteDTO;
import ws.dto.CambioPasswordDTO;
import ws.dto.DetalleClienteDTO;
import ws.dto.ItemClienteDTO;
import ws.dto.RegistrarClienteDTO;
import ws.dto.SessionDTO;
import ws.model.documentos.Cliente;
import ws.model.entidades.Bloqueo;
import ws.model.enums.EstadoRegistro;
import ws.repositorio.ClienteRepo;
import ws.servicios.interfaces.ClienteServicio;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteServicioImpl implements ClienteServicio{

    private final ClienteRepo clienteRepo; //El repositorio
    
    
    @Override
    public void iniciarSesion(SessionDTO sessionDTO) throws Exception {
        
    }

    @Override
    public String enviarCodigoRecuperacion(String email) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enviarLinkRecuperacion'");
    }

    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPassword) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cambiarPassword'");
    }

    /**
     * metodo para registrar a un usuario a la aplicacion
     */
    @Override
    public void registrarse(RegistrarClienteDTO registroClienteDTO) throws Exception {
        
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
        
        clienteRepo.save(cliente);
    }

    /**
     * metodo para verificar si el nickname existe o no
     * @param nickname
     * @return
     */
    private boolean verificarNickNameExiste(@NotBlank String nickname) {
        return clienteRepo.findByNickname(nickname).isPresent();
    }

    /**
     * metodo que verifica si esl email existe
     * @param email
     * @return
     */
    private boolean verificarEmailExistente(@NotBlank @Email String email) {
        
        return clienteRepo.findByEmail(email).isPresent();
    }

    /**
     * metodo para obtener a un determinado cliente con el uso del codigo
     */
    @Override
    public DetalleClienteDTO obtenerCliente(String codigo) throws Exception {
        Optional<Cliente> optionalCLiente = clienteRepo.findById(codigo);

        if(optionalCLiente.isEmpty()){
            throw new Exception("El codigo no se encuentra registrado");
        }

        Cliente cliente = optionalCLiente.get();

        return new DetalleClienteDTO(cliente.getNombre(), cliente.getPassword(), cliente.getEmail(), cliente.getFotoPerfil(),cliente.getNickname() , cliente.getCiudad(), cliente.getTelefonos());
    }

    /**
     * metodo para desactivar al cliente
     */
    @Override
    public void eliminarCliente(String codigo) throws Exception{
        Optional<Cliente> clienteOptional = clienteRepo.findById(codigo);
        
        if(clienteOptional.isEmpty()){
            throw new Exception("El cliente no se a encontrado");
        }
        
        Cliente cliente = clienteOptional.get();
        cliente.setEstadoRegistro(EstadoRegistro.INACTIVO);
        clienteRepo.save(cliente);
    }

    /**
     * metodo para listar a todos los clientes
     */
    @Override
    public List<ItemClienteDTO> listarClientes() {
        List<Cliente> clientes = clienteRepo.findAll();

        List<ItemClienteDTO> items = new ArrayList<>();

        for (Cliente cliente: clientes){
            items.add(new ItemClienteDTO(cliente.getCodigo(), cliente.getNombre(), cliente.getFotoPerfil(), cliente.getNickname(), cliente.getCiudad()));

        }
        return items;    
    }

    /**
     * metodo para guardar las paginas favoritas
     */
    @Override
    public void guardarFavorito(String codigoCliente, String codigoNegocio)throws Exception {
        Optional<Cliente> optionalCliente= clienteRepo.findById(codigoCliente);

        if(optionalCliente.isEmpty()){
            throw new Exception("El cliente no existe");
        }
        Cliente cliente = optionalCliente.get();
        cliente.getFavortios().add(codigoNegocio);
        clienteRepo.save(cliente);
    }

    /**
     * metodo para quitar un negocio de favoritos
     */
    @Override
    public void quitarFavorito(String codigoCliente, String codigoNegocio)throws Exception {
        Optional<Cliente> optionalCliente= clienteRepo.findById(codigoCliente);

        if(optionalCliente.isEmpty()){
            throw new Exception("El cliente no existe");
        }
        Cliente cliente = optionalCliente.get();
        cliente.getFavortios().remove(codigoNegocio);
        clienteRepo.save(cliente);
    }

    /**
     * metodo para actualizar un perfil
     */
    @Override
    public void actualizarPerfil(ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        Optional<Cliente> optionalCliente= clienteRepo.findById(actualizarClienteDTO.codigo());

        if(optionalCliente.isEmpty()){
            throw new Exception("El cliente no existe");
        }
        Cliente cliente = optionalCliente.get();
        cliente.setNombre(actualizarClienteDTO.nombre());
        cliente.setFotoPerfil(actualizarClienteDTO.fotoPerfil());
        cliente.setNickname(actualizarClienteDTO.nickname());
        cliente.setCiudad(actualizarClienteDTO.ciudad());
        clienteRepo.save(cliente);
    }

     /**
      * metodo que verifica los diferentes bloqueos con los que cuenta el usuario y verifica si se encuentra bloqueado
      */
    @Override
    public boolean verificarBloqueo(String codigoCliente) throws Exception  {
        Optional<Cliente> optionalCliente= clienteRepo.findById(codigoCliente);

        if(optionalCliente.isEmpty()){
            throw new Exception("El cliente no existe");
        }
        
        Cliente cliente = optionalCliente.get();
        for (int i = 0; i < cliente.getBloqueos().size(); i++) {
            if(verificarBloqueoActual(cliente.getBloqueos().get(i))){
                return true;
            }
        }

        return false;
    }

    //metodo que verifica si el usuario se encuentra bloqueado
    private boolean verificarBloqueoActual(Bloqueo bloqueo) {
        LocalDateTime fechaHoy = LocalDateTime.now();

        if (fechaHoy.isAfter(bloqueo.getFechaInicio()) && fechaHoy.isBefore(bloqueo.getFechaFinal())) {
            return true;
        } else if (fechaHoy.equals(bloqueo.getFechaInicio()) || fechaHoy.equals(bloqueo.getFechaFinal())) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Metodo que guarda las paginas que visito en un historial, y cada 10 se borran las ultimas 5
     */
    @Override
    public void guardarHistorial(String codigoCliente, String codigoNegocio) throws Exception {
        Optional<Cliente> optionalCliente= clienteRepo.findById(codigoCliente);

        if(optionalCliente.isEmpty()){
            throw new Exception("El cliente no existe");
        }
        
        Cliente cliente = optionalCliente.get();
        if(cliente.getHistorial().size()==10){
            ArrayList<String> historialNuevo= limpiarHistorial(cliente.getHistorial());
            cliente.setHistorial(historialNuevo);
        }
        
        cliente.getHistorial().add(codigoNegocio);
        clienteRepo.save(cliente);
    }

    /**
     * Metoodo para limpiar el historial cuando llega a 10 paginas vistas, y deja las ultimas 5 paginas vistas
     * @param historialViejo
     * @return
     */
    private ArrayList<String> limpiarHistorial(ArrayList<String> historialViejo) {
        ArrayList<String> historialNuevo = new ArrayList<>();
        for (int i = 6; i < historialViejo.size(); i++) {
            historialNuevo.add(historialViejo.get(i));
        }

        return historialNuevo;
    }


}
