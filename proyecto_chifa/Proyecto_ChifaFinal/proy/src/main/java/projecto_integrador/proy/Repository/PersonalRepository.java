package projecto_integrador.proy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projecto_integrador.proy.Model.Personal;

//Como un componente de Spring de tipo "repositorio" para proporcionae metodos CRUD
public interface PersonalRepository extends JpaRepository<Personal, Integer> {
    Personal findByCorreo(String correo);
}
