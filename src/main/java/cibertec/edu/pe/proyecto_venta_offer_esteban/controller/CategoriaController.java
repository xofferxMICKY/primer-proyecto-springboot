package cibertec.edu.pe.proyecto_venta_offer_esteban.controller;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Categoria;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Categoria obtener(@PathVariable Integer id) {
        return categoriaService.buscarPorId(id);
    }

    @PostMapping
    public Map<String, Object> registrar(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaService.guardar(categoria);

        Map<String, Object> response = new HashMap<>();
        if (nuevaCategoria != null && nuevaCategoria.getIdCategoria() != null) {
            response.put("tipo", "success");
            response.put("texto", "Creado correctamente");
        } else {
            response.put("tipo", "warning");
            response.put("texto", "No se creó");
        }

        return response;
    }

    @PutMapping
    public Map<String, Object> actualizar(@RequestBody Categoria categoria) {
        Categoria categoriaActualizada = categoriaService.guardar(categoria);

        Map<String, Object> response = new HashMap<>();
        if (categoriaActualizada != null && categoriaActualizada.getIdCategoria() != null) {
            response.put("tipo", "success");
            response.put("texto", "Actualizado correctamente");
        } else {
            response.put("tipo", "warning");
            response.put("texto", "No se actualizó");
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> eliminar(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            categoriaService.eliminar(id);
            response.put("tipo", "success");
            response.put("texto", "Eliminado correctamente");
        } catch (Exception e) {
            response.put("tipo", "error");
            response.put("texto", "No se pudo eliminar");
        }

        return response;
    }
}
