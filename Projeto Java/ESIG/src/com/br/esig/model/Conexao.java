package com.br.esig.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.br.esig.controller.IDataOperations;

public class Conexao implements IDataOperations{
	private static final String url = "jdbc:postgresql://localhost:5432/ESIG";
	private static final String user = "postgres";
	private static final String password = "1234";
	private ArrayList<Funcionario> listaFuncionarios;
	private Set<Funcionario> listaFuncionariosProventos;
	

	public void connect() throws Exception{

		Connection con = DriverManager.getConnection(url,user,password);
		if(con !=null) {
			JOptionPane.showMessageDialog(null, "Conectado");
			listaFuncionarios = new ArrayList<Funcionario>();
			listaFuncionariosProventos = new HashSet<Funcionario>();
		}
	}

	public DefaultTableModel getData() throws Exception { // CARREGAR
		Funcionario novoFuncionario;

		String query = "select p.\"ID\",p.\"Nome\", c.\"Nome_Cargo\", v.\"Valor\", v.\"Tipo\" from public.\"Pessoa\" p\r\n"
				+ "inner join public.\"Cargo\" c on p.\"Cargo_ID\" = c.\"ID\"\r\n"
				+ "inner join public.\"Cargo_Vencimentos\" cv on c.\"ID\" = cv.\"Cargo_ID\"\r\n"
				+ "inner join public.\"Vencimentos\" v on cv.\"Vencimento_ID\" = v.\"ID\" order by p.\"ID\"";
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
				listaFuncionarios.add(novoFuncionario);
				
				

				
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelTable;
	}

	public void saveData() {


		try (Connection connection = DriverManager.getConnection(url, user, password)){
			PreparedStatement st = connection.prepareStatement("INSERT INTO pessoa_salario_consolidado (pessoa_id, nome_pessoa, nome_cargo, salario) VALUES (?, ?, ?, ?)"+"ON CONFLICT (pessoa_id) DO UPDATE SET salario = EXCLUDED.salario");

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
		double proventosFuncionario = 0;
		Set<Funcionario> listaFuncionarioPK = new HashSet<>(listaFuncionarios);
		

		for(Funcionario pk : listaFuncionarioPK) {
			
			for (Funcionario f1: listaFuncionarios.stream().filter(lista -> lista.getId().equals(pk.getId()))
				    .collect(Collectors.toList())) {
				if(f1.getTipo().toUpperCase().equals("CREDITO")) {	
					proventosFuncionario += f1.getSalario();
				} else {
					proventosFuncionario -= f1.getSalario();
				}
				
			}
			
			Funcionario funcionarioAtualizado = new Funcionario(pk.getId(), pk.getNome(), pk.getCargo(), String.valueOf(proventosFuncionario), "PROVENTOS");
		    listaFuncionariosProventos.add(funcionarioAtualizado);
		    proventosFuncionario = 0;
		    			
		}		
	    	
	}


	public DefaultTableModel getResultData() throws Exception { // CARREGAR

		String query = "select pessoa_id, nome_pessoa, nome_cargo, salario from public.\"pessoa_salario_consolidado\""+"order by pessoa_id";
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

