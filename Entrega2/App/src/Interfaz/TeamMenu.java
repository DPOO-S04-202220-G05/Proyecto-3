package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import Controller.controller;

public class TeamMenu {

    private JFrame window;

    public TeamMenu(controller controller, String userName){

        window = new JFrame();
        window.setTitle("TEAM MENU");
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
                NewTeam ventanaNuevoEquipo = new NewTeam(controller, userName);
                ventanaNuevoEquipo.show();
                window.setVisible(false);
            }
            
        });

        JButton showTitularsButton = new JButton("SELECCIONAR EQUIPO EXISTENTE");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 1;
        layout.gridy = 1;
        panel.add(showTitularsButton, layout);
        showTitularsButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) { 
                UserTeams equiposUsuario;
                try {
                    equiposUsuario = new UserTeams(controller, userName);
                    equiposUsuario.show();
                    window.setVisible(false);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            
        });

        JButton closeSessionButton = new JButton("CERRAR SESIÓN");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 2;
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
