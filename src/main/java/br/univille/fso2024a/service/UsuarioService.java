package br.univille.fso2024a.service;

import java.util.List;
import br.univille.fso2024a.entity.Usuario;

public interface UsuarioService {
    List<Usuario> getAll();
    Usuario save(Usuario usuario);
    Usuario delete(long userid);
    Usuario getById(long userid);
    Usuario findByEmail(String email);

}
