package cibertec.edu.pe.proyecto_venta_offer_esteban.service;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> listarTodos();
    Producto buscarPorId(Integer id);
    Producto guardar(Producto producto);
    void eliminar(Integer id);
}
