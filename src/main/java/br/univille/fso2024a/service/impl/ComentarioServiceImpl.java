package br.univille.fso2024a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.fso2024a.entity.Comentario;
import br.univille.fso2024a.repository.ComentarioRepository;
import br.univille.fso2024a.service.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService {
    @Autowired
    private ComentarioRepository repository;

    @Override
    public Comentario getById(long id) {
        var retorno = repository.findById(id);
        if (retorno.isPresent()){
            return retorno.get();
        }
        return null;
    }

    @Override
    public Comentario save(Comentario comentario) {
        return repository.save(comentario);
    }

    @Override
    public List<Comentario> getAll() {
        return repository.findAll();
    }
    
}
