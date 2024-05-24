package com.example.demo.services.Impl;

import com.example.demo.entities.Instrumento;
import com.example.demo.repositories.BaseRepository;
import com.example.demo.repositories.InstrumentoRepository;
import com.example.demo.services.Base.BaseServiceImpl;
import com.example.demo.services.InstrumentoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentoServiceImpl extends BaseServiceImpl<Instrumento, Long> implements InstrumentoService {

    @Autowired
    private InstrumentoRepository instrumentoRepository;

    public InstrumentoServiceImpl(BaseRepository<Instrumento, Long> baseRepository, InstrumentoRepository instrumentoRepository) {
        super(baseRepository);
        this.instrumentoRepository = instrumentoRepository;
    }

    @Transactional
    public List<Instrumento> search(String palabra) throws Exception{
        try{
            List<Instrumento> instrumentos;
            instrumentos = instrumentoRepository.search(palabra);
            return instrumentos;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
