package projecto_integrador.proy.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.HttpSession;
import projecto_integrador.proy.Model.CompraRequest;
import projecto_integrador.proy.Model.Usuario;
import projecto_integrador.proy.Services.CompraService;
import java.util.List;
@Controller
public class PruebasController {
    @Autowired
    private CompraService compraService;

    @PostMapping("/compras/insertar")
    @ResponseBody
    public String insertarCompra(@RequestBody CompraRequest compraRequest, HttpSession session) {
        //Obtener información del usuario de la sesion
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        //Asignar datos del usuario al objeto compraRequest
        String correoUsuario = usuario.getCorreo();
        String nombreUsuario = usuario.getNombre();
    
        //Obtener y concatenar los productos comprados desde la sesión
        String productosComprados = (String) session.getAttribute("productosString");
        if (productosComprados == null) {
            productosComprados = "No hay productos comprados";
        }
    
        //Obtener las mesas seleccionadas desde la sesión
        @SuppressWarnings("unchecked")
        List<String> mesasReservadas = (List<String>) session.getAttribute("mesasSeleccionadas");
        String mesasStringa = mesasReservadas != null ? String.join(", ", mesasReservadas) : "No hay mesas reservadas";
    
        //Llenar el objeto CompraRequest con datos de la sesión y del cuerpo de la solicitud
        compraRequest.setCorreoUsuario(correoUsuario);
        compraRequest.setNombreUsuario(nombreUsuario);
        compraRequest.setProductosComprados(productosComprados);
        compraRequest.setMesasReservadas(mesasStringa);
    
        //Asignar valores para subtotal y total (puedes calcularlos dinámicamente)
        compraRequest.setSubtotal(100.00);
        compraRequest.setTotal(110.00);
    
        //Guardar la compra
        if (compraService.guardarCompra(compraRequest)) {
            return "Compra completada exitosamente.";
        } else {
            return "Error al insertar la compra.";
        }
    }
}
