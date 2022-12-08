package Interfaz;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.*;

import Controller.controller;
import Analizador.jugador;

public class ShowTeam {
	
	private JFrame window;
	
	public ShowTeam(controller controller) throws IOException {
		window = new JFrame();
        window.setTitle("MY TEAM");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(600, 600);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.insets = new Insets(0, 50, 50, 50);
        
        String userName = controller.getUserName();
        ArrayList<jugador> players = controller.consultarEquipo(userName);
        
        String col[] = {"POS", "NOMBRE"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        JTable table = new JTable(tableModel);
        int count = 1;
        
        for(jugador jugador:players) {
        	Object[] info = {count, jugador.getNombre(), jugador.getPosicion()};
        	tableModel.addRow(info);
            count++;
        }
        
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 1;
        panel.add(table, layout);

        JButton returnButton = new JButton("Volver");
        returnButton.addActionListener((ActionListener) new ActionListener(){

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
        layout.gridy = 2;
        layout.insets = new Insets(0, 0, 50, 0);

        panel.add(returnButton, layout);
   
        window.add(panel);
	}
	
	public void show() {
        window.setVisible(true);
    }

}
