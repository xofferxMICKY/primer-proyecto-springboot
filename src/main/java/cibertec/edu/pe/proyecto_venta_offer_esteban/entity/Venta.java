package cibertec.edu.pe.proyecto_venta_offer_esteban.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "venta")
@Data
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private Double total;

    @JsonIgnore
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<VentaProducto> ventaProductos;

    // ✅ Asignar automáticamente la fecha antes de guardar
    @PrePersist
    public void asignarFecha() {
        this.fecha = LocalDateTime.now();
    }
}
