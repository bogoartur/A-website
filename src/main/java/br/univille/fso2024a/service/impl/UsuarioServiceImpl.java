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
    @Override
    public Usuario findByEmail(String email) {
        var retorno = repository.findByEmail(email);
        return retorno;
    }

    public void seguirUser(Long idSeguidor, Long idSeguido) {
        Usuario seguidor = repository.findById(idSeguidor).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        Usuario seguido = repository.findById(idSeguido).orElseThrow(() -> new RuntimeException("Usuuario não encontrado"));

        seguidor.getSeguindo().add(seguido);
        repository.save(seguidor);
    }

    public void deixarSeguirUser(Long idSeguidor, Long idSeguido) {
        Usuario seguidor = repository.findById(idSeguidor).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        Usuario seguido = repository.findById(idSeguido).orElseThrow(() -> new RuntimeException("Usuuario não encontrado"));

        seguidor.getSeguindo().remove(seguido);
        repository.save(seguidor);
    }
}
