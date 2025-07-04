package cibertec.edu.pe.proyecto_venta_offer_esteban.service.impl;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.VentaProducto;
import cibertec.edu.pe.proyecto_venta_offer_esteban.repository.VentaProductoRepository;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.VentaProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaProductoServiceImpl implements VentaProductoService {

    private final VentaProductoRepository ventaProductoRepository;

    public VentaProductoServiceImpl(VentaProductoRepository ventaProductoRepository) {
        this.ventaProductoRepository = ventaProductoRepository;
    }

    @Override
    public List<VentaProducto> listarTodos() {
        return ventaProductoRepository.findAll();
    }

    @Override
    public VentaProducto buscarPorId(Integer id) {
        return ventaProductoRepository.findById(id).orElse(null);
    }

    @Override
    public VentaProducto guardar(VentaProducto ventaProducto) {
        return ventaProductoRepository.save(ventaProducto);
    }

    @Override
    public void eliminar(Integer id) {
        ventaProductoRepository.deleteById(id);
    }
}
