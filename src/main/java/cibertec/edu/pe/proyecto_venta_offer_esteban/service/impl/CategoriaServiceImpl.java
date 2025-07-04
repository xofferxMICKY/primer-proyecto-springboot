package cibertec.edu.pe.proyecto_venta_offer_esteban.service.impl;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Categoria;
import cibertec.edu.pe.proyecto_venta_offer_esteban.repository.CategoriaRepository;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria buscarPorId(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public void eliminar(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
