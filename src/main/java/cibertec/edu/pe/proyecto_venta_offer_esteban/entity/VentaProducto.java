package cibertec.edu.pe.proyecto_venta_offer_esteban.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "venta_producto")
@Data
public class VentaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVentaProducto;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    private Integer cantidad;
    private Double precioUnitario;


}