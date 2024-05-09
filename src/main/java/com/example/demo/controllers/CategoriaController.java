package com.example.demo.controllers;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Instrumento;
import com.example.demo.services.CategoriaServiceImpl;
import com.example.demo.services.InstrumentoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin( "*" )
@RequestMapping(path = "api/categorias")
public class CategoriaController extends BaseControllerImpl<Categoria, CategoriaServiceImpl>{
}
