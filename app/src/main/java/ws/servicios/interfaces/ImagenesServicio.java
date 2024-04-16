package ws.servicios.interfaces;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ImagenesServicio {
    Map subirImagen(MultipartFile imagen) throws Exception;
    Map eliminarImagen(String idImagen) throws Exception;
    
}