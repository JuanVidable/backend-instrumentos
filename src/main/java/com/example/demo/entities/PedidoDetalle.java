package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
public class PedidoDetalle extends Base{
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "fk_instrumento")
    private Instrumento instrumento;

    @ManyToOne
    @JoinColumn(name = "fk_pedido")
    private Pedido pedido;

}
