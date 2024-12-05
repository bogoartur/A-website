package br.univille.fso2024a.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.univille.fso2024a.entity.Postagem;
import br.univille.fso2024a.entity.Usuario;
import br.univille.fso2024a.repository.PostagemRepository;
import br.univille.fso2024a.service.PostagemService;


@Service
public class PostagemServiceImpl implements PostagemService {
    @Autowired
    private PostagemRepository repository;

    @Override
    public List<Postagem> getAll() {
        return repository.findAll(Sort.by(Sort.Order.desc("criadaEm")));
    }

    @Override
    public List<Postagem> getByUsuario(Usuario usuario) {
        return repository.findByUsuarioOrderByCriadaEmDesc(usuario);
    }

    @Override
    public Postagem save(Postagem postagem) {
        repository.save(postagem);
        return postagem;
    }

    @Override
    public Postagem delete(long id) {
        var retorno = repository.findById(id);
        if (retorno.isPresent()){
            repository.deleteById(id);
            return retorno.get();
        }
        return null;
    }

    @Override
    public Postagem getById(long id) {
        var retorno = repository.findById(id);
        if (retorno.isPresent()){
            return retorno.get();
        }
        return null;
    }

    @Override
    public List<Postagem> getByTermo(String termo) {
        var listaPostagens = repository.findAll(Sort.by(Sort.Order.desc("criadaEm")));
        List<Postagem> resultadoBusca = new ArrayList<>();

        for(Postagem post : listaPostagens) {
            if(post.getTexto().toLowerCase().contains(termo.toLowerCase())) {
                resultadoBusca.add(post);
            }
        }
 
        return resultadoBusca;
    }
}
