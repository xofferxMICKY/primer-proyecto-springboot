package cibertec.edu.pe.proyecto_venta_offer_esteban.service.impl;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Usuario;
import cibertec.edu.pe.proyecto_venta_offer_esteban.repository.UsuarioRepository;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario buscarPorUsuario(String usuario) {
        return usuarioRepository.findByCorreo(usuario).get();
    }
}
