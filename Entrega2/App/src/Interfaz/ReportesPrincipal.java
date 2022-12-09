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

public class ReportesPrincipal {
	private JFrame window;
	public ReportesPrincipal(controller controller, JFrame anterior)
	{
		window = new JFrame();
		window.setTitle("Reportes");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(350, 400);
        window.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        
        JLabel label = new JLabel();
        label.setText("¿Que reporte desea ver?");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.insets = new Insets(0, 0, 50, 0);
        panel.add(label, layout);
        
        JButton botonPuntos = new JButton("Comparacion por puntos totales");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 1;
        panel.add(botonPuntos, layout);
        botonPuntos.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               
					ReportePorPuntosTotales reportePuntos;
					try {
						reportePuntos = new ReportePorPuntosTotales(controller);
						reportePuntos.show();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				     
            }
            
        });
        
        JButton botonPrecio = new JButton("Comparacion por precio");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 2;
        panel.add(botonPrecio, layout);
        botonPrecio.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					ReportePorPrecio reportePrecio = new ReportePorPrecio(controller);
					reportePrecio.show();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                window.setVisible(false);
            }
            
        });
        
        JButton volver = new JButton("Volver");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 3;
        panel.add(volver, layout);
        volver.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                anterior.setVisible(true);
                window.setVisible(false);
            }
            
        });
        window.add(panel);
	}
	
	public void show() {
        window.setVisible(true);
    }
}
