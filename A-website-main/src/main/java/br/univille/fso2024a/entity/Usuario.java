package br.univille.fso2024a.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

//import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private long userid; 
    @Column(length = 1000, nullable = false)
    private String nome;
    private String email;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date dataNasc;
    private String senha;
    
    //private Date dataNasc;

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
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    //public Date getDataNasc() {
      //  return dataNasc;
    //}
    //public void setDataNasc(Date dataNasc) {
       // this.dataNasc = dataNasc;
    //}
    
}
