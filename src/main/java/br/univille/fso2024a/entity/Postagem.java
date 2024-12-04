package br.univille.fso2024a.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    private int curtidas = 0;
    
    public int getCurtidas() {
        return curtidas;
    }
    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    @Column(length = 240, nullable = false)
    private String texto;
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Column(name = "criada_em")
    private LocalDateTime criadaEm;
    @PrePersist
    public void prePersist() {
        if (criadaEm == null) {
            criadaEm = LocalDateTime.now();
        }
    }

    public LocalDateTime getcriadaEm() {
        return criadaEm;
    }

    public void setcriadaEm(LocalDateTime criadaEm) {
        this.criadaEm = criadaEm;
    }
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "userid", nullable = false)
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void aumentaCurtidas() {
        this.curtidas++;
    }

    @OneToMany(mappedBy = "postagem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Comentario> comentarios;
    public Set<Comentario> getComentarios() {
        return comentarios;
    }
    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
