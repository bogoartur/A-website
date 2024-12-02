package br.univille.fso2024a.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.univille.fso2024a.entity.Curtida;
import br.univille.fso2024a.entity.Postagem;
import br.univille.fso2024a.entity.Usuario;
import br.univille.fso2024a.repository.CurtidaRepository;
import br.univille.fso2024a.service.PostagemService;
import br.univille.fso2024a.service.UsuarioService;
import br.univille.fso2024a.service.impl.UsuarioServiceImpl;

import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/")
public class PostagemController {
    @Autowired
    private PostagemService service;

    @Autowired
    private UsuarioServiceImpl usuarioService;

        @Autowired
    private CurtidaRepository curtidaRepository;

    @GetMapping
    public ModelAndView index(@AuthenticationPrincipal OAuth2User principal) {
        var listaPostagens = service.getAll();
        var postagem = new Postagem();
        
        String emailLogado = principal.getAttribute("preferred_username");
        String nomeLogado = principal.getAttribute("name");

        Usuario umUsuario = usuarioService.findByEmail(emailLogado);

        if (umUsuario == null) {
            umUsuario = new Usuario(nomeLogado, emailLogado);
            usuarioService.save(umUsuario);
        }

        Set<Long> curtidasDoUsuario = curtidaRepository.findByUsuario(umUsuario)
        .stream()
        .map(curtida -> curtida.getPostagem().getId())
        .collect(Collectors.toSet());

        ModelAndView modelAndView = new ModelAndView("home/index");

        modelAndView.addObject("listaPostagens", listaPostagens);
        modelAndView.addObject("postagem", postagem);
        modelAndView.addObject("umUsuario", umUsuario);
        modelAndView.addObject("curtidasDoUsuario", curtidasDoUsuario);
        return modelAndView;
    }

    @GetMapping("/novo")
    public ModelAndView novo(){
        var postagem = new Postagem();
        var usuarios = usuarioService.getAll();
        return new ModelAndView("postagem/form", "postagem", postagem).addObject("usuarios", usuarios);
    }

    @PostMapping
    public ModelAndView save(Postagem postagem, @AuthenticationPrincipal OAuth2User principal ){

        String emailLogado = principal.getAttribute("preferred_username").toString();
        Usuario usuarioLogado = usuarioService.findByEmail(emailLogado);

        postagem.setUsuario(usuarioLogado);
        
        service.save(postagem);
        
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete (@PathVariable("id") long id) {
        var umaPostagem = service.getById(id);
        if (umaPostagem != null) {
            service.delete(id);
        }
        return new ModelAndView("redirect:/");
    }

 @PostMapping("/like/{id}")
    public String curtir(@PathVariable("id") long id, @AuthenticationPrincipal OAuth2User principal) {
        var postagem = service.getById(id);
        String emailLogado = principal.getAttribute("preferred_username");
        var usuario = usuarioService.findByEmail(emailLogado);

        var curtidaOpcao = curtidaRepository.findByPostagemAndUsuario(postagem,usuario);
        if (curtidaOpcao.isPresent()) {
            curtidaRepository.delete(curtidaOpcao.get());
            postagem.setCurtidas(postagem.getCurtidas()-1);
        } else {
            Curtida curtida = new Curtida();
            curtida.setPostagem(postagem);
            curtida.setUsuario(usuario);
            curtidaRepository.save(curtida);
            postagem.setCurtidas(postagem.getCurtidas()+1);
        }
service.save(postagem);
        return "redirect:/";
    }
}
