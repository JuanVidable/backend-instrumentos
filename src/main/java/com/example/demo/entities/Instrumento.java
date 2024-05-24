package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instrumento extends Base {
    private String instrumento;
    private String marca;
    private String modelo;
    private String imagen;
    private float precio;
    private String costoEnvio;
    private int cantidadVendida;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "fk_categoria")
    private Categoria categoria;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instrumento", fetch = FetchType.EAGER)
//    private List<PedidoDetalle> detalles;
}
