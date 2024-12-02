package projecto_integrador.proy.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mesas")
public class Mesa {
    //Se crean los elementos para las mesas
    @Id
    private String id_mesa;
    private int numero_mesa;
    private String disponibilidad;

    //Getters y Setters
    public String getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(String id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getNumero_mesa() {
        return numero_mesa;
    }

    public void setNumero_mesa(int numero_mesa) {
        this.numero_mesa = numero_mesa;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
