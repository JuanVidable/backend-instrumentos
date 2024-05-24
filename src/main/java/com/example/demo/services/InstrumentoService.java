package com.example.demo.services;

import com.example.demo.entities.Instrumento;
import com.example.demo.services.Base.BaseService;

import java.util.List;

public interface InstrumentoService extends BaseService<Instrumento, Long> {
    public List<Instrumento> search(String palabra) throws Exception;
}
