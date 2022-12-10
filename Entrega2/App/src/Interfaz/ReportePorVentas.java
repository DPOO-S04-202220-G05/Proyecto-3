package Interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import Analizador.equipo;
import Controller.controller;

public class ReportePorVentas {
	private JFrame window;
	public ReportePorVentas(controller controller) throws IOException
	{
		window = new JFrame();
		window.setTitle("Reporte por precio");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(400, 400);
       
        DefaultCategoryDataset datos = new DefaultCategoryDataset();

        
        File CarpetaVentas = new File("Ventas");
        
        if(CarpetaVentas.exists())
        {
        	
        	 for(File file: CarpetaVentas.listFiles())
             {
        		 String NombreArchivoVentas = file.getName();
        		 String[] Nombres = NombreArchivoVentas.split("-");
        		 
        		
        		 
        		 datos.setValue(Integer.parseInt(Nombres[0]), "Gastos", Nombres[1]);
             }
        	
        	
        }
        else
        {
        	JOptionPane.showMessageDialog(window, "No se han hecho ventas");
        }
        
       
        JFreeChart Grafico = ChartFactory.createBarChart(
				"Progreso comparativo de equipos - Por precio del equipo", 
				"Equipos", 
				"Valor", 
				datos);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        ChartPanel Reporte = new ChartPanel(Grafico);
        Reporte.setMouseWheelEnabled(true);
        Reporte.setPreferredSize(new Dimension(400,300));
        panel.add(Reporte);
        
        
        window.add(panel);
	}
	public void show() {
        window.setVisible(true);
    }
}
