package cibertec.edu.pe.proyecto_venta_offer_esteban.service;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Rol;

import java.util.List;

public interface RolService {
    List<Rol> listarTodos();
    Rol buscarPorId(Integer id);
    Rol guardar(Rol rol);
    void eliminar(Integer id);
}