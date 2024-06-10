package com.example.demo.services.Impl;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Pedido;
import com.example.demo.entities.PedidoDetalle;
import com.example.demo.repositories.BaseRepository;
import com.example.demo.repositories.PedidoDetalleRepository;
import com.example.demo.services.Base.BaseService;
import com.example.demo.services.Base.BaseServiceImpl;
import com.example.demo.services.PedidoDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PedidoDetalleServiceImpl extends BaseServiceImpl<PedidoDetalle, Long> implements PedidoDetalleService {
    public PedidoDetalleServiceImpl(BaseRepository<PedidoDetalle, Long> baseRepository) {
        super(baseRepository);
    }
    @Autowired
    PedidoDetalleRepository pedidoDetalleRepository;
    @Override
    public List<Map<String, Object>> obtenerPedidosPorInstrumentos() {
        return pedidoDetalleRepository.obtenerCantidadPedidosPorInstrumento();
    }

    public List<Object[]> generarReporteExcel(String fechaDesde, String fechaHasta) {
        return pedidoDetalleRepository.generarReporteExcel(fechaDesde, fechaHasta);
    }
}
