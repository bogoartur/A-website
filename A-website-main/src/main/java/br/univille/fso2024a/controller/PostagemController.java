package br.univille.fso2024a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.univille.fso2024a.entity.Postagem;
import br.univille.fso2024a.entity.Usuario;
import br.univille.fso2024a.service.PostagemService;
import br.univille.fso2024a.service.UsuarioService;

@Controller
@RequestMapping("/homepage")
public class PostagemController {
    @Autowired
    private PostagemService service;

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public ModelAndView index() {
        var listaPostagens = service.getAll();
        return new ModelAndView("postagem/index", "listaPostagens", listaPostagens);
    }

    @GetMapping("/novo")
    public ModelAndView novo(){
        var postagem = new Postagem();
        var usuarios = usuarioService.getAll();
        return new ModelAndView("postagem/form", "postagem", postagem).addObject("usuarios", usuarios);
    }

    @PostMapping
    public ModelAndView save(Postagem postagem, @RequestParam Long usuario){
        Usuario usuarioObj = usuarioService.getById(usuario);
        postagem.setUsuario(usuarioObj);
        service.save(postagem);
        return new ModelAndView("redirect:/homepage");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete (@PathVariable("id") long id) {
        var umaPostagem = service.getById(id);
        if (umaPostagem != null) {
            service.delete(id);
        }
        return new ModelAndView("redirect:/homepage");
    }
}
