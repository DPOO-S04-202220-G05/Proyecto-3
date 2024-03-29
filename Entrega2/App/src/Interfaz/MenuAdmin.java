package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import Analizador.equipo;
import Controller.controller;

public class MenuAdmin {

    private JFrame window;

    public MenuAdmin(controller controller) {
        window = new JFrame();
        window.setTitle("ADMIN MENU");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(550, 400);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.insets = new Insets(0, 0, 50, 0);

        JButton teamsButton = new JButton("CARGAR TEMPORADA Y EQUIPOS");
        panel.add(teamsButton, layout);
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 1;
        teamsButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                LoadSeason loadSeason;
                try {
                    loadSeason = new LoadSeason(controller);
                    loadSeason.show();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                window.setVisible(false);
            }
            
        });
        
        JButton matchesButton = new JButton("CARGAR PARTIDOS");
        panel.add(matchesButton, layout);
        matchesButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                LoadMatch loadMatches = new LoadMatch(controller);
                loadMatches.show();
                window.setVisible(false);
            }
            
        });
        
        JButton NewReportButton = new JButton("Generar Reportes");
        
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 2;
        
        panel.add(NewReportButton, layout);
        NewReportButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               ReportesPrincipal reportesVentana = new ReportesPrincipal(controller, window);
               ArrayList<equipo> equipos;
			try {
				equipos = controller.obtenerEquiposFantasia();
				if(!equipos.isEmpty())
				{
				  for(int i=0; i<equipos.size();i++)
	               {
	               equipo elequipo = equipos.get(i);
	               controller.actualizarPuntos(elequipo);
	               }
	               reportesVentana.show();
	                window.setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(window, "No hay equipos disponibles para elaborar reportes");
				}
				} catch (IOException e1) {
				// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(window, "No hay equipos disponibles para elaborar reportes");
				e1.printStackTrace();
			}
               
             
            }
            
        });
        
        JButton returnButton = new JButton("Cerrar Sesion");
        returnButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            	AdminLogin anterior;
                anterior = new AdminLogin(controller);
                anterior.show();
                window.setVisible(false);
            }
            
        });
        
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 3;
        layout.insets = new Insets(0, 0, 50, 0);

        panel.add(returnButton, layout);
       
        window.add(panel);
    }

    public void show() {
        window.setVisible(true);
    }
    
}
