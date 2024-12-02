package projecto_integrador.proy.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Se crean todos los elementos de la compra
    private int idCompra;

    @Column(name = "correo_usuario", nullable = false)
    private String correoUsuario;

    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;

    @Column(name = "productos_comprados", nullable = false)
    private String productosComprados;

    @Column(name = "mesas_reservadas", nullable = false)
    private String mesasReservadas;

    @Column(nullable = false)
    private double subtotal;

    @Column(nullable = false)
    private double total;

    // Getters y Setters
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getProductosComprados() {
        return productosComprados;
    }

    public void setProductosComprados(String productosComprados) {
        this.productosComprados = productosComprados;
    }

    public String getMesasReservadas() {
        return mesasReservadas;
    }

    public void setMesasReservadas(String mesasReservadas) {
        this.mesasReservadas = mesasReservadas;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
