package cibertec.edu.pe.proyecto_venta_offer_esteban.controller;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Producto;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> listar() {
        return productoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Producto obtener(@PathVariable Integer id) {
        return productoService.buscarPorId(id);
    }

    @PostMapping
    public Map<String, Object> registrar(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.guardar(producto);

        Map<String, Object> response = new HashMap<>();
        if (nuevoProducto != null && nuevoProducto.getIdProducto() != null) {
            response.put("tipo", "success");
            response.put("texto", "Producto creado correctamente");
        } else {
            response.put("tipo", "warning");
            response.put("texto", "No se pudo crear el producto");
        }

        return response;
    }

    @PutMapping
    public Map<String, Object> actualizar(@RequestBody Producto producto) {
        Producto productoActualizado = productoService.guardar(producto);

        Map<String, Object> response = new HashMap<>();
        if (productoActualizado != null && productoActualizado.getIdProducto() != null) {
            response.put("tipo", "success");
            response.put("texto", "Producto actualizado correctamente");
        } else {
            response.put("tipo", "warning");
            response.put("texto", "No se pudo actualizar el producto");
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> eliminar(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            productoService.eliminar(id);
            response.put("tipo", "success");
            response.put("texto", "Producto eliminado correctamente");
        } catch (Exception e) {
            response.put("tipo", "error");
            response.put("texto", "No se pudo eliminar el producto");
        }

        return response;
    }
}
