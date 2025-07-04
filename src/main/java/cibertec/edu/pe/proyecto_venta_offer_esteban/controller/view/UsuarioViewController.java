package cibertec.edu.pe.proyecto_venta_offer_esteban.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioViewController {

    @GetMapping("/usuarios")
    public String usuariosIndex(Model model) {
        return "usuario/index"; // Thymeleaf buscará en /templates/usuario/index.html
    }
}
