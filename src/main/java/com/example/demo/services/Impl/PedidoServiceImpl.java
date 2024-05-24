package com.example.demo.services.Impl;

import com.example.demo.entities.Pedido;
import com.example.demo.repositories.BaseRepository;
import com.example.demo.services.Base.BaseServiceImpl;
import com.example.demo.services.PedidoService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido, Long> implements PedidoService {
    public PedidoServiceImpl(BaseRepository<Pedido, Long> baseRepository) {
        super(baseRepository);
    }
}
