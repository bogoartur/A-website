package br.univille.fso2024a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.univille.fso2024a.entity.Comentario;
import br.univille.fso2024a.entity.Notificacao;
import br.univille.fso2024a.entity.Postagem;
import br.univille.fso2024a.entity.Usuario;
import br.univille.fso2024a.repository.NotificacaoRepository;
import br.univille.fso2024a.service.NotificacaoService;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {

    @Autowired
    private NotificacaoRepository repository;


    public void criarNotificacao(String mensagem, Usuario usuario, Postagem postagem) {
        Notificacao notificacao = new Notificacao();
        notificacao.setMensagem(mensagem);
        notificacao.setUsuario(usuario);
        notificacao.setPostagem(postagem);
        repository.save(notificacao);
    }

    public void criarNotificacaoComentario(String mensagem, Usuario usuario, Comentario comentario) {
        Notificacao notificacao = new Notificacao();
        notificacao.setMensagem(mensagem);
        notificacao.setUsuario(usuario);
        notificacao.setComentario(comentario);
        repository.save(notificacao);
    }

    @Override
    public List<Notificacao> getAll() {
        return repository.findAll(Sort.by(Sort.Order.desc("criadaEm")));
    }

    @Override
    public Notificacao save(Notificacao notificacao) {
        repository.save(notificacao);
        return notificacao;
    }

    @Override
    public Notificacao getById(Long id) {
        var retorno = repository.findById(id);
        if (retorno.isPresent()){
            return retorno.get();
        }
        return null;
    }

    public List<Notificacao> notificacoesNaoLidas(Usuario usuario){
        return  repository.findByUsuarioAndLidaFalse(usuario);
    }

    public void marcarComoLida(Long id) {
        var notificacao = repository.findById(id);
        if (notificacao.isPresent()) {
            notificacao.get().setLida(true);
            repository.save(notificacao.get());
        }
    }
}
