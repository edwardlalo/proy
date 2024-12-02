package projecto_integrador.proy.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntranetVentasController {
    
    @GetMapping("/intranet-ventas")
    public String intranet_ventas() {
        return "Intranet-ventas"; // Redirecciona a la seccion de usuarios de intranet
    }
}
