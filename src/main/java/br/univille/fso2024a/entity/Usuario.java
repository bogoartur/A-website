package br.univille.fso2024a.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;


@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private long userid; 
    @Column(length = 1000, nullable = false)
    private String nome;
    @Column(unique = true)
    private String email;


    //private Date dataNasc;

    public Usuario() {}

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public long getUserid() {
        return userid;
    }
    public void setUserid(long userid) {
        this.userid = userid;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Postagem> postagens = new ArrayList<>();


    public List<Postagem> getPostagens() {
        return postagens;
    }

    public void setPostagens(List<Postagem> postagens) {
        this.postagens = postagens;
    }

    private String caminhoFotoPerfil;


    public String getCaminhoFotoPerfil() {
        return caminhoFotoPerfil;
    }

    public void setCaminhoFotoPerfil(String caminhoFotoPerfil) {
        this.caminhoFotoPerfil = caminhoFotoPerfil;
    }

    @ManyToMany
    @JoinTable(name = "seguindo", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "seguido_id"))
    private List<Usuario> seguindo = new ArrayList<>();

    public List<Usuario> getSeguindo() {
        return seguindo;
    }

    public void setSeguindo(List<Usuario> seguindo) {
        this.seguindo = seguindo;
    }

    @ManyToMany(mappedBy = "seguindo")
    private List<Usuario> seguidores = new ArrayList<>();


    public List<Usuario> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Usuario> seguidores) {
        this.seguidores = seguidores;
    }
    
}
