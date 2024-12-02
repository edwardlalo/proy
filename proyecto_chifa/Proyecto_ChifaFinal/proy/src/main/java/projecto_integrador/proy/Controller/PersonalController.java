package projecto_integrador.proy.Controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import projecto_integrador.proy.Model.Personal;
import projecto_integrador.proy.Services.PersonalService;

@Controller
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    // Crea un objeto de la dependencia de encriptacion
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //Realiza el proceso de login comparando correos y contraseñas
    @PostMapping("/intranetlogin")
    public String login(@ModelAttribute("username") String correo, @ModelAttribute("password") String contrasena, Model model, HttpSession session) {
        Personal encontrado = personalService.findByCorreo(correo);

        if (encontrado != null && passwordEncoder.matches(contrasena, encontrado.getContrasena())) {
            // Guardar los datos del usuario en la sesión
            session.setAttribute("usuarioLogueado", encontrado);

            model.addAttribute("mensaje", "Inicio de sesión exitoso!");
            return "redirect:/intranet";//Redirecciona a la intranet tras login exitoso
        } else {
            model.addAttribute("mensaje", "Credenciales incorrectas");
            return "intranetlogin";// Vuelve a mostrar el formulario de login con mensaje de error
        }
    }
}
