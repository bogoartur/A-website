package br.univille.fso2024a.service;

import java.util.List;

import br.univille.fso2024a.entity.Notificacao;

public interface NotificacaoService {
    List<Notificacao> getAll();
    Notificacao save(Notificacao notificacao);
    Notificacao getById(Long id);
}
