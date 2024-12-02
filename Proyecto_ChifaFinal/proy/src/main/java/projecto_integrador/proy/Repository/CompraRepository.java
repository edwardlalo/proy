package projecto_integrador.proy.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projecto_integrador.proy.Model.Compra;
//Como un componente de Spring de tipo "repositorio" para proporcionae metodos CRUD
@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {
    
}
