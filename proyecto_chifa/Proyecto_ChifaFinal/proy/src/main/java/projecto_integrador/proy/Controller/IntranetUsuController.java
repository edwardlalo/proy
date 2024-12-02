package projecto_integrador.proy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import projecto_integrador.proy.Model.IntranetUsu;
import projecto_integrador.proy.Model.Usuario;
import projecto_integrador.proy.Repository.IntranetUsuRepository;
import projecto_integrador.proy.Repository.UsuarioRepository;
import projecto_integrador.proy.Util.ListaUsuariosExcel;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Controller
public class IntranetUsuController {
    @Autowired
    private IntranetUsuRepository intranetUsuRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Se administran los datos de los usuarios en una lista y se almacenan
    @GetMapping("/intranet_usuarios")
    public String intranetUsu(Model model) {
        List<IntranetUsu> usuarios = intranetUsuRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "Intranet_usuarios"; //Redirige a la seccion de usuarios de la intranet
    }
    
    //Gestiona el guardado del excel que va a contener a todos los usuarios
    @GetMapping(value = "/intranet_usuarios/exportar", params = "format=xlsx")
    public ModelAndView exportarUsuariosExcel() {
        List<Usuario> usuarios = usuarioRepository.findAll(); // Usa la instancia inyectada
        System.out.println("Usuarios recuperados: " + usuarios.size()); // Verificar el tamaño de la lista
        ModelAndView modelAndView = new ModelAndView(new ListaUsuariosExcel());
        modelAndView.addObject("usuarios", usuarios);
        return modelAndView;
    }
}
