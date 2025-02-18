package br.com.esig.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "pessoa_salario_consolidado_jsf")

public class Pessoa_Salario_Consolidado_JSF{
	
    @Id
    @Column(name="id")  
	private BigInteger id;
    
    @Column(name="nome_pessoa")  
	private String nome_pessoa;
    
    @Column(name="nome_cargo")
	private String nome_cargo;
    
    @Column(name="salario")
	private Double salario;
	
	public Pessoa_Salario_Consolidado_JSF() {}
	
	
	public BigInteger getId() {
		return id;
	}
	
	public void setId(BigInteger id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome_pessoa;
	}
	public void setNome(String nome) {
		this.nome_pessoa = nome;
	}
	public String getCargo() {
		return nome_cargo;
	}
	public void setCargo(String cargo) {
		this.nome_cargo = cargo;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}


	@Override
	public int hashCode() {
		return Objects.hash(nome_cargo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa_Salario_Consolidado_JSF other = (Pessoa_Salario_Consolidado_JSF) obj;
		return Objects.equals(nome_cargo, other.nome_cargo);
	}
	
	public Pessoa_Salario_Consolidado_JSF(BigInteger pID, String pNome, String pCargo, Double pSalario) {
		this.id = pID;
		this.nome_pessoa = pNome;
		this.nome_cargo = pCargo;
		this.salario = pSalario;
	}
	
	
	
}
