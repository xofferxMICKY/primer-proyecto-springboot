package cibertec.edu.pe.proyecto_venta_offer_esteban.repository;

import cibertec.edu.pe.proyecto_venta_offer_esteban.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}