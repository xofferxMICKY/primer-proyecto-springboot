package cibertec.edu.pe.proyecto_venta_offer_esteban.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioViewController {

    @GetMapping("/administrador")
    public String vistaAdministrador() {
        return "index/admin"; // Va a templates/index/admin.html
    }

    @GetMapping("/operario")
    public String vistaOperario() {
        return "index/operario"; // Va a templates/index/operario.html
    }
}
