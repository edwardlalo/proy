package projecto_integrador.proy.Controller;
import jakarta.servlet.http.HttpSession; // Importa HttpSession
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagoController {

    @GetMapping("/pago")
    public String pago(Model model, HttpSession session) {
        // Verificar si hay un usuario en la sesión
        if (session.getAttribute("usuario") != null) {
            model.addAttribute("usuario", session.getAttribute("usuario")); //Agrega el usuario al modelo
        }
        return "Pago"; //Redirecciona a Pago.html
    }
    
}
