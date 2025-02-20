package br.com.esig.DAO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.esig.entidades.Pessoa_Vencimento;
import br.com.esig.entidades.Pessoa_Salario_Consolidado_JSF;
import br.com.jputil.JPUtil;


public class DAO<E> {

	

	public void salvarLista(List<Pessoa_Salario_Consolidado_JSF> entidades) {  // Saida -> Pessoa_Salario_consolidado
	    EntityManager entityManager = JPUtil.getEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
	    
	    try {
	        entityTransaction.begin();
	        
	        for (Pessoa_Salario_Consolidado_JSF entidade : entidades) {
	            entityManager.merge(entidade);
	        }
	        
	        entityTransaction.commit();
	    } catch (Exception e) {
	        entityTransaction.rollback();
	        throw e; // Repropaga a exceção para tratamento externo
	    } finally {
	        entityManager.close();
	    }
	}
	
	public void salvar(E entidade) {  // Saida -> Pessoa_Salario_consolidado
		EntityManager entityManager = JPUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.merge(entidade);
		entityTransaction.commit();
		entityManager.close();	
	}

	public List<Pessoa_Salario_Consolidado_JSF> getDataList(Class<E> entidade) {
		EntityManager entityManager = JPUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		
		List<Pessoa_Salario_Consolidado_JSF> resultData = entityManager.createQuery("SELECT p FROM Pessoa_Salario_Consolidado_JSF p ORDER BY p.id",Pessoa_Salario_Consolidado_JSF.class).getResultList();
		
		entityTransaction.commit();
		entityManager.close();	
		return resultData;
	}
	
	public List<Pessoa_Vencimento> getVencDataList(Class<E> entidade){
		Pessoa_Vencimento pessoaLida;
		List<Pessoa_Vencimento> resultData = new ArrayList<Pessoa_Vencimento>();
		
		
		EntityManager entityManager = JPUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		String sql = "select p.\"ID\",p.\"Nome\", c.\"Nome_Cargo\", v.\"Valor\", v.\"Tipo\" from public.\"Pessoa\" p\r\n"
				+ "inner join public.\"Cargo\" c on p.\"Cargo_ID\" = c.\"ID\"\r\n"
				+ "inner join public.\"Cargo_Vencimentos\" cv on c.\"ID\" = cv.\"Cargo_ID\"\r\n"
				+ "inner join public.\"Vencimentos\" v on cv.\"Vencimento_ID\" = v.\"ID\" order by p.\"ID\"";
		List<Object[]> resultados = entityManager.createNativeQuery(sql).getResultList();
		
		for (Object[] row : resultados) {
			
			pessoaLida = new Pessoa_Vencimento();
			pessoaLida.setId((BigInteger) row[0]);
			pessoaLida.setNome((String) row[1]);
			pessoaLida.setNome_Cargo((String) row[2]);
			pessoaLida.setValor((Float) row[3]);
			pessoaLida.setTipo((String) row[4]);
			
			resultData.add(pessoaLida);
			System.out.println("id:"+ row[0]+" Nome: " + row[1] + ", Cargo: " + row[2] + ", Salário: " + row[3]);
	
		}

		entityTransaction.commit();
		entityManager.close();
		return resultData;	
	}
	
	public void excluirPorId(E entidade) {  // Saida -> Pessoa_Salario_consolidado
		EntityManager entityManager = JPUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		
		Object pk = JPUtil.getPK(entidade);		
		entityManager.createNativeQuery("delete from pessoa_salario_consolidado_jsf where id = "+pk).executeUpdate();
		
		entityTransaction.commit();
		entityManager.close();	
	}
	
	
	
	

}
