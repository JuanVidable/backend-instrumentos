package com.example.demo.controllers;

import com.example.demo.controllers.Base.BaseControllerImpl;
import com.example.demo.entities.Pedido;
import com.example.demo.services.Impl.PedidoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/pedidos")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoServiceImpl> {
}
