package cibertec.edu.pe.proyecto_venta_offer_esteban.service;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listarTodos();
    Usuario buscarPorId(Integer id);
    Usuario guardar(Usuario usuario);
    void eliminar(Integer id);
    Usuario buscarPorUsuario(String usuario);
}