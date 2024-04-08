package ws.servicios.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import ws.dto.ActualizarNegocioDTO;
import ws.dto.BuscarNegocioDTO;
import ws.dto.DetalleClienteDTO;
import ws.dto.DetalleNegocioDTO;
import ws.dto.ItemNegocioDTO;
import ws.dto.RegistrarNegocioDTO;
import ws.model.documentos.Negocio;
import ws.model.entidades.Ubicacion;
import ws.model.enums.EstadoNegocio;
import ws.model.enums.TipoNegocio;
import ws.repositorio.NegocioRepo;
import ws.servicios.interfaces.ClienteServicio;
import ws.servicios.interfaces.NegocioServicio;

@Service
@RequiredArgsConstructor
@Transactional
public class NegocioServicioImpl implements NegocioServicio {

    private final NegocioRepo negocioRepo;

    @Autowired
    private final ClienteServicio clienteServicio;

    /**
     * metodo para crear un negocio
     */
    @Override
    public void crearNegocio(RegistrarNegocioDTO registroNegocioDTO) throws Exception {
        
        clienteServicio.obtenerCliente(registroNegocioDTO.codigoUsuario());

        Negocio negocio = new Negocio();
        negocio.setUbicacion(registroNegocioDTO.ubicacion());
        negocio.setCodigoCliente(registroNegocioDTO.codigoUsuario());
        negocio.setNombre(registroNegocioDTO.nombre());
        negocio.setDescripcion(registroNegocioDTO.descripcion());
        negocio.setHorarios(registroNegocioDTO.horarios());
        negocio.setImagenes(registroNegocioDTO.imagenes());
        negocio.setTipoNegocio(registroNegocioDTO.tipoNegocio());
        negocio.setTelefonos(registroNegocioDTO.telefonos());
        negocio.setEstadoNegocio(EstadoNegocio.PENDIENTE);
        negocio.setPopularidad(0);


        negocioRepo.save(negocio);
    }

    /**
     * Metodo para actualizar un negocio
     */
    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception {

        clienteServicio.obtenerCliente(actualizarNegocioDTO.codigoUsuario());
        Optional<Negocio> negocioOptional = negocioRepo.findById(actualizarNegocioDTO.codigo());

        if(negocioOptional.isEmpty()){
            throw new Exception("El negocio no existe");
        }

        Negocio negocio = negocioOptional.get();
        negocio.setUbicacion(actualizarNegocioDTO.ubicacion());
        negocio.setNombre(actualizarNegocioDTO.nombre());
        negocio.setDescripcion(actualizarNegocioDTO.descripcion());
        negocio.setHorarios(actualizarNegocioDTO.horarios());
        negocio.setImagenes(actualizarNegocioDTO.imagenes());
        negocio.setTipoNegocio(actualizarNegocioDTO.tipoNegocio());
        negocio.setTelefonos(actualizarNegocioDTO.telefonos());
        negocio.setEstadoNegocio(actualizarNegocioDTO.estadoNegocio());

        negocioRepo.save(negocio);
    }

    /**
     * Metodo para eliminar un negocio
     */
    @Override
    public void eliminarNegocio(String codigoNegocio) throws Exception {
        Optional<Negocio> negocioOptional = negocioRepo.findById(codigoNegocio);

        if(negocioOptional.isEmpty()){
            throw new Exception("El negocio no existe");
        }

        Negocio negocio = negocioOptional.get();
        negocio.setEstadoNegocio(EstadoNegocio.INACTIVO);
        negocioRepo.save(negocio);
    }

    /**
     * Metodo que busca un negocio dependiendo el requerimiento puede ser por tipo, nombre o ambas y de estado activo
     */
    @Override
    public List<ItemNegocioDTO> buscarNegocio(BuscarNegocioDTO buscarNegocioDTO) throws Exception {
        List<ItemNegocioDTO> itemsNegocio = new ArrayList<>();
        List<Negocio> negocioOptional = negocioRepo.findBusiness(buscarNegocioDTO.nombre(), buscarNegocioDTO.tipoNegocio());
        if (negocioOptional.isEmpty()) {
            throw new Exception("No se pudo encontrar ningun negocio con las caracteristiacas");
        }
        for (int i = 0; i < negocioOptional.size(); i++) {
            ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(negocioOptional.get(i).getCodigo(), negocioOptional.get(i).getUbicacion(), negocioOptional.get(i).getNombre(), negocioOptional.get(i).getDescripcion(), negocioOptional.get(i).getImagenes().get(0), negocioOptional.get(i).getTipoNegocio());
            itemsNegocio.add(itemNegocioDTO);
        }
      
        return itemsNegocio;
    }

    /**
     * Metodo que obtiene un negocio que este pendiente
     */
    @Override
    public DetalleNegocioDTO obtenerNegocio(String codigoNegocio) throws Exception {
        Optional<Negocio> negocioOptional = negocioRepo.findByNegocioModerador(codigoNegocio);
        
        if(negocioOptional.isEmpty()){
            throw new Exception("No se a encontrado el negocio");
        }
        Negocio negocio = negocioOptional.get();

        return new DetalleNegocioDTO(negocio.getCodigo(), negocio.getCodigoCliente(),negocio.getUbicacion(), negocio.getNombre(), negocio.getDescripcion(), negocio.getHorarios(), negocio.getImagenes(), negocio.getTipoNegocio(), negocio.getHistorialRevisiones(),negocio.getTelefonos());
    }

    @Override
    public List<ItemNegocioDTO> listarNegocioPropietario(String codigoCliente) throws Exception {
        List<ItemNegocioDTO> itemsNegocio = new ArrayList<>();
        List<Negocio> negocios = negocioRepo.findByCodigoCliente(codigoCliente);

        if(negocios.isEmpty()){
            throw new Exception("No se encontro nigun negocio con ese codigo");
        }

        for (int i = 0; i < negocios.size(); i++) {
            ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(negocios.get(i).getCodigo(), negocios.get(i).getUbicacion(), negocios.get(i).getNombre(), negocios.get(i).getDescripcion(), negocios.get(i).getImagenes().get(0), negocios.get(i).getTipoNegocio());
            itemsNegocio.add(itemNegocioDTO);
        }

        return itemsNegocio;
    }

    

    @Override
    public List<ItemNegocioDTO> recomendarLugares(String codigoUsuario, Ubicacion ubicacion) throws Exception {
        List<ItemNegocioDTO> itemsNegocio = new ArrayList<>();
        DetalleClienteDTO detalleClienteDTO = clienteServicio.obtenerCliente(codigoUsuario);
        List<String> historial = detalleClienteDTO.historial();
        
        
        if(historial.size() <5){
            throw new Exception("No hay negocios que recomendrar");
        }

        String nombre = obtenerNegocioActivo(historial.get(0)).nombre();
        TipoNegocio tipoNegocio = obtenerNegocioActivo(historial.get(1)).tipoNegocio();
        String nombre1 = obtenerNegocioActivo(historial.get(2)).nombre();
        TipoNegocio tipoNegocio1 = obtenerNegocioActivo(historial.get(3)).tipoNegocio();
        String nombre2 = obtenerNegocioActivo(historial.get(4)).nombre();

        
        List<Negocio> negocios = negocioRepo.findByRecomendar(nombre, tipoNegocio, nombre1, tipoNegocio1, nombre2);
        
        for (int i = 0; i < negocios.size(); i++) {
            ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(negocios.get(i).getCodigo(), negocios.get(i).getUbicacion(), negocios.get(i).getNombre(), negocios.get(i).getDescripcion(), negocios.get(i).getImagenes().get(0), negocios.get(i).getTipoNegocio());
            itemsNegocio.add(itemNegocioDTO);
        }

        return itemsNegocio;
    }

    public TipoNegocio obtenerTipoNegocioRandom() {
        // Obtener todos los valores del enum
        TipoNegocio[] valores = TipoNegocio.values();
        
        // Crear un objeto Random
        Random rand = new Random();

        // Generar un índice aleatorio dentro del rango de los valores del enum
        int indiceAleatorio = rand.nextInt(valores.length);
        
        // Devolver el valor del enum correspondiente al índice aleatorio generado
        return valores[indiceAleatorio];
    }


    @Override
    public List<ItemNegocioDTO> listarPeticiones() throws Exception {
        List<ItemNegocioDTO> itemsNegocio = new ArrayList<>();
        List<Negocio> negocios = negocioRepo.findByEstadoNegocio(EstadoNegocio.PENDIENTE);

        if(negocios.isEmpty()){
            throw new Exception("No se encontro nigun negocio pendiente");
        }

        for (int i = 0; i < negocios.size(); i++) {
            ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(negocios.get(i).getCodigo(), negocios.get(i).getUbicacion(), negocios.get(i).getNombre(), negocios.get(i).getDescripcion(), negocios.get(i).getImagenes().get(0), negocios.get(i).getTipoNegocio());
            itemsNegocio.add(itemNegocioDTO);
        }

        return itemsNegocio;
    }


    /**
     * falta terminar
     */
    @Override
    public boolean verificarAbierto(String codigoNegocio) throws Exception {

        Optional<Negocio> negocioOptional = negocioRepo.findById(codigoNegocio);

        if(negocioOptional.isEmpty()){
            throw new Exception("El negocio no existe");
        }

        //Negocio negocio = negocioOptional.get();
        
        return true;
    }

    

    @Override
    public void finalizarTiempoEspera() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finalizarTiempoEspera'");
    }


    /**
     * Falta
     */
    @Override
    public List<ItemNegocioDTO> listarHistorial (String codigoCliente, Ubicacion ubicacion) throws Exception {
        List<ItemNegocioDTO> itemsNegocio = new ArrayList<>();
        

        return itemsNegocio;
    }

    @Override
    public List<ItemNegocioDTO> listarNegociosFavoritos(String codigoCliente, Ubicacion ubicacion) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarNegociosFavoritos'");
    }


    /**
     * metodo que ordena un arraylist del mas cerca al mas lejos
     * @param desdeUbicacion
     * @param listaNegocios
     * @return
     */
    public List<ItemNegocioDTO> ordenarNegociosPorDistancia(Ubicacion desdeUbicacion, List<ItemNegocioDTO> listaNegocios) {
        listaNegocios.sort(Comparator.comparingDouble(negocio -> distanciaEntrePuntos(desdeUbicacion, negocio.ubicacion())));
        return listaNegocios;
    }

    public double distanciaEntrePuntos(Ubicacion ubicacion1, Ubicacion ubicacion2) {
        return Math.sqrt(Math.pow(ubicacion1.getLatitud() - ubicacion2.getLatitud(), 2) + Math.pow(ubicacion1.getLongitud() - ubicacion2.getLongitud(), 2));
    }

    @Override
    public void agregarPuntuacion(String codigoNegocio, int puntuacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarPuntuacion'");
    }

    @Override
    public DetalleNegocioDTO obtenerNegocioActivo(String codigoNegocio) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerNegocioActivo'");
    }

    @Override
    public List<ItemNegocioDTO> ordenarUbicacion(List<ItemNegocioDTO> listaNegocios) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ordenarUbicacion'");
    }

    @Override
    public List<ItemNegocioDTO> ordenarPuntuacion(List<ItemNegocioDTO> listaNegocios) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ordenarPuntuacion'");
    }

    


}
