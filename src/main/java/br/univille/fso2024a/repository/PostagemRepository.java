package br.univille.fso2024a.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.fso2024a.entity.Postagem;
import br.univille.fso2024a.entity.Usuario;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
    List<Postagem> findByUsuarioOrderByCriadaEmDesc(Usuario usuario);
}
