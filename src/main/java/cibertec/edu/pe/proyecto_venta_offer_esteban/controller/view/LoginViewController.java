package cibertec.edu.pe.proyecto_venta_offer_esteban.controller.view;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Usuario;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginViewController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public LoginViewController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String productosIndex(Model model) {
        // Si necesitas enviar algo al modelo, lo agregas aquí
        return "login/index"; // Esto busca src/main/resources/templates/producto/index.html
    }
    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> registroIndex(@RequestBody Usuario usuario) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Encriptar clave si no está en blanco
            if (usuario.getClave() != null && !usuario.getClave().isEmpty()) {
                usuario.setClave(passwordEncoder.encode(usuario.getClave()));
            }

            Usuario nuevoUsuario = usuarioService.guardar(usuario);

            if (nuevoUsuario != null && nuevoUsuario.getIdUsuario() != null) {
                response.put("tipo", "success");
                response.put("texto", "Usuario creado correctamente");
            } else {
                response.put("tipo", "warning");
                response.put("texto", "No se pudo crear el usuario");
            }

        } catch (Exception e) {
            response.put("tipo", "error");
            response.put("texto", "Error al crear usuario: " + e.getMessage());
        }

        return response;
    }
}
