package projecto_integrador.proy.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import projecto_integrador.proy.Services.CompraService;
import projecto_integrador.proy.Model.CompraRequest;

@RestController
@RequestMapping("/compras")
public class CompraController {
    @Autowired
    private CompraService compraService;
    // Cuando de realiza una compra este revisa los datos y evalua que todo este correcto para lanzar el mensaje
    @PostMapping("/realizar")
    public ResponseEntity<String> realizarCompra(@RequestBody CompraRequest compraRequest) {
        boolean exito = compraService.guardarCompra(compraRequest);
        if (exito) {
            return ResponseEntity.ok("Compra realizada exitosamente!");
        } else {
            return ResponseEntity.status(500).body("Error al realizar la compra.");
        }
    }
}
