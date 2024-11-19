package br.univille.fso2024a.service;

import br.univille.fso2024a.entity.Usuario;
import br.univille.fso2024a.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String arroba) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByArroba(arroba)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o arroba: " + arroba));

        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getArroba())
                .password(usuario.getSenha())  // A senha deve estar codificada (usando BCrypt, por exemplo)
                .roles("USER")                 // Você pode definir as roles aqui (ex.: USER, ADMIN)
                .build();
    }
}
