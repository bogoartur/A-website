package br.univille.fso2024a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import br.univille.fso2024a.service.UsuarioService;
import br.univille.fso2024a.entity.Usuario;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {


    @Autowired
    private UsuarioService service;
    
    @GetMapping
    public ModelAndView index(){
        var listaUsuarios = service.getAll();
        return new ModelAndView("usuario/index", "listaUsuarios", listaUsuarios);
        
    }
    @GetMapping("/novo")
    public ModelAndView novo(){
        var usuario = new Usuario();

        return new ModelAndView("usuario/form", "usuario", usuario);
    }

    @PostMapping
    public ModelAndView save(Usuario usuario){
        service.save(usuario);
        return new ModelAndView("redirect:/usuarios");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") long id) {
        var umUsuario = service.getById(id);
        return new ModelAndView("usuario/form", "usuario", umUsuario);
    }
}
