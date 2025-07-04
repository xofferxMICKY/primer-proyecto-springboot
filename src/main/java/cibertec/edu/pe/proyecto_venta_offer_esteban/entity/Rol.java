package cibertec.edu.pe.proyecto_venta_offer_esteban.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "rol")
@Data
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;


}