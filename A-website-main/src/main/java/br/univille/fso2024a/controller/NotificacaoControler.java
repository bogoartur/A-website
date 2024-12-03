package br.univille.fso2024a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.fso2024a.entity.Postagem;
import br.univille.fso2024a.entity.Usuario;
import br.univille.fso2024a.repository.ComentarioRepository;
import br.univille.fso2024a.repository.CurtidaRepository;
import br.univille.fso2024a.service.ComentarioService;
import br.univille.fso2024a.service.PostagemService;
import br.univille.fso2024a.service.impl.NotificacaoServiceImpl;
import br.univille.fso2024a.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/notificacao")
public class NotificacaoControler {
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private NotificacaoServiceImpl notificacaoService;

    @Autowired
    private PostagemService postagemService;

    @Autowired
    private CurtidaRepository curtidaRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ComentarioService comentarioService;


    @GetMapping
    public ModelAndView index(@AuthenticationPrincipal OAuth2User principal) {

        String emailLogado = principal.getAttribute("preferred_username");
var postagem = new Postagem();

        Usuario umUsuario = usuarioService.findByEmail(emailLogado);
        var listaPostagens = postagemService.getAll();
        var notificacoes = notificacaoService.getAll();
        
        ModelAndView modelAndView = new ModelAndView("notificacao/index");
        modelAndView.addObject("listaPostagens", listaPostagens);
        modelAndView.addObject("postagem", postagem);
        modelAndView.addObject("umUsuario", umUsuario);
        modelAndView.addObject("notificacoes", notificacoes);
        return modelAndView;
    }
}
