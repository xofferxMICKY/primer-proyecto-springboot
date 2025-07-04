package cibertec.edu.pe.proyecto_venta_offer_esteban.service.impl;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Venta;
import cibertec.edu.pe.proyecto_venta_offer_esteban.repository.VentaRepository;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.VentaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> listarTodos() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta buscarPorId(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public Venta guardar(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public void eliminar(Integer id) {
        ventaRepository.deleteById(id);
    }
}
