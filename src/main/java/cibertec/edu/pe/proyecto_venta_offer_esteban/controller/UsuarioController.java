package cibertec.edu.pe.proyecto_venta_offer_esteban.controller;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Usuario;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id);
    }

    @PostMapping
    public Map<String, Object> registrar(@RequestBody Usuario usuario) {
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

    @PutMapping
    public Map<String, Object> actualizar(@RequestBody Usuario usuario) {
        Map<String, Object> response = new HashMap<>();

        try {
            Usuario actualizado = usuarioService.guardar(usuario);

            if (actualizado != null && actualizado.getIdUsuario() != null) {
                response.put("tipo", "success");
                response.put("texto", "Usuario actualizado correctamente");
            } else {
                response.put("tipo", "warning");
                response.put("texto", "No se actualizó el usuario");
            }

        } catch (Exception e) {
            response.put("tipo", "error");
            response.put("texto", "Error al actualizar usuario: " + e.getMessage());
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> eliminar(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            usuarioService.eliminar(id);
            response.put("tipo", "success");
            response.put("texto", "Usuario eliminado correctamente");
        } catch (Exception e) {
            response.put("tipo", "error");
            response.put("texto", "No se pudo eliminar el usuario");
        }

        return response;
    }


    @GetMapping("/usuario/{id}")
    public Usuario obtenerPorUsuario(@PathVariable String id) {
        return usuarioService.buscarPorUsuario(id);
    }
}
