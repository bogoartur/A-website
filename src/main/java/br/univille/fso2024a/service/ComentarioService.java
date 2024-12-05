package br.univille.fso2024a.service;

import java.util.List;
import br.univille.fso2024a.entity.Comentario;


public interface ComentarioService {
    List<Comentario> getAll();
    Comentario save(Comentario postagem);
    Comentario getById(long id);
}
