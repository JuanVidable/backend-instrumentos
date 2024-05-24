package com.example.demo.services.Impl;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Pedido;
import com.example.demo.entities.PedidoDetalle;
import com.example.demo.repositories.BaseRepository;
import com.example.demo.services.Base.BaseService;
import com.example.demo.services.Base.BaseServiceImpl;
import com.example.demo.services.PedidoDetalleService;
import org.springframework.stereotype.Service;

@Service
public class PedidoDetalleServiceImpl extends BaseServiceImpl<PedidoDetalle, Long> implements PedidoDetalleService {
    public PedidoDetalleServiceImpl(BaseRepository<PedidoDetalle, Long> baseRepository) {
        super(baseRepository);
    }
}
