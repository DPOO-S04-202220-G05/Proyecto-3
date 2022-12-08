package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import Analizador.jugador;
import Controller.controller;

public class MenuUser {

    private JFrame window;

    public MenuUser(controller controller){
        window = new JFrame();
        window.setTitle("USER MENU");
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

        JButton newTeamButton = new JButton("CREAR UN NUEVO EQUIPO");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 1;
        panel.add(newTeamButton, layout);
        newTeamButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                NewTeam ventanaNuevoEquipo = new NewTeam(controller);
                ventanaNuevoEquipo.show();
                window.setVisible(false);
            }
            
        });

        JButton showTeamButton = new JButton("VER MI EQUIPO");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 2;
        panel.add(showTeamButton, layout);
        showTeamButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                ShowTeam showTeam;
				try {
					showTeam = new ShowTeam(controller);
					showTeam.show();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                window.setVisible(false);
            }
            
        });

        JButton buyPlayerButton = new JButton("COMPRAR JUGADOR");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 3;
        panel.add(buyPlayerButton, layout);
        buyPlayerButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                BuyPlayer buyPlayer;
				try {
					buyPlayer = new BuyPlayer(controller);
	                buyPlayer.show();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                window.setVisible(false);
            }
            
        });

        JButton sellPlayerButton = new JButton("VENDER JUGADOR");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 4;
        panel.add(sellPlayerButton, layout);
        sellPlayerButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                SellPlayer sellPlayer;
				try {
					sellPlayer = new SellPlayer(controller);
	                sellPlayer.show();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                window.setVisible(false);
            }
            
        });

        JButton configureTemplateButton = new JButton("CONFIGURAR ALINEACIÓN");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 5;
        panel.add(configureTemplateButton, layout);
        configureTemplateButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
            	ConfigurarAlineacion Alineacion;
				try 
				{
					
					Alineacion = new ConfigurarAlineacion(controller);
					Alineacion.show();
					window.setVisible(false);
				} 
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
               
                window.setVisible(false);
            }
            
        });

        JButton showTitularsButton = new JButton("MOSTRAR TITULARES");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 1;
        layout.gridy = 1;
        panel.add(showTitularsButton, layout);
        showTitularsButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) { 
                ShowHeadlines showHeadlines;
                try {
                    showHeadlines = new ShowHeadlines(controller);
                    showHeadlines.show();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                window.setVisible(false);
            }
            
        });

        JButton electCaptainButton = new JButton("ELEGIR CAPITÁN");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 1;
        layout.gridy = 2;
        panel.add(electCaptainButton, layout);
        electCaptainButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					ElegirCap pestaniaCap = new ElegirCap(controller, window);
					pestaniaCap.show();
					window.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        JButton showStatisticsButton = new JButton("MOSTRAR ESTADÍSITCAS");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 1;
        layout.gridy = 3;
        panel.add(showStatisticsButton, layout);
        showStatisticsButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                menuEstadisticas ventanaEstadisticas = new menuEstadisticas(controller);
                ventanaEstadisticas.show();
                window.setVisible(false);
            }
            
        });

        JButton showCalendarButton = new JButton("MOSTRAR CALENDARIO");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 1;
        layout.gridy = 4;
        panel.add(showCalendarButton, layout);
        showCalendarButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					showCalendar calendario = new showCalendar(controller);
					calendario.show();
					window.setVisible(false);
            	} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
                
            }
            
        });

        JButton closeSessionButton = new JButton("CERRAR SESIÓN");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 1;
        layout.gridy = 5;
        panel.add(closeSessionButton, layout);
        closeSessionButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            	UserLogin anterior;
                anterior = new UserLogin(controller);
                anterior.show();
                window.setVisible(false);
            }
            
        });

        window.add(panel);
    }

    public void show() {
        window.setVisible(true);
    }
    
}
