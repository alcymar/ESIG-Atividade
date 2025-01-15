package com.br.esig.model;

import java.util.ArrayList;

public class Funcionario {
	private Integer id;
	private String nome;
	private String cargo;
	private Double salario;
	private ArrayList<Funcionario> listaFuncionarios;
	
	public Funcionario(Integer pID, String pNome, String pCargo, String pSalario) {
		this.id = pID;
		this.nome = pNome;
		this.cargo = pCargo;
		this.salario = Double.parseDouble(pSalario);
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
	
	public void calculaSalario(Funcionario pFuncionario, String pTipo) {

		listaFuncionarios.add(pFuncionario);	


		for(Funcionario f : listaFuncionarios){

			if((f.getId().equals(pFuncionario.getId()) && pTipo.equals("CREDITO"))) {
				f.setSalario(f.getSalario() + pFuncionario.getSalario());
				break;
			}

			if((f.getId().equals(pFuncionario.getId()) && pTipo.equals("DEBITO"))) {

				if(f.getSalario() > pFuncionario.getSalario()) {
					f.setSalario(f.getSalario() - pFuncionario.getSalario());
					break;
				}

			}

		}

	}


}
