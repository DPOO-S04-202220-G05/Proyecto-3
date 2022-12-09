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
        window.setSize(600, 600);
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
        layout.insets = new Insets(0, 50, 50, 50);
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
               
            }
            
        });
        
        JButton botonPuntosPorFecha = new JButton("Comparacion de puntos por fecha");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 3;
        panel.add(botonPuntosPorFecha, layout);
        botonPuntosPorFecha.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					FechasParaReporte ventanaFechas = new FechasParaReporte(controller,window);
					window.setVisible(false);
					ventanaFechas.show();
                } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
            }
            
        });
        
        JButton botonDesempenioJugadores = new JButton("Desempenio de jugadores");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 1;
        layout.gridy = 1;
        panel.add(botonDesempenioJugadores, layout);
        botonDesempenioJugadores.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					MostrarEquiposParaReporte ventanaEquipos = new MostrarEquiposParaReporte(controller,window);
					window.setVisible(false);
					ventanaEquipos.show();
                
                } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
            }
            
        });
        
        JButton botonComparativoVentas = new JButton("Comparacion de ventas");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 1;
        layout.gridy = 2;
        panel.add(botonComparativoVentas, layout);
        botonComparativoVentas.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					ReportePorVentas graficaVentas = new ReportePorVentas(controller);
					graficaVentas.show();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
                
            }
            
        });
        
        JButton volver = new JButton("Volver");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 1;
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
