package br.univille.fso2024a.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.univille.fso2024a.entity.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
}
