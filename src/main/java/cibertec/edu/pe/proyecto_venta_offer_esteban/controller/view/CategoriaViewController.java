package cibertec.edu.pe.proyecto_venta_offer_esteban.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoriaViewController {

    @GetMapping("/categorias")
    public String categoriasIndex() {
        return "categoria/index"; // Busca en templates/categoria/index.html
    }
}
