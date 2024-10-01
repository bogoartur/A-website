package br.univille.fso2024a.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.fso2024a.repository.UsuarioRepository;
import br.univille.fso2024a.service.UsuarioService;
import br.univille.fso2024a.entity.Usuario;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {


    @Autowired
    private UsuarioService service;
    
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios(){
        var listaUsuarios = service.getAll();
        return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);
        
    }
}
