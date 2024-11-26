package br.univille.fso2024a.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.univille.fso2024a.repository.UsuarioRepository;
import br.univille.fso2024a.service.UsuarioService;
import br.univille.fso2024a.entity.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;


    
    @Override
    public List<Usuario> getAll() {
        return repository.findAll();
    }

    @Override
    public Usuario save(Usuario usuario) {
        repository.save(usuario);
        return usuario;
    }

    @Override
    public Usuario delete(long userid) {
        var retorno = repository.findById(userid);
        if (retorno.isPresent()){
            repository.deleteById(userid);
            return retorno.get();
        }
        return null;
    }

    @Override
    public Usuario getById(long userid) {
        var retorno = repository.findById(userid);
        if (retorno.isPresent()){
            return retorno.get();
        }
        return null;
    }


}
