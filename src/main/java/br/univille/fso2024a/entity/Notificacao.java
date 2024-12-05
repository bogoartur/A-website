package br.univille.fso2024a.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    private boolean lida = false;

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name="postagem_id")
    private Postagem postagem;

    public Postagem getPostagem() {
        return postagem;
    }

    public void setPostagem(Postagem postagem) {
        this.postagem = postagem;
    }

    @ManyToOne
    @JoinColumn(name="comentario_id")
    private Comentario comentario;

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    @Column(name = "criada_em")
    private LocalDateTime criadaEm = LocalDateTime.now();
    @PrePersist
    public void prePersist(){
        if (criadaEm == null) {
            criadaEm = LocalDateTime.now();
        }
    }
    public LocalDateTime getCriadaEm() {
        return criadaEm;
    }

    public void setCriadaEm(LocalDateTime criadaEm) {
        this.criadaEm = criadaEm;
    }
}
