package br.com.esig;

import javax.swing.table.DefaultTableModel;

public interface IDataOperations {
	
	public void connect() throws Exception;
	
	public String getData() throws Exception;
	
	public void getResultData() throws Exception;
	
	public void saveData();

}
