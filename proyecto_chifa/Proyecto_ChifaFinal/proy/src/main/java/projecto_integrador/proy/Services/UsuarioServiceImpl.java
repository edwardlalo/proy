package projecto_integrador.proy.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projecto_integrador.proy.Model.Usuario;
import projecto_integrador.proy.Repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Crear un objeto BCryptPasswordEncoder para encriptar contraseñas
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public void save(Usuario usuario) {
        //Encriptar la contraseña antes de guardarla
        String contraseñaEncriptada = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(contraseñaEncriptada);
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
}
