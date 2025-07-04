package cibertec.edu.pe.proyecto_venta_offer_esteban.controller;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Producto;
import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Usuario;
import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Venta;
import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.VentaProducto;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.ProductoService;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.UsuarioService;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.VentaProductoService;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;
    private final UsuarioService usuarioService;
    private final ProductoService productoService;
    private final VentaProductoService ventaProductoService;

    public VentaController(VentaService ventaService, UsuarioService usuarioService, ProductoService productoService, VentaProductoService ventaProductoService) {
        this.ventaService = ventaService;
        this.usuarioService = usuarioService;
        this.productoService = productoService;
        this.ventaProductoService = ventaProductoService;
    }

    @GetMapping
    public List<Venta> listar() {
        return ventaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Venta obtener(@PathVariable Integer id) {
        return ventaService.buscarPorId(id);
    }

    @PostMapping
    public Venta registrar(@RequestBody Venta venta) {
        return ventaService.guardar(venta);
    }

    @PutMapping
    public Venta actualizar(@RequestBody Venta venta) {
        return ventaService.guardar(venta);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        ventaService.eliminar(id);
    }

    @PostMapping("/comprar")
    public ResponseEntity<?> realizarCompra(@RequestBody Map<String, Object> datos) {
        try {
            Integer idUsuario = (Integer) datos.get("idUsuario");
            Integer idProducto = (Integer) datos.get("idProducto");

            // Buscar usuario y producto
            Usuario usuario = usuarioService.buscarPorId(idUsuario);
            Producto producto = productoService.buscarPorId(idProducto);

            if (producto == null || producto.getStock() <= 0) {
                return ResponseEntity.badRequest().body("Producto sin stock disponible.");
            }

            // Crear venta
            Venta venta = new Venta();
            venta.setUsuario(usuario);
            venta.setTotal(producto.getPrecio()); // precio del único producto
            Venta ventaRegistrada = ventaService.guardar(venta);

            // Crear venta-producto
            VentaProducto ventaProducto = new VentaProducto();
            ventaProducto.setVenta(ventaRegistrada);
            ventaProducto.setProducto(producto);
            ventaProducto.setCantidad(1);
            ventaProducto.setPrecioUnitario(producto.getPrecio());
            ventaProductoService.guardar(ventaProducto);

            // Reducir stock
            producto.setStock(producto.getStock() - 1);
            productoService.guardar(producto);

            return ResponseEntity.ok().body("Compra registrada correctamente.");
        } catch (Exception e) {
            e.printStackTrace(); // 👈 muestra el error en consola
            return ResponseEntity.internalServerError()
                    .body("Error al realizar la compra: " + e.getMessage()); // 👈 devuelve el mensaje exacto al frontend
        }
    }


}
