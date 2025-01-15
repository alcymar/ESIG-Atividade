package com.br.esig.controller;

import javax.swing.table.DefaultTableModel;

public interface IDataOperations {
	
	public void connect() throws Exception;
	
	public DefaultTableModel getData() throws Exception;
	
	public DefaultTableModel getResultData() throws Exception;
	
	public void saveData();

}
