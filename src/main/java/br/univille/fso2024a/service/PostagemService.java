package br.univille.fso2024a.service;

import java.util.List;
import br.univille.fso2024a.entity.Postagem;
import br.univille.fso2024a.entity.Usuario;

public interface PostagemService {
    List<Postagem> getAll();
    Postagem save(Postagem postagem);
    Postagem delete(long id);
    Postagem getById(long id);
    List<Postagem> getByUsuario(Usuario usuario);
    List<Postagem> getByTermo(String termo);
}
