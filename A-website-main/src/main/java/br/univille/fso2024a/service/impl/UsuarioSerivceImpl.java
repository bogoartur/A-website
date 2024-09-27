package br.univille.fso2024a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.fso2024a.repository.UsuarioRepository;
import br.univille.fso2024a.service.UsuarioService;
import br.univille.fso2024a.entity.Usuario;

@Service
public class UsuarioSerivceImpl implements UsuarioService {
    
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
    public Usuario delete(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Usuario getById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }
}
