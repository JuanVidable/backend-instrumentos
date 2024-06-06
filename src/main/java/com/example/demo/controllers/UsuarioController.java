package com.example.demo.controllers;

import com.example.demo.controllers.Base.BaseControllerImpl;
import com.example.demo.entities.Usuario;
import com.example.demo.services.Impl.UsuarioServiceImpl;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/usuarios")
public class UsuarioController extends BaseControllerImpl<Usuario, UsuarioServiceImpl> {

    @Autowired
    UsuarioService usuarioService;
    @PostMapping("/crear")
    public ResponseEntity<?> crearUser(@RequestBody Usuario usuario) throws Exception {
        Usuario usuarioNuevo = usuarioService.crearUsuario(usuario);
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioNuevo);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }

}
