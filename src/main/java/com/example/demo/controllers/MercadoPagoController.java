package com.example.demo.controllers;

import com.example.demo.entities.Pedido;
import com.example.demo.entities.PreferenceMP;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.resources.preference.Preference;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class MercadoPagoController {
    public PreferenceMP getPreferenciaIdMercadoPago(Pedido pedido){
        try {
            MercadoPagoConfig.setAccessToken("TEST-3839135581548674-052222-920d61ce4f230453e9a614db36451857-785517820");

            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(pedido.getId().toString())
                    .title("Titulo de pedido")
                    .description("Pedido realizado desde el carrito de compras")
                    .currencyId("ARS")  // Asegúrate de usar el código de moneda correcto
                    .quantity(1)  // Asegúrate de incluir la cantidad
                    .unitPrice(new BigDecimal(pedido.getTotal_pedido()))
                    .build();

            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

            PreferenceBackUrlsRequest backURL = PreferenceBackUrlsRequest.builder()
                    .success("http://localhost:5173/mpsuccess")
                    .pending("http://localhost:5173/mppending")
                    .failure("http://localhost:5173/mpfailure")
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backURL)
                    .build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            System.out.println("Llego hasta aca");

            PreferenceMP mpPreference = new PreferenceMP();
            mpPreference.setStatusCode(preference.getResponse().getStatusCode());
            mpPreference.setId(preference.getId());
            return mpPreference;

        } catch (MPApiException e) {
            System.err.println("Error de API de MercadoPago: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
