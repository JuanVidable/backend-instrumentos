package com.example.demo.controllers;

import com.example.demo.controllers.Base.BaseControllerImpl;
import com.example.demo.entities.Pedido;
import com.example.demo.entities.PreferenceMP;
import com.example.demo.services.Impl.PedidoServiceImpl;
import com.example.demo.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("api/pedidos")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoServiceImpl> {
    @Autowired
    PedidoService pedidoService;
    @PostMapping("create_preference_mp")
    public PreferenceMP crearPreferenciaMercadoPago(@RequestBody Pedido pedido){
        MercadoPagoController cMercadoPago = new MercadoPagoController();
        PreferenceMP preference = cMercadoPago.getPreferenciaIdMercadoPago(pedido);
        return preference;
    }

    @GetMapping("/cantidad-por-mes")
    public List<Map<String, Object>> obtenerCantidadPedidosPorMes() {
        return pedidoService.obtenerCantidadPedidosPorMes();
    }
}
