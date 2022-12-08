package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Analizador.equipo;
import Controller.controller;
import Analizador.jugador;

public class TeamStatistics {

    private JFrame window;
    
    public TeamStatistics(controller controller, equipo equipo){
        window = new JFrame();
        window.setTitle("TEAM STATISTICS");
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

        ArrayList<jugador> jugadores = controller.sortJugadoresUsuario(equipo);

        String col[] = {"POS", "NOMBRE", "PUNTOS", "POSICIÓN"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        JTable table = new JTable(tableModel);
        int count = 1;

        for(jugador jugador:jugadores) {
        	Object[] info = {count, jugador.getNombre(), jugador.getPuntos(), jugador.getPosicion()};
        	tableModel.addRow(info);
            count++;
        }

        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 1;
        panel.add(table, layout);

        JButton returnButton = new JButton("Volver");
        returnButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            	PositionsTable anterior;
                try {
                    anterior = new PositionsTable(controller);
                    anterior.show();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
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
