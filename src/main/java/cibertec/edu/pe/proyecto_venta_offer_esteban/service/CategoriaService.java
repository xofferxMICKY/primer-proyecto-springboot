package cibertec.edu.pe.proyecto_venta_offer_esteban.service;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> listarTodos();
    Categoria buscarPorId(Integer id);
    Categoria guardar(Categoria categoria);
    void eliminar(Integer id);
}