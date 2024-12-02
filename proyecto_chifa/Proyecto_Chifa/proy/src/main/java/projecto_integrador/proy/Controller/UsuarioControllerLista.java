package projecto_integrador.proy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import projecto_integrador.proy.Services.UsuarioServiceImpl;
import projecto_integrador.proy.Util.ListaUsuariosExcel;

@Controller
public class UsuarioControllerLista {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping("/lista_usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioServiceImpl.listarTodos());
        return "Intranet_usuarios"; //Archivo Intranet_usuarios.html
    }
    //Prepara el metodo para guardar a los usuarios en un excel
    @GetMapping(value = "/lista_usuarios", params = "format=xlsx")
    public ModelAndView listarUsuariosExcel() {
        ModelAndView modelAndView = new ModelAndView(new ListaUsuariosExcel());
        modelAndView.addObject("usuarios", usuarioServiceImpl.listarTodos());
        return modelAndView;
    }
}
