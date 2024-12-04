package br.univille.fso2024a.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.fso2024a.entity.Notificacao;
import br.univille.fso2024a.entity.Usuario;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    List<Notificacao> findByUsuarioAndLidaFalse(Usuario usuario);
}
