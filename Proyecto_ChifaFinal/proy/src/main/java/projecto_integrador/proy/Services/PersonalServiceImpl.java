package projecto_integrador.proy.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecto_integrador.proy.Model.Personal;
import projecto_integrador.proy.Repository.PersonalRepository;

import java.util.List;

@Service
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    private PersonalRepository personalRepository;

    @Override
    public void save(Personal personal) {
        personalRepository.save(personal);
    }

    @Override
    public Personal findByCorreo(String correo) {
        return personalRepository.findByCorreo(correo);
    }

    @Override
    public List<Personal> findAll() {
        return personalRepository.findAll();
    }
}
