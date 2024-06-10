package com.example.demo.services;

import com.example.demo.entities.PedidoDetalle;
import com.example.demo.services.Base.BaseService;

import java.util.List;
import java.util.Map;

public interface PedidoDetalleService extends BaseService<PedidoDetalle, Long> {

    public List<Map<String, Object>> obtenerPedidosPorInstrumentos();

    public List<Object[]> generarReporteExcel(String fechaDesde, String fechaHasta);
}
