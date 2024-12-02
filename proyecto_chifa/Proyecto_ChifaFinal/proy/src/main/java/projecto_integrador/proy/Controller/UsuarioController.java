package projecto_integrador.proy.Controller;
import projecto_integrador.proy.Model.Usuario;
import projecto_integrador.proy.Services.UsuarioService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Crea un objeto BCryptPasswordEncoder para comparar contraseñas
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/registro")
    public String registro(@ModelAttribute Usuario usuario, Model model) {
        //Guardar el usuario
        usuarioService.save(usuario);
        model.addAttribute("mensaje", "Registro exitoso!");
        return "Login"; //Redirecciona al login despues del registro
    }
    //Se procesa el login haciendo la comparacion entre correo y contraseña
    @PostMapping("/procesarLogin")
    public String login(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
        Usuario encontrado = usuarioService.findByCorreo(usuario.getCorreo());

        if (encontrado != null && passwordEncoder.matches(usuario.getContrasena(), encontrado.getContrasena())) {
            //Guardar los datos del usuario en la sesión
            session.setAttribute("usuario", encontrado);
            model.addAttribute("mensaje", "Inicio de sesión exitoso!");
            return "redirect:/inicio"; //Redirecciona a la página de inicio tras el login exitoso
        } else {
            model.addAttribute("mensaje", "Credenciales incorrectas");
            return "Login"; //Vuelve a mostrar el formulario de login si las credenciales son incorrectas
        }
    }
}
