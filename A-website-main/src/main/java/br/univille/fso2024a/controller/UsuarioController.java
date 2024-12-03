package br.univille.fso2024a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('APPROLE_Admin')")
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
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object username = authentication.getPrincipal();
        System.out.println(username);
        System.out.println(username);
        System.out.println(username);
        System.out.println(username);
        System.out.println(username);
        System.out.println(username);
        System.out.println(username);
        System.out.println(username);
        return new ModelAndView("redirect:/usuarios");
    }

    @GetMapping("/alterar/{userid}")
    public ModelAndView alterar(@PathVariable("userid") long userid) {
        var umUsuario = service.getById(userid);
        return new ModelAndView("usuario/form", "usuario", umUsuario);
    }

    @GetMapping("/delete/{userid}")
    public ModelAndView delete (@PathVariable("userid") long userid) {
        var umUsuario = service.getById(userid);
        if (umUsuario != null) {
            service.delete(userid);
        }
        return new ModelAndView("redirect:/usuarios");
    }
}
