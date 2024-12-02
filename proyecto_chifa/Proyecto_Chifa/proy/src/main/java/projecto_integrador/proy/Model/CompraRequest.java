package projecto_integrador.proy.Model;

public class CompraRequest {
    private String correoUsuario;
    private String nombreUsuario;
    private String productosComprados;
    private String mesasReservadas;
    private double subtotal;
    private double total;

    // Getters y setters
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
