package Interfaz;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Controller.controller;

public class showCalendar {
	private JFrame window;
	
	public showCalendar(controller controller) throws IOException{
		 
		 window = new JFrame();
	     window.setTitle("Calendario");
	     window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	     window.setSize(600, 600);
	     window.setLocationRelativeTo(null);

	     JPanel panel = new JPanel();
	     panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
	     panel.setBackground(Color.WHITE);
	     panel.setLayout(new GridBagLayout());
	     GridBagConstraints layout = new GridBagConstraints();

	     JLabel titulo = new JLabel();
	     titulo.setText("Calendario");
	     layout.fill = GridBagConstraints.NORTH;
	     layout.gridx = 0;
	     layout.gridy = -2;
	     panel.add(titulo,layout);
	         
	       	ArrayList<String[]> calendar = controller.mostrarCalendario();
	       	
	       	String[] titulos = {"Equipo Local" , "Equipo Visitante" , "Fecha Dias" , "Hora" , "Fecha "};
	        calendar.add(0, titulos);
	        Object[][] matriz = new Object[calendar.size()][];
	      
	        for(int i = 0; i<calendar.size(); i++)
			{
				String[] dato = calendar.get(i);
				Object[] fila1 = {dato[0],dato[1],dato[2],dato[3],dato[4]};
	        	matriz[i] = fila1;
			}
	        
	        
	        
	        DefaultTableModel dm = new DefaultTableModel();
	        dm.setDataVector(matriz, new Object[]{"String", "String", "String","String","String"});

	        JTable table = new JTable(dm);
	        table.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
	        table.setGridColor(Color.BLACK);
	        panel.add(table, layout);
	        JButton returnButton = new JButton("Volver");
	        returnButton.addActionListener(new ActionListener(){

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	MenuUser anterior;
	                anterior = new MenuUser(controller);
	                anterior.show();
	                window.setVisible(false);
	            }
	            
	        });
	        
	        layout.fill = GridBagConstraints.HORIZONTAL;
	        layout.gridx = 0;
	        layout.gridy = 8;
	        layout.insets = new Insets(0, 0, 50, 0);

	        panel.add(returnButton, layout);

	        window.add(panel);
	    }

	    public void show() {
	        window.setVisible(true);
	    }

}

