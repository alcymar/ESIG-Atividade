package com.br.esig.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.*;

import com.br.esig.model.Conexao;

public class Visual {
	private JFrame frameTela;
	private JButton btnConnect;
	private JButton btnLoad;
	private JButton btnCalculate;
	private JTable  tableResult;
	private JTable  tableResultCalc;
	private JPanel  tablePanel;
	
	private JPanel  tablePanelAfterCalc;
	
	private Conexao DBObject;
	private final String[] titles = {"Nome","Nome_Cargo","Valor","Tipo"};

	public Visual() {
		
		try {
	
			DBObject = new Conexao();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

		tablePanelAfterCalc = new JPanel();
		tableResultCalc = new JTable();
		tableResultCalc.setSize(700, 400);
		tablePanelAfterCalc.add(new JScrollPane(tableResultCalc));
		
		tablePanel = new JPanel();
		tableResult = new JTable();
		tableResult.setSize(1000, 400);
		tablePanel.add(new JScrollPane(tableResult));
		
		
		frameTela = new JFrame("ESIG - Atividade");

		btnConnect = new JButton("Conectar");
		btnConnect.setToolTipText("Conectar");

		btnCalculate = new JButton("Calcular");
		btnCalculate.setToolTipText("Calcular");
		
		btnLoad = new JButton("Carregar");
		btnLoad.setToolTipText("Carregar");

		frameTela.setLayout(null);
		frameTela.setSize(1000, 620);
		frameTela.setVisible(true);
		frameTela.add(btnConnect);
		frameTela.add(btnLoad);
		frameTela.add(btnCalculate);
		frameTela.add(tablePanel);
		frameTela.add(tablePanelAfterCalc);
		frameTela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		btnConnect.setBounds(10, 1, 100, 30);
		btnLoad.setBounds(110, 1, 100, 30);
		btnCalculate.setBounds(210, 1, 100, 30);
		
		tablePanel.setBounds(1, 100, 480, 720);
		tablePanelAfterCalc.setBounds(500, 100, 480, 720);


		///////////// Eventos dos botões /////////////////
		btnConnect.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent me){
				try {

					DBObject.connect();

				} catch (Exception e) {

					e.printStackTrace();
				}
			}});

		btnCalculate.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent me){
				try {

					DBObject.calculaSalario();
					DBObject.saveData();
					tableResultCalc.setModel(DBObject.getResultData());
					

				} catch (Exception e) {

					e.printStackTrace();
				}	
				
			}});
		
		btnLoad.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent me){
				try {

					tableResult.setModel(DBObject.getData());
					
				} catch (Exception e) {

					e.printStackTrace();
				}
			}});


	}


}

