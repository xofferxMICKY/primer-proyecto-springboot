package cibertec.edu.pe.proyecto_venta_offer_esteban.controller;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Producto;
import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.VentaProducto;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.ProductoService;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.VentaProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venta-productos")
public class VentaProductoController {

    private final VentaProductoService ventaProductoService;
    private final ProductoService productoService; // ✅ nuevo

    public VentaProductoController(VentaProductoService ventaProductoService,
                                   ProductoService productoService) {
        this.ventaProductoService = ventaProductoService;
        this.productoService = productoService;
    }

    @GetMapping
    public List<VentaProducto> listar() {
        return ventaProductoService.listarTodos();
    }

    @GetMapping("/{id}")
    public VentaProducto obtener(@PathVariable Integer id) {
        return ventaProductoService.buscarPorId(id);
    }

    @PostMapping
    public VentaProducto registrar(@RequestBody VentaProducto ventaProducto) {
        Producto producto = productoService.buscarPorId(ventaProducto.getProducto().getIdProducto());

        if (producto == null || producto.getStock() < ventaProducto.getCantidad()) {
            throw new RuntimeException("Stock insuficiente o producto no encontrado");
        }

        // Disminuir el stock del producto
        producto.setStock(producto.getStock() - ventaProducto.getCantidad());
        productoService.guardar(producto);

        return ventaProductoService.guardar(ventaProducto);
    }


    @PutMapping
    public VentaProducto actualizar(@RequestBody VentaProducto ventaProducto) {
        return ventaProductoService.guardar(ventaProducto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        ventaProductoService.eliminar(id);
    }
}
