package projecto_integrador.proy.Controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    //Metodo para cerrar sesion
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); //Destruye la sesion
        return "redirect:/inicio"; //Redirige a Inicio.html
    }
}
