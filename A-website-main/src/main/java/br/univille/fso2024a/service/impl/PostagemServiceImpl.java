package br.univille.fso2024a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.univille.fso2024a.entity.Postagem;
import br.univille.fso2024a.repository.PostagemRepository;
import br.univille.fso2024a.service.PostagemService;


@Service
public class PostagemServiceImpl implements PostagemService {
    @Autowired
    private PostagemRepository repository;

    @Override
    public List<Postagem> getAll() {
        return repository.findAll(Sort.by(Sort.Order.desc("createdAt")));
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
}
