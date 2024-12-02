package projecto_integrador.proy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactanosController {
    
    @GetMapping("/contactanos")
    public String contactanos() {
        return "Contactanos"; // Redirecciona a la seccion de usuarios de intranet
    }
    
}
