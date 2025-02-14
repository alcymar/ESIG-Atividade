package br.com.esig.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@org.hibernate.annotations.Entity
@Entity
@Table(name = "Pessoa")
public class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "Cidade", length = 50, nullable = false)
    private String cidade;

    @Column(name = "Email", length = 50)
    private String email;

    @Column(name = "CEP", length = 50)
    private String cep;

    @Column(name = "Endereco", length = 100)
    private String endereco;

    @Column(name = "Pais", length = 50)
    private String pais;

    @Column(name = "Usuario", length = 30)
    private String usuario;

    @Column(name = "Telefone", length = 50)
    private String telefone;

    @Column(name = "Data_Nascimento")
    private java.time.LocalDateTime dataNascimento;

    @ManyToOne
    @JoinColumn(name = "Cargo_ID", referencedColumnName = "ID")
    private Cargo cargo;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public java.time.LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(java.time.LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    public Pessoa() {}
    
}

