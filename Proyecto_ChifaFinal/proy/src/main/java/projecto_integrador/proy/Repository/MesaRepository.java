package projecto_integrador.proy.Repository;

import projecto_integrador.proy.Model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Como un componente de Spring de tipo "repositorio" para proporcionae metodos CRUD
@Repository
public interface MesaRepository extends JpaRepository<Mesa, String> {
    
}
