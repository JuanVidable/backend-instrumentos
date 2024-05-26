package com.example.demo.controllers;

import com.example.demo.controllers.Base.BaseControllerImpl;
import com.example.demo.entities.Pedido;
import com.example.demo.entities.PreferenceMP;
import com.example.demo.services.Impl.PedidoServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/pedidos")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoServiceImpl> {
    @PostMapping("create_preference_mp")
    public PreferenceMP crearPreferenciaMercadoPago(@RequestBody Pedido pedido){
        MercadoPagoController cMercadoPago = new MercadoPagoController();
        PreferenceMP preference = cMercadoPago.getPreferenciaIdMercadoPago(pedido);
        return preference;
    }
}
