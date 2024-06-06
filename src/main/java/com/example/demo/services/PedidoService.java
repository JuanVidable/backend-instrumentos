package com.example.demo.services;

import com.example.demo.entities.Pedido;
import com.example.demo.services.Base.BaseService;

import java.util.List;
import java.util.Map;

public interface PedidoService extends BaseService<Pedido, Long> {
    public List<Map<String, Object>> obtenerCantidadPedidosPorMes();
}
