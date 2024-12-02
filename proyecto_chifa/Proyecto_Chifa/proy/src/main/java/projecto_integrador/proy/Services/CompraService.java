package projecto_integrador.proy.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecto_integrador.proy.Model.Compra;
import projecto_integrador.proy.Model.CompraRequest;
import projecto_integrador.proy.Repository.CompraRepository;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public boolean guardarCompra(CompraRequest compraRequest) {
        Compra compra = new Compra();
        compra.setCorreoUsuario(compraRequest.getCorreoUsuario());
        compra.setNombreUsuario(compraRequest.getNombreUsuario());
        compra.setProductosComprados(compraRequest.getProductosComprados());
        compra.setMesasReservadas(compraRequest.getMesasReservadas());
        compra.setSubtotal(compraRequest.getSubtotal());
        compra.setTotal(compraRequest.getTotal());

        compraRepository.save(compra);
        return true;
    }
}
