package com.example.demo.services;

import com.example.demo.entities.Instrumento;

import java.util.List;

public interface InstrumentoService extends BaseService<Instrumento, Long>{
    public List<Instrumento> search(String palabra) throws Exception;
}
