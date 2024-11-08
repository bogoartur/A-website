package br.univille.fso2024a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.univille.fso2024a.entity.Usuario;
import br.univille.fso2024a.service.UsuarioService;
//import br.univille.fso2024a.service.UsuarioServiceImpl;

@Controller
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private UsuarioService usuarioService;

   // @Autowired
    //private UsuarioService usuarioService;

    @GetMapping("/login")
    public ModelAndView mostrarLogin() {
        return new ModelAndView("auth/login");
    }

    @GetMapping("/registro")
    public ModelAndView mostrarRegistro() {
        ModelAndView modelAndView = new ModelAndView("auth/registro");
        modelAndView.addObject("usuario", new Usuario());
        return modelAndView;
    }

    @PostMapping("/registro")
    public ModelAndView registrarUsuario(Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
        return new ModelAndView("redirect:/auth/login");
    }

    @PostMapping("login")
    public ModelAndView loginUsuario(@RequestParam String arroba, @RequestParam String senha) {
        boolean eValido = usuarioService.validaUsuario(arroba, senha);
        if (eValido) {
            return new ModelAndView("redirect:/homepage");
        }
        return new ModelAndView("auth/login", "error", "Credenciais invalidas");
    }
}
