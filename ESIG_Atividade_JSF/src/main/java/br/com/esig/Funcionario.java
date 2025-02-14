package br.com.esig;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;



public class Funcionario{
	private Integer id;
	private String nome;
	private String cargo;
	private Double salario;
	private String tipo; 
	private ArrayList<Funcionario> listaFuncionarios;
	
	
	public Funcionario() {}

	public ArrayList<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}


	public void setListaFuncionarios(ArrayList<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	
	
	public Funcionario(Integer pID, String pNome, String pCargo, String pSalario, String tipo) {
		this.id = pID;
		this.nome = pNome;
		this.cargo = pCargo;
		this.salario = Double.parseDouble(pSalario);
		this.tipo = tipo;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void conect() {
		
	}
	
	

}

