package cibertec.edu.pe.proyecto_venta_offer_esteban.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductoViewController {

    @GetMapping("/productos")
    public String productosIndex(Model model) {
        // Si necesitas enviar algo al modelo, lo agregas aquí
        return "producto/index"; // Esto busca src/main/resources/templates/producto/index.html
    }

}
