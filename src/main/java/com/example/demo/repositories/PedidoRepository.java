package com.example.demo.repositories;

import com.example.demo.entities.Pedido;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PedidoRepository extends BaseRepository<Pedido, Long>{
    @Query(value = "SELECT EXTRACT(YEAR FROM p.fecha_pedido) AS anio, " +
            "EXTRACT(MONTH FROM p.fecha_pedido) AS mes, " +
            "COUNT(*) AS cantidad_pedidos " +
            "FROM pedido p " +
            "GROUP BY anio, mes " +
            "ORDER BY anio, mes",
            nativeQuery = true)
    List<Map<String, Object>> obtenerCantidadPedidosPorMes();
}
