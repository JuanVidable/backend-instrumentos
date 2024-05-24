package com.example.demo;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Instrumento;
import com.example.demo.entities.Pedido;
import com.example.demo.entities.PedidoDetalle;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.InstrumentoRepository;
import com.example.demo.repositories.PedidoDetalleRepository;
import com.example.demo.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class InstrumentosApplication {


	public static void main(String[] args) {

		SpringApplication.run(InstrumentosApplication.class, args);
		System.out.println("OK");

	}



}
