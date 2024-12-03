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

import br.univille.fso2024a.entity.Comentario;
import br.univille.fso2024a.entity.Curtida;
import br.univille.fso2024a.entity.Postagem;
import br.univille.fso2024a.entity.Usuario;
import br.univille.fso2024a.repository.ComentarioRepository;
import br.univille.fso2024a.repository.CurtidaRepository;
import br.univille.fso2024a.service.ComentarioService;
import br.univille.fso2024a.service.NotificacaoService;
import br.univille.fso2024a.service.PostagemService;
import br.univille.fso2024a.service.UsuarioService;
import br.univille.fso2024a.service.impl.NotificacaoServiceImpl;
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

    @Autowired
    private NotificacaoServiceImpl notificacaoService;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    public ModelAndView index(@AuthenticationPrincipal OAuth2User principal) {
        var listaPostagens = service.getAll();
        var postagem = new Postagem();
        
        String emailLogado = principal.getAttribute("preferred_username");
        String nomeLogado = principal.getAttribute("name");


        Usuario usuarioLogado = usuarioService.findByEmail(emailLogado);

        if (usuarioLogado == null) {
            usuarioLogado = new Usuario(nomeLogado, emailLogado);
            usuarioService.save(usuarioLogado);
        }
        Set<Long> curtidasDePostagens = curtidaRepository.findByUsuario(usuarioLogado)
        .stream()
        .filter(curtida -> curtida.getPostagem() != null)  // Only for posts
        .map(curtida -> curtida.getPostagem().getId())
        .collect(Collectors.toSet());
    
    Set<Long> curtidasDeComentarios = curtidaRepository.findByUsuario(usuarioLogado)
        .stream()
        .filter(curtida -> curtida.getComentario() != null)  // Only for comments
        .map(curtida -> curtida.getComentario().getId())
        .collect(Collectors.toSet());
    

        ModelAndView modelAndView = new ModelAndView("home/index");

        modelAndView.addObject("listaPostagens", listaPostagens);
        modelAndView.addObject("postagem", postagem);
        modelAndView.addObject("usuarioLogado", usuarioLogado);
        modelAndView.addObject("curtidasDePostagens", curtidasDePostagens);
        modelAndView.addObject("curtidasDeComentarios", curtidasDeComentarios);
        return modelAndView;
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

 @PostMapping("/like/postagem/{id}")
    public String curtir(@PathVariable("id") long id, @AuthenticationPrincipal OAuth2User principal) {
        var postagem = service.getById(id);
        String emailLogado = principal.getAttribute("preferred_username");
        var usuario = usuarioService.findByEmail(emailLogado);
        var usuarioCurtido = postagem.getUsuario();
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

            if (!usuario.equals(postagem.getUsuario())) { //confere se usuario n eh o mesmo q postou
                notificacaoService.criarNotificacao(usuario.getNome() + " curtiu sua postagem", postagem.getUsuario(), postagem);
           }
        }
        service.save(postagem);
        return "redirect:/";
    }

    @PostMapping("/comentario/{postagemId}")
    public String comentar(@PathVariable("postagemId") Long postagemId,
                            @RequestParam("texto") String texto,
                            @AuthenticationPrincipal OAuth2User principal) {
        var postagem = service.getById(postagemId);
        String emailLogado = principal.getAttribute("preferred_username");
        var usuario = usuarioService.findByEmail(emailLogado);

        Comentario comentario = new Comentario();
       
        comentario.setUsuario(usuario);
        comentario.setTexto(texto);
        comentario.setPostagem(postagem);
        comentarioRepository.save(comentario);

        if (!usuario.equals(postagem.getUsuario())) { //confere se usuario n eh o mesmo q postou
        notificacaoService.criarNotificacao(usuario.getNome() + " comentou em sua postagem", postagem.getUsuario(), postagem);
        }
        
        return "redirect:/";
    }
    
    @PostMapping("/like/comentario/{id}")
    public String curtirComentario(@PathVariable("id") long id, @AuthenticationPrincipal OAuth2User principal) {
        var comentario = comentarioService.getById(id);
        String emailLogado = principal.getAttribute("preferred_username");
        var usuario = usuarioService.findByEmail(emailLogado);

        var curtidaOpcao = curtidaRepository.findByComentarioAndUsuario(comentario,usuario);
        if (curtidaOpcao.isPresent()) {
            curtidaRepository.delete(curtidaOpcao.get());
            comentario.setCurtidas(comentario.getCurtidas()-1);
        } else {
            Curtida curtida = new Curtida();
            curtida.setComentario(comentario);
            curtida.setUsuario(usuario);
            curtidaRepository.save(curtida);
            comentario.setCurtidas(comentario.getCurtidas()+1);

            if (!usuario.equals(comentario.getUsuario())) { //confere se usuario n eh o mesmo q postou
                notificacaoService.criarNotificacaoComentario(usuario.getNome() + " curtiu seu comentário", comentario.getUsuario(), comentario);
           }
        }
        comentarioService.save(comentario);
        return "redirect:/";
    }
}
