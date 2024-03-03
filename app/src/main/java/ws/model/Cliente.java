package ws.model;

import java.util.ArrayList;

public class Cliente {

    private String nombre;
    private String password;
    private String email;
    private String fotoPerfil;
    private String codigo;
    private String nickname;
    private String ciudad;
    private EstadoRegistro estadoRegistro;
    private ArrayList<Bloqueo> bloqueos = new ArrayList<>();
    private ArrayList<String> favortios = new ArrayList<>();

}
