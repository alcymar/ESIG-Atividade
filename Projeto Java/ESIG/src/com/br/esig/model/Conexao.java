package com.br.esig.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.br.esig.controller.IDataOperations;

public class Conexao implements IDataOperations{
	private static final String url = "jdbc:postgresql://localhost:5432/ESIG";
	private static final String user = "postgres";
	private static final String password = "1234";

	private ArrayList<Funcionario> listaFuncionariosCredito;
	private ArrayList<Funcionario> listaFuncionariosDebito;
	private ArrayList<Funcionario> listaFuncionariosProventos;
	private Set<Funcionario> listaFuncionarioPK = new HashSet<Funcionario>(); // utilizar o Set


	public void connect() throws Exception{

		Connection con = DriverManager.getConnection(url,user,password);
		if(con !=null) {
			JOptionPane.showMessageDialog(null, "Conectado");
			listaFuncionariosCredito = new ArrayList<Funcionario>();
			listaFuncionariosDebito = new ArrayList<Funcionario>();
			listaFuncionariosProventos = new ArrayList<Funcionario>();
		}
	}

	public DefaultTableModel getData() throws Exception { // CARREGAR
		Funcionario novoFuncionario;

		String query = "select p.\"ID\",p.\"Nome\", c.\"Nome_Cargo\", v.\"Valor\", v.\"Tipo\" from public.\"Pessoa\" p\r\n"
				+ "inner join public.\"Cargo\" c on p.\"Cargo_ID\" = c.\"ID\"\r\n"
				+ "inner join public.\"Cargo_Vencimentos\" cv on c.\"ID\" = cv.\"Cargo_ID\"\r\n"
				+ "inner join public.\"Vencimentos\" v on cv.\"Vencimento_ID\" = v.\"ID\" order by v.\"Tipo\"";
		DefaultTableModel modelTable = new DefaultTableModel();



		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			ResultSet rs = preparedStatement.executeQuery();

			modelTable.addColumn("ID");
			modelTable.addColumn("Nome");
			modelTable.addColumn("Cargo");
			modelTable.addColumn("Valor");
			modelTable.addColumn("Tipo");

			while(rs.next()) {
				modelTable.addRow(new Object[] {
						rs.getInt("ID"),
						rs.getString("Nome"),
						rs.getString("Nome_Cargo"),
						rs.getString("Valor"),
						rs.getString("Tipo")
				});

				
				novoFuncionario = new Funcionario(rs.getInt("ID"), rs.getString("Nome"), rs.getString("Nome_Cargo"), rs.getString("Valor"), rs.getString("Tipo"));
				listaFuncionarioPK.add(novoFuncionario);

				if(novoFuncionario.getTipo().equals("CREDITO")) {
					listaFuncionariosCredito.add(novoFuncionario);
				}

				if(novoFuncionario.getTipo().equals("DEBITO")) {
					listaFuncionariosDebito.add(novoFuncionario);
				}

				
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelTable;
	}

	public void saveData() {


		try (Connection connection = DriverManager.getConnection(url, user, password)){
			PreparedStatement st = connection.prepareStatement("INSERT INTO pessoa_salario_consolidado (pessoa_id, nome_pessoa, nome_cargo, salario) VALUES (?, ?, ?, ?)");

			for(Funcionario f : listaFuncionariosProventos){

				st.setInt(1, f.getId());
				st.setString(2, f.getNome());
				st.setString(3, f.getCargo());
				st.setLong(4, f.getSalario().longValue());
				st.executeUpdate();

			}
			st.close();


		} catch (SQLException e) {
			e.printStackTrace();
		}

	}



	public void calculaSalario() {
		double totalCredito = 0;
		double totalDebito  = 0;
		double proventosFuncionario = 0;
		
		for(Funcionario pk : listaFuncionarioPK) {
            
			
			for(Funcionario fCredito : listaFuncionariosCredito) {	

				if(fCredito.getId() == pk.getId()) {	
					totalCredito += fCredito.getSalario();
					break;
				}
			}
		
			
			for(Funcionario fDebito : listaFuncionariosDebito) {

				if(fDebito.getId() == pk.getId()) {	
					totalDebito += fDebito.getSalario();
					break;
				}
			}
			
			
			proventosFuncionario  = totalCredito - totalDebito;
			Funcionario funcionarioAtualizado = new Funcionario(pk.getId(), pk.getNome(), pk.getCargo(), String.valueOf(proventosFuncionario), "PROVENTOS");
		    listaFuncionariosProventos.add(funcionarioAtualizado);
		    
		    proventosFuncionario = 0;
			totalCredito = 0;
			totalDebito  = 0;
		    			
		}		
	    	
	}


	public DefaultTableModel getResultData() throws Exception { // CARREGAR

		String query = "select pessoa_id, nome_pessoa, nome_cargo, salario from public.\"pessoa_salario_consolidado\"";
		DefaultTableModel modelTable = new DefaultTableModel();



		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			ResultSet rs = preparedStatement.executeQuery();

			modelTable.addColumn("ID");
			modelTable.addColumn("Nome");
			modelTable.addColumn("Cargo");
			modelTable.addColumn("Salario");

			while(rs.next()) {
				modelTable.addRow(new Object[] {
						rs.getInt("pessoa_id"),
						rs.getString("nome_pessoa"),
						rs.getString("nome_cargo"),
						rs.getString("salario"),
				});
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelTable;
	}


}

