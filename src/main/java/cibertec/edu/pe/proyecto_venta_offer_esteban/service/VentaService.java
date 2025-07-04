package cibertec.edu.pe.proyecto_venta_offer_esteban.service;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Venta;

import java.util.List;

public interface VentaService {
    List<Venta> listarTodos();
    Venta buscarPorId(Integer id);
    Venta guardar(Venta venta);
    void eliminar(Integer id);
}
