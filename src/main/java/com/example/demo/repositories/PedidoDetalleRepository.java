package com.example.demo.repositories;

import com.example.demo.entities.Pedido;
import com.example.demo.entities.PedidoDetalle;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PedidoDetalleRepository extends BaseRepository<PedidoDetalle, Long>{

    @Query(value = "SELECT i.instrumento AS nombre_instrumento, " +
            "COUNT(*) AS cantidad_pedidos " +
            "FROM pedido_detalle pd " +
            "JOIN instrumento i ON pd.fk_instrumento = i.id " +
            "GROUP BY pd.fk_instrumento",
            nativeQuery = true)
    List<Map<String, Object>> obtenerCantidadPedidosPorInstrumento();

    @Query(value = "SELECT p.fecha_pedido, pd.cantidad, i.instrumento, i.marca, i.modelo, i.precio, pd.cantidad * i.precio AS subtotal " +
            "FROM pedido_detalle pd " +
            "JOIN pedido p ON pd.fk_pedido = p.id " +
            "JOIN instrumento i ON pd.fk_instrumento = i.id " +
            "WHERE CONVERT(SUBSTRING(p.fecha_pedido, 1, 10), DATE) BETWEEN :fechaDesde AND :fechaHasta",
            nativeQuery = true)
    List<Object[]> generarReporteExcel(String fechaDesde, String fechaHasta);
}
