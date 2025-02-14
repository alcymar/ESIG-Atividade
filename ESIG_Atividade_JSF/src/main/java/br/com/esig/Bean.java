package br.com.esig;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.esig.DAO.DAO;
import br.com.esig.entidades.Pessoa_Vencimento;
import br.com.esig.entidades.Pessoa_Salario_Consolidado_JSF;

@ManagedBean(name = "bean")
@ViewScoped
public class Bean {
	
	private List<Pessoa_Vencimento> listaVencimentos = new ArrayList<Pessoa_Vencimento>();
	private DAO<Pessoa_Vencimento> daoVencimentos = new DAO<Pessoa_Vencimento>();
	
	private List<Pessoa_Salario_Consolidado_JSF> listaProventos = new ArrayList<Pessoa_Salario_Consolidado_JSF>();
	private Pessoa_Salario_Consolidado_JSF pessoaSalarioOBJ = new Pessoa_Salario_Consolidado_JSF(); //Usado para salvar
	private DAO<Pessoa_Salario_Consolidado_JSF> daoProventos = new DAO<Pessoa_Salario_Consolidado_JSF>();
	
	
	public List<Pessoa_Vencimento> getListaVencimentos(){
		listaVencimentos = daoVencimentos.getVencDataList(Pessoa_Vencimento.class);
		return listaVencimentos;
	}
	
	public List<Pessoa_Salario_Consolidado_JSF> getListaProventos() {
		listaProventos = daoProventos.getDataList(Pessoa_Salario_Consolidado_JSF.class);
		return listaProventos;
	}
	
	
	public void setListaVencimentos(List<Pessoa_Vencimento> listaVencimentos) {
		this.listaVencimentos = listaVencimentos;
	}


	public void setListaProventos(List<Pessoa_Salario_Consolidado_JSF> listaProventos) {
		this.listaProventos = listaProventos;
	}


	public String salvar() {
		daoProventos.salvar(pessoaSalarioOBJ);
		return "";
	}
	
	public String salvarlistaProventos() {
		daoProventos.salvarLista(listaProventos);
		return "";
	}
	
	public String carregar() {
		daoVencimentos.getVencDataList(Pessoa_Vencimento.class);
		return "";
	}
	
	
	public Pessoa_Salario_Consolidado_JSF getPessoaSalarioOBJ() {
		return pessoaSalarioOBJ;
	}


	public void setPessoaSalarioOBJ(Pessoa_Salario_Consolidado_JSF pessoaSalarioOBJ) {
		this.pessoaSalarioOBJ = pessoaSalarioOBJ;
	}


	public DAO<Pessoa_Salario_Consolidado_JSF> getDao() {
		return daoProventos;
	}


	public void setDao(DAO<Pessoa_Salario_Consolidado_JSF> dao) {
		this.daoProventos = dao;
	}
	
	public String calcular() {
		double proventosFuncionario = 0;
		Set<Pessoa_Vencimento> listaVencimentosPK = new HashSet<>(listaVencimentos);
		

		for(Pessoa_Vencimento pk : listaVencimentosPK) {
			
			for (Pessoa_Vencimento f1: listaVencimentos.stream().filter(lista -> lista.getId().equals(pk.getId()))
				    .collect(Collectors.toList())) {
				if(f1.getTipo().toUpperCase().equals("CREDITO")) {	
					proventosFuncionario += f1.getValor();
				} else {
					proventosFuncionario -= f1.getValor();
				}
				
			}
			
			Pessoa_Salario_Consolidado_JSF funcionarioAtualizado = new Pessoa_Salario_Consolidado_JSF(pk.getId(), pk.getNome(), pk.getNome_Cargo(), proventosFuncionario);
			listaProventos.add(funcionarioAtualizado);
		    proventosFuncionario = 0;
		    			
		}
		return "";
	}
	
	

	
	
	

	
	

}
