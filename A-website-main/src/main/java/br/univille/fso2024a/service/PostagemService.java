package br.univille.fso2024a.service;

import java.util.List;
import br.univille.fso2024a.entity.Postagem;

public interface PostagemService {
    List<Postagem> getAll();
    Postagem save(Postagem postagem);
    Postagem delete(long id);
    Postagem getById(long id);
}
