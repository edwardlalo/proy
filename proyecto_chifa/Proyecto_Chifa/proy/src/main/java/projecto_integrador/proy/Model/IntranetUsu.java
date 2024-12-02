package projecto_integrador.proy.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class IntranetUsu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Se crean los elementos para la lista de usuarios de la intranet
    private int id;

    @Column(nullable = false, length = 100)
    private String correo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String contrasena;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
