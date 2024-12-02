package projecto_integrador.proy.Services;
import projecto_integrador.proy.Model.Mesa;
import projecto_integrador.proy.Repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;
    public List<Mesa> obtenerMesas() {
        return mesaRepository.findAll(); // Con ayuda del repositorio devuelve todas las mesas de la tabla
    }
}
