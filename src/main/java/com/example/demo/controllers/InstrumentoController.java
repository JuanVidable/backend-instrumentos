package com.example.demo.controllers;

import com.example.demo.entities.Instrumento;
import com.example.demo.services.InstrumentoServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/instrumentos")
public class InstrumentoController extends BaseControllerImpl<Instrumento, InstrumentoServiceImpl>{
    @Autowired
    private InstrumentoServiceImpl instrumentoService;
    @GetMapping("search")
    public ResponseEntity<?> search(@RequestParam String palabra) throws Exception{
        try{
            return ResponseEntity.status(HttpStatus.OK).body(instrumentoService.search(palabra));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
