package projecto_integrador.proy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NosotrosController {
    
    @GetMapping("/nosotros")
    public String nosotros() {
        return "Nosotros"; // Redirecciona a la seccion de usuarios de intranet
    }
    
}
