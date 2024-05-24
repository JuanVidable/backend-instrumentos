package com.example.demo.services.Impl;

import com.example.demo.entities.Categoria;
import com.example.demo.repositories.BaseRepository;
import com.example.demo.services.Base.BaseServiceImpl;
import com.example.demo.services.CategoriaService;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl extends BaseServiceImpl<Categoria, Long> implements CategoriaService {

    public CategoriaServiceImpl(BaseRepository<Categoria, Long> baseRepository) {
        super(baseRepository);
    }
}
