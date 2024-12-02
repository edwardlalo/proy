package projecto_integrador.proy.Services;

import projecto_integrador.proy.Model.Personal;

import java.util.List;

//Guarda los datos del personal en la bd
public interface PersonalService {
    void save(Personal personal);
    Personal findByCorreo(String correo);
    List<Personal> findAll();
}
