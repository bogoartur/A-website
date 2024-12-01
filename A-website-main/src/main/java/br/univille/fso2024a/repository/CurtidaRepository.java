package br.univille.fso2024a.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.univille.fso2024a.entity.Curtida;
import br.univille.fso2024a.entity.Postagem;
import br.univille.fso2024a.entity.Usuario;

public interface CurtidaRepository extends JpaRepository<Curtida, Long> {
    Optional<Curtida> findByPostagemAndUsuario(Postagem postagem, Usuario usuario);
    List<Curtida> findByUsuario(Usuario usuario);
}
