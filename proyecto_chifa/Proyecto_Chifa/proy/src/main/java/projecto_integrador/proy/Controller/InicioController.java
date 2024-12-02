package projecto_integrador.proy.Controller;

import jakarta.servlet.http.HttpSession; // Importa HttpSession
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
    // Coloca al inicio como la pagina principal
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        // Verificar si hay un usuario en la sesion
        if (session.getAttribute("usuario") != null) {
            model.addAttribute("usuario", session.getAttribute("usuario")); // Agrega el usuario al modelo
        }
        return "Inicio"; //Redirecciona al inicio
    }

    @GetMapping("/inicio")
    public String inicio(Model model, HttpSession session) {
        // Verificar si hay un usuario en la sesion
        if (session.getAttribute("usuario") != null) {
            model.addAttribute("usuario", session.getAttribute("usuario")); // Agrega el usuario al modelo
        }
        return "Inicio"; //Redirecciona al inicio
    }
}
