package cibertec.edu.pe.proyecto_venta_offer_esteban.controller;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Rol;
import cibertec.edu.pe.proyecto_venta_offer_esteban.service.RolService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public List<Rol> listar() {
        return rolService.listarTodos();
    }

    @GetMapping("/{id}")
    public Rol obtener(@PathVariable Integer id) {
        return rolService.buscarPorId(id);
    }

    @PostMapping
    public Map<String, Object> registrar(@RequestBody Rol rol) {
        Rol nuevoRol = rolService.guardar(rol);

        Map<String, Object> response = new HashMap<>();

        if (nuevoRol != null && nuevoRol.getIdRol() != null) {
            response.put("tipo", "success");
            response.put("texto", "Creado correctamente");
        } else {
            response.put("tipo", "warning");
            response.put("texto", "No se creó");
        }

        return response;
    }


    @PutMapping
    public Map<String, Object> actualizar(@RequestBody Rol rol) {
        Rol rolActualizado = rolService.guardar(rol);

        Map<String, Object> response = new HashMap<>();

        if (rolActualizado != null && rolActualizado.getIdRol() != null) {
            response.put("tipo", "success");
            response.put("texto", "Actualizado correctamente");
        } else {
            response.put("tipo", "warning");
            response.put("texto", "No se actualizó");
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> eliminar(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            rolService.eliminar(id);
            response.put("tipo", "success");
            response.put("texto", "Eliminado correctamente");
        } catch (Exception e) {
            response.put("tipo", "error");
            response.put("texto", "No se pudo eliminar");
        }

        return response;
    }

}
