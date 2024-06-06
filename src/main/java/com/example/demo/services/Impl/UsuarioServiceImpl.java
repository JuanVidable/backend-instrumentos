package com.example.demo.services.Impl;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.BaseRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.Base.BaseServiceImpl;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService {
    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository) {
        super(baseRepository);
    }

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario){
        usuario.setClave(usuario.getClaveEncriptada());
        return usuarioRepository.save(usuario);
    }
}
