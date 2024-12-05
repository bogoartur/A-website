package br.univille.fso2024a.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.servlet.ModelAndView;

import br.univille.fso2024a.service.PostagemService;
import br.univille.fso2024a.service.UsuarioService;
import br.univille.fso2024a.service.impl.PostagemServiceImpl;
import br.univille.fso2024a.entity.Usuario;
import br.univille.fso2024a.repository.CurtidaRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

            @Autowired
    private CurtidaRepository curtidaRepository;

    @Autowired
    private UsuarioService service;

    @Autowired
    private PostagemServiceImpl postagemService;
    
    @GetMapping("/admin")
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

        return new ModelAndView("redirect:/usuarios/admin");
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

    @GetMapping("/perfil/{userid}")
    public ModelAndView perfil (@PathVariable("userid") long userid, @AuthenticationPrincipal OAuth2User principal) {
        var umUsuario = service.getById(userid);
        String emailLogado = principal.getAttribute("preferred_username");
        var usuarioLogado = service.findByEmail(emailLogado);
        var postagensUsuario = postagemService.getByUsuario(umUsuario);

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
        var usuariosSeguindo = usuarioLogado.getSeguindo();


        ModelAndView modelAndView = new ModelAndView("usuario/perfil");
        modelAndView.addObject("usuariosSeguindo", usuariosSeguindo);
        modelAndView.addObject("postagensUsuario", postagensUsuario);
        modelAndView.addObject("umUsuario", umUsuario);
        modelAndView.addObject("usuarioLogado", usuarioLogado);
        modelAndView.addObject("curtidasDePostagens", curtidasDePostagens);
        modelAndView.addObject("curtidasDeComentarios", curtidasDeComentarios);

        return modelAndView;
    }
}
