package projecto_integrador.proy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projecto_integrador.proy.Model.Usuario;

//Como un componente de Spring de tipo "repositorio" para proporcionae metodos CRUD
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByCorreo(String correo);
}
