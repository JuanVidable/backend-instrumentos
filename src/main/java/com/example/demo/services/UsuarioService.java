package com.example.demo.services;

import com.example.demo.entities.Usuario;
import com.example.demo.services.Base.BaseService;

public interface UsuarioService extends BaseService<Usuario, Long> {
    public Usuario crearUsuario(Usuario usuario) throws Exception;
}
