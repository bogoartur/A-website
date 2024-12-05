package br.univille.fso2024a.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Curtida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "postagem_id", nullable = true)
    private Postagem postagem;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Getters e setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Postagem getPostagem() {
        return postagem;
    }

    public void setPostagem(Postagem postagem) {
        this.postagem = postagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name = "comentario_id", nullable = true)
    private Comentario comentario;

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

}
