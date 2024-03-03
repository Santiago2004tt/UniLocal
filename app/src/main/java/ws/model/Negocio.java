package ws.model;

import java.util.ArrayList;

public class Negocio {

    private Ubicacion Ubicacion;
    private String nombre;
    private String descripcion;
    private ArrayList<Horario> horarios = new ArrayList<>();
    private EstadoRegistro estadoRegistro;
    private ArrayList<String> imagenes = new ArrayList<>();
    private ArrayList<HistorialRevision> historialRevisiones = new ArrayList<>();
    private String codigo;
    private String codigoCliente;
    private TipoNegocio tipoNegocio;
    private ArrayList<String> telefonos;
}
