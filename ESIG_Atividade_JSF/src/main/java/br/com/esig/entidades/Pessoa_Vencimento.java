package br.com.esig.entidades;


import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "view_pessoa_salario_consolidado")
public class Pessoa_Vencimento implements Serializable{
	private static final long serialVersionUID = 1L;
	
    @Id
    private BigInteger id; // Adicionando o ID como chave primária
	
    private String Nome;
    
    private String Nome_Cargo;

    private Float Valor;
    
    private String Tipo;
    
    
    public BigInteger getId() {
		return this.id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getNome() {
		return this.Nome;
	}
	public void setNome(String nome) {
		this.Nome = nome;
	}
	public String getNome_Cargo() {
		return this.Nome_Cargo;
	}
	public void setNome_Cargo(String nome_Cargo) {
		this.Nome_Cargo = nome_Cargo;
	}
	public Float getValor() {
		return this.Valor;
	}
	public void setValor(Float valor) {
		this.Valor = valor;
	}
	public String getTipo() {
		return this.Tipo;
	}
	public void setTipo(String tipo) {
		this.Tipo = tipo;
	}
	
	public Pessoa_Vencimento() {}
	
	public Pessoa_Vencimento(BigInteger id, String nome, String cargo, Float valor, String tipo) {
		this.id = id;
		this.Nome = nome;
		this.Nome_Cargo = cargo;
		this.Valor = valor;
		this.Tipo = tipo;
	}

	
	
	
	
	

}
