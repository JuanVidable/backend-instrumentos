package com.example.demo.controllers;

import com.example.demo.controllers.Base.BaseControllerImpl;
import com.example.demo.entities.PedidoDetalle;
import com.example.demo.services.Impl.PedidoDetalleServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/detalles")
public class PedidoDetalleController extends BaseControllerImpl<PedidoDetalle, PedidoDetalleServiceImpl> {
}
