package cibertec.edu.pe.proyecto_venta_offer_esteban.controller.view;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Rol;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.RolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RolViewController {


    @GetMapping("/roles")
    public String rolesIndex(Model model) {
        return "rol/index"; // thymeleaf buscará /templates/rol/lista.html
    }
}
