package projecto_integrador.proy.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecto_integrador.proy.Model.Platillo;
import projecto_integrador.proy.Repository.PlatilloRepository;
import java.util.List;

@Service
public class PlatilloService {

    @Autowired
    private PlatilloRepository platilloRepository;
    // Método para obtener todos los platillos de la bd con ayuda del repository
    public List<Platillo> findAll() {
        return platilloRepository.findAll();
    }
    
    public Platillo guardarPlatillo(Platillo platillo) {
        if (platillo.getId() != null) {
            // Si el platillo tiene un id, lo actualizamos
            return platilloRepository.save(platillo);
        } else {
            // Si no tiene id, creamos uno nuevo
            return platilloRepository.save(platillo);
        }
    }
    

    public Platillo obtenerPlatilloPorId(Long id) {
        return platilloRepository.findById(id).orElse(null);
    }
    
    public void eliminarPlatillo(long id) {
        platilloRepository.deleteById(id);
    }
    
    /*public Platillo actualizarPlatillo(Long id, Platillo platilloRepository) {
        return platilloRepository.findById(id)
                .map(platillo -> {
                    platillo.setNombre(platilloRepository.getNombre());
                    platillo.setDescripcion(platilloRepository.getDescripcion());
                    platillo.setPrecio(platilloRepository.getPrecio());
                    platillo.setCalificacion(platilloRepository.getCalificacion());
                    platillo.setImagen(platilloRepository.getImagen());
                    return platilloRepository.save(platillo);
                })
                .orElse(null);
    }*/
}