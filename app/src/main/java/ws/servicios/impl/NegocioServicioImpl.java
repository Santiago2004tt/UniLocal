package ws.servicios.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import ws.model.entidades.Horario;
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
        negocio.setHistorialRevisiones(actualizarNegocioDTO.historialRevision());
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
        List<Negocio> negocioOptional = new ArrayList<>();

        if(buscarNegocioDTO.nombre() != null && buscarNegocioDTO.tipoNegocio() != null){
            negocioOptional = negocioRepo.findByNombreContainingIgnoreCaseAndTipoNegocioAndEstadoNegocio(buscarNegocioDTO.nombre(), buscarNegocioDTO.tipoNegocio(), EstadoNegocio.APROBADO);

            if (negocioOptional.isEmpty()) {
                throw new Exception("No se pudo encontrar ningun negocio con las caracteristiacas");
            }
        }else if(buscarNegocioDTO.nombre() != null){
            negocioOptional = negocioRepo.findByNombreContainingIgnoreCaseAndEstadoNegocio(buscarNegocioDTO.nombre(), EstadoNegocio.APROBADO);

            if (negocioOptional.isEmpty()) {
                throw new Exception("No se pudo encontrar ningun negocio con las caracteristiacas");
            }
        }else if(buscarNegocioDTO.tipoNegocio() != null){
            negocioOptional = negocioRepo.findByTipoNegocioAndEstadoNegocio(buscarNegocioDTO.tipoNegocio(), EstadoNegocio.APROBADO);
            
            if (negocioOptional.isEmpty()) {
                throw new Exception("No se pudo encontrar ningun negocio con las caracteristiacas");
            }
        }else{
            throw new Exception("Selecciona para buscar");
        }

        for (int i = 0; i < negocioOptional.size(); i++) {
            ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(negocioOptional.get(i).getCodigo(), negocioOptional.get(i).getUbicacion(), negocioOptional.get(i).getNombre(), negocioOptional.get(i).getDescripcion(), negocioOptional.get(i).getImagenes().get(0), negocioOptional.get(i).getTipoNegocio(), negocioOptional.get(i).getPopularidad());
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

        return new DetalleNegocioDTO(negocio.getCodigo(), negocio.getCodigoCliente(),negocio.getUbicacion(), negocio.getNombre(), negocio.getDescripcion(), negocio.getHorarios(), negocio.getImagenes(), negocio.getTipoNegocio(), negocio.getHistorialRevisiones(),negocio.getTelefonos(), negocio.getPopularidad());
    }

    /**
     * Metodo que lista los negocios propietario usando l 
     */
    @Override
    public List<ItemNegocioDTO> listarNegocioPropietario(String codigoCliente) throws Exception {
        List<ItemNegocioDTO> itemsNegocio = new ArrayList<>();
        List<Negocio> negocios = negocioRepo.findByCodigoCliente(codigoCliente);

        if(negocios.isEmpty()){
            throw new Exception("No se encontro nigun negocio con ese codigo");
        }

        for (int i = 0; i < negocios.size(); i++) {
            ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(negocios.get(i).getCodigo(), negocios.get(i).getUbicacion(), negocios.get(i).getNombre(), negocios.get(i).getDescripcion(), negocios.get(i).getImagenes().get(0), negocios.get(i).getTipoNegocio(),negocios.get(i).getPopularidad());
            itemsNegocio.add(itemNegocioDTO);
        }

        return itemsNegocio;
    }

    /** 
     * metodo que recomienda al usuario los diversos sitios
     */
    @Override
    public List<ItemNegocioDTO> recomendarLugares(String codigoUsuario, Ubicacion ubicacion) throws Exception {
        List<ItemNegocioDTO> itemsNegocio = new ArrayList<>();
        DetalleClienteDTO detalleClienteDTO = clienteServicio.obtenerCliente(codigoUsuario);
        List<String> historial = detalleClienteDTO.historial();
        
        
        if(historial.size() <5){
            throw new Exception("No hay negocios que recomendrar");
        }

        //las variables que seran usadas para tratar los valores
        String nombre = obtenerNegocioActivo(historial.get(0)).nombre();
        TipoNegocio tipoNegocio = obtenerNegocioActivo(historial.get(1)).tipoNegocio();
        String nombre1 = obtenerNegocioActivo(historial.get(2)).nombre();
        TipoNegocio tipoNegocio1 = obtenerNegocioActivo(historial.get(3)).tipoNegocio();
        String nombre2 = obtenerNegocioActivo(historial.get(4)).nombre();

    
        List<Negocio> negocios = negocioRepo.findByRecomendar(nombre, tipoNegocio, nombre1, tipoNegocio1, nombre2);
        
        for (int i = 0; i < negocios.size(); i++) {
            ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(negocios.get(i).getCodigo(), negocios.get(i).getUbicacion(), negocios.get(i).getNombre(), negocios.get(i).getDescripcion(), negocios.get(i).getImagenes().get(0), negocios.get(i).getTipoNegocio(), negocios.get(i).getPopularidad());
            itemsNegocio.add(itemNegocioDTO);
        }

        return itemsNegocio;
    }

    /**
     * metodo que lista las peticiones del usuario
     */
    @Override
    public List<ItemNegocioDTO> listarPeticiones() throws Exception {
        List<ItemNegocioDTO> itemsNegocio = new ArrayList<>();
        List<Negocio> negocios = negocioRepo.findByEstadoNegocio(EstadoNegocio.PENDIENTE);

        if(negocios.isEmpty()){
            throw new Exception("No se encontro nigun negocio pendiente");
        }

        for (int i = 0; i < negocios.size(); i++) {
            ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(negocios.get(i).getCodigo(), negocios.get(i).getUbicacion(), negocios.get(i).getNombre(), negocios.get(i).getDescripcion(), negocios.get(i).getImagenes().get(0), negocios.get(i).getTipoNegocio(), negocios.get(i).getPopularidad());
            itemsNegocio.add(itemNegocioDTO);
        }

        return itemsNegocio;
    }


    /**
     * metodo que verifica si un negocio esta abierto o cerrado
     */
    @Override
    public boolean verificarAbierto(String codigoNegocio) throws Exception {

        Optional<Negocio> negocioOptional = negocioRepo.findById(codigoNegocio);

        if(negocioOptional.isEmpty()){
            throw new Exception("El negocio no existe");
        }

        Negocio negocio = negocioOptional.get();

        ArrayList<Horario> fechasHoy = new ArrayList<>();
        for (int i = 0; i < negocio.getHorarios().size(); i++) {
            DayOfWeek dia = negocio.getHorarios().get(i).getDia();
            if(dia == LocalDate.now().getDayOfWeek()){
                fechasHoy.add(negocio.getHorarios().get(i));
            }
        }
        
        return verificarAbiertoFechas(fechasHoy);
    }

    /**
     * metodo que al recibir las fechas verifica si esta abierto o no
     * @param fechasHoy
     * @return
     */
    private boolean verificarAbiertoFechas(ArrayList<Horario> fechasHoy) {
        for (int i = 0; i < fechasHoy.size(); i++) {
            int horaActual = LocalTime.now().getHour();
            Horario horario = fechasHoy.get(i);
            if(horario.getHoraInicio()<horaActual && horario.getHoraFin()>horaActual){
                return true;
            }
        }
        return false;
    }

    @Override
    public void finalizarTiempoEspera() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finalizarTiempoEspera'");
    }


    @Override
    public List<ItemNegocioDTO> listarNegociosFavoritos(String codigoCliente, Ubicacion ubicacion) throws Exception {
        List<ItemNegocioDTO> lista = new ArrayList<>();
        DetalleClienteDTO detalleClienteDTO = clienteServicio.obtenerCliente(codigoCliente);
        List<String> favoritos = detalleClienteDTO.favoritos();

        for (int i = 0; i < favoritos.size(); i++) {
            DetalleNegocioDTO detalleNegocioDTO = obtenerNegocio(favoritos.get(i));
            ItemNegocioDTO itemNegocioDTO = new ItemNegocioDTO(detalleClienteDTO.codigo(), detalleNegocioDTO.ubicacion(), detalleNegocioDTO.nombre(), detalleNegocioDTO.descripcion(), detalleNegocioDTO.imagenes().get(0), detalleNegocioDTO.tipoNegocio(), detalleNegocioDTO.puntuacion());
            lista.add(itemNegocioDTO);
        }

        if(lista.isEmpty()){
            throw new Exception("No cuenta con negocios favoritos");
        }

        return lista;
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

    /**
     * Metodo que agrega puntuacion al negocio
     */
    @Override
    public void agregarPuntuacion(String codigoNegocio, int puntuacion) throws Exception {
        Optional<Negocio> negocioOptional = negocioRepo.findById(codigoNegocio);

        if(negocioOptional.isEmpty()){
            throw new Exception("El negocio no fue encontrado");
        }

        Negocio negocio = negocioOptional.get();
        negocio.setPopularidad(puntuacion);
        negocioRepo.save(negocio);
    }

    /**
     * metodo que obtiene un negocio activo
     */
    @Override
    public DetalleNegocioDTO obtenerNegocioActivo(String codigoNegocio) throws Exception {
        Optional<Negocio> negocioOptional = negocioRepo.findByCodigoAndEstadoNegocio(codigoNegocio, EstadoNegocio.APROBADO);
        
        if(negocioOptional.isEmpty()){
            throw new Exception("No se a encontrado el negocio");
        }
        Negocio negocio = negocioOptional.get();

        return new DetalleNegocioDTO(negocio.getCodigo(), negocio.getCodigoCliente(),negocio.getUbicacion(), negocio.getNombre(), negocio.getDescripcion(), negocio.getHorarios(), negocio.getImagenes(), negocio.getTipoNegocio(), negocio.getHistorialRevisiones(),negocio.getTelefonos(), negocio.getPopularidad());
    }

    /**
     * metodo que ordena la lista de ciudades por mas cercanos a mas lejanos
     */
    @Override
    public List<ItemNegocioDTO> ordenarUbicacion(Ubicacion ubicacion, List<ItemNegocioDTO> listaNegocios) {
        return ordenarNegociosPorDistancia(ubicacion, listaNegocios);
    }

    /**
     * metodo que ordena los negocios por calificacion
     */
    @Override
    public List<ItemNegocioDTO> ordenarPuntuacion(List<ItemNegocioDTO> listaNegocios) throws Exception {
        return listaNegocios.stream()
        .sorted(Comparator.comparingInt(ItemNegocioDTO::puntuacion).reversed())
        .collect(Collectors.toList());
    }


}
