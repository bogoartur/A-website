package br.univille.fso2024a.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.fso2024a.entity.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
    
}
