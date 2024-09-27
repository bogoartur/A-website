package br.univille.fso2024a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.fsoweb20242.entity.Cliente;
import br.univille.fsoweb20242.repository.ClienteRepository;
import br.univille.fsoweb20242.service.ClienteService;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
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
