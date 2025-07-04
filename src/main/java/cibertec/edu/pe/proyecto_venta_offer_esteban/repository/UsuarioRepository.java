package cibertec.edu.pe.proyecto_venta_offer_esteban.repository;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);
}
