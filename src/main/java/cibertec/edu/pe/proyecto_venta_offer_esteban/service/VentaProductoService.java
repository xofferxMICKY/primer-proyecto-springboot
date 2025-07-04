package cibertec.edu.pe.proyecto_venta_offer_esteban.service;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.VentaProducto;

import java.util.List;

public interface VentaProductoService {
    List<VentaProducto> listarTodos();
    VentaProducto buscarPorId(Integer id);
    VentaProducto guardar(VentaProducto ventaProducto);
    void eliminar(Integer id);
}