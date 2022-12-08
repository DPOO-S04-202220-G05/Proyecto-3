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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Analizador.equipo;
import Controller.controller;

public class menuEstadisticas {
    
    private JFrame window;

    public menuEstadisticas(controller controller, equipo equipo) {
        window = new JFrame();
        window.setTitle("USER TYPE");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(350, 400);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        JLabel label = new JLabel();
        label.setText("¿Que estadisticas quiere ver?");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.insets = new Insets(0, 0, 50, 0);
        panel.add(label, layout);

        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 1;
        layout.insets = new Insets(0, 0, 50, 0);

        JButton posTable = new JButton("Tabla de posiciones");
        panel.add(posTable, layout);
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 2;
        posTable.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                PositionsTable positionsTable;
                try {
                    positionsTable = new PositionsTable(controller, equipo);
                    positionsTable.show();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                window.setVisible(false);
            }
            
        });
        
        
        JButton bestTeam = new JButton("Mejor Equipo por Fecha");
        panel.add(bestTeam, layout);
        bestTeam.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                bestTeam ventanaMejoresEquipos = null;
				try {
					ventanaMejoresEquipos = new bestTeam(controller, equipo);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					PopUps popup = new PopUps();
					popup.errorMejorEquipo();
				}
                ventanaMejoresEquipos.show();
                window.setVisible(false);
            }
            
        });

        JButton bestWorstPlayer = new JButton("Jugador con más y menos puntos");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 3;
        panel.add(bestWorstPlayer, layout);
        bestWorstPlayer.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	try 
            	{
            		PopUps popUp = new PopUps();
            		ArrayList<String> JugadorMenosyMas = new  ArrayList<String>();
            		JugadorMenosyMas = controller.jugadorconMasyMenosPuntos(equipo);
            		
            		popUp.mejorYpeorJugador(JugadorMenosyMas.get(0), JugadorMenosyMas.get(1), JugadorMenosyMas.get(2), JugadorMenosyMas.get(3));
            		
            		
            		
				} 
            	
            	catch (IOException e1) 
            	
            	{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
             
            }
            
        });

        JButton returnButton = new JButton("Volver");
        returnButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            	MenuUser anterior;
                anterior = new MenuUser(controller, equipo);
                anterior.show();
                window.setVisible(false);
            }
        });
        
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 4;
        layout.insets = new Insets(0, 0, 50, 0);

        panel.add(returnButton, layout);

        window.add(panel);
    }

    public void show() {
        window.setVisible(true);
    }

}
