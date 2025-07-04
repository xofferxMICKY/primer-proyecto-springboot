package cibertec.edu.pe.proyecto_venta_offer_esteban.service.impl;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Rol;
import cibertec.edu.pe.proyecto_venta_offer_esteban.repository.RolRepository;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.RolService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Rol> listarTodos() {
        return rolRepository.findAll();
    }

    @Override
    public Rol buscarPorId(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Override
    public Rol guardar(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public void eliminar(Integer id) {
        rolRepository.deleteById(id);
    }
}