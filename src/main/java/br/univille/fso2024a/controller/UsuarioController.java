package br.univille.fso2024a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.fso2024a.repository.ClienteRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {


    @Autowired
    private ClienteRepository repository;
    
    @GetMapping
    public ModelAndView index(){
        var listaUsuarios = repository.findAll();
        return new ModelAndView("usuario/index", "listaUsuarios", listaUsuarios);
        
    }
}
