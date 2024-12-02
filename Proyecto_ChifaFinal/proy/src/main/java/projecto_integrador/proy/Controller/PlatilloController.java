package projecto_integrador.proy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import projecto_integrador.proy.Model.Platillo;
import projecto_integrador.proy.Services.PlatilloService;
import java.util.List;

@Controller
public class PlatilloController {
    @Autowired
    private PlatilloService platilloService;

    // Cargar platillos.html"
    @GetMapping("/platillos")
    public String mostrarPlatillos(Model model, HttpSession session) {
        // Verificar si hay un usuario en la sesion
        if (session.getAttribute("usuario") != null) {
            model.addAttribute("usuario", session.getAttribute("usuario")); // Agrega el usuario al modelo
        }
        List<Platillo> platillos = platilloService.findAll(); // Obtener todos los platillos
        model.addAttribute("platillos", platillos); // Agregar platillos al modelo
        return "Platillos"; // Redirecciona a platillos.html
    }
    //Cargar en intranet_productos.html
    @GetMapping("/intranet_productos")
    public String obtenerPlatillos(Model model) {
        List<Platillo> platillos = platilloService.findAll();
        System.out.println("Platillos obtenidos: " + platillos);
        model.addAttribute("platillos", platillos);
        model.addAttribute("nuevoPlatillo", new Platillo());
        return "Intranet_Productos";  // Asegúrate que el nombre del archivo HTML es correcto
    }

    @PostMapping("/intranet_productos/guardar")
    @ResponseBody
    public Platillo guardarPlatillo(@RequestBody Platillo platillo) {
        return platilloService.guardarPlatillo(platillo);  // Usamos el mismo método para guardar o actualizar
    }

    @GetMapping("/intranet_productos/editar/{id}")
    @ResponseBody
    public Platillo editarPlatillo(@PathVariable Long id) {
        Platillo platillo = platilloService.obtenerPlatilloPorId(id);
        return platillo;  // Esto debe devolver un objeto JSON
    }

    

    @DeleteMapping("/intranet_productos/eliminar/{id}")
    @ResponseBody
    public String eliminarPlatillo(@PathVariable Long id) {
        platilloService.eliminarPlatillo(id);
        return "Platillo eliminado correctamente";
    }
    /* 
    @PostMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<Void> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuarioActualizado) {
        usuarioServicio.actualizarUsuario(id, usuarioActualizado);
        return ResponseEntity.ok().build(); // Devuelve un estado 200 OK
    }*/

    
}
