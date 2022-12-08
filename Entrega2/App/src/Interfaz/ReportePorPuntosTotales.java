package Interfaz;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import Analizador.equipo;
import Controller.controller;

public class ReportePorPuntosTotales {
	private JFrame window;
	public ReportePorPuntosTotales(controller controller) throws IOException
	{
		window = new JFrame();
		window.setTitle("Reporte por puntos totales");
		
        window.setSize(350, 400);
       
	    
        JPanel panel = new JPanel();
        
        
        
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        ArrayList<equipo> equiposfantasia = controller.obtenerEquiposFantasia();
        
        for(int i=0; i<equiposfantasia.size();i++)
        {
        	equipo equipofantasia = equiposfantasia.get(i);
        	datos.setValue(equipofantasia.getPuntos(), "Puntos", equipofantasia.getNombre());
        	
        }
        JFreeChart Grafico = ChartFactory.createBarChart(
				"Progreso comparativo de equipos - Por puntos totales", 
				"Equipos", 
				"Puntos Totales", 
				datos);
        ChartPanel Reporte = new ChartPanel(Grafico);
        Reporte.setSize(200,200);
        panel.add(Reporte);
        window.add(panel);
	}
	public void show() {
        window.setVisible(true);
    }
}
