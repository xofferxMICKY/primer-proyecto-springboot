package cibertec.edu.pe.proyecto_venta_offer_esteban.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    private String nombre;
    private String correo;
    private String clave;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;


}