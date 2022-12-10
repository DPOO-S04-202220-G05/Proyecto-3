package Interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
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

public class ReportePorPuntosFechas {
	private JFrame window;
	public ReportePorPuntosFechas(controller controller, int fecha) throws IOException
	{
		window = new JFrame();
		window.setTitle("Reporte por puntos Fecha-"+fecha);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(400, 400);
       
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        File CarpetaFechas = new File("PuntosFechas/Fecha"+fecha);
        
        if(CarpetaFechas.exists())
        {
        	
        	 for(File file: CarpetaFechas.listFiles())
             {
        		 String NombreArchivo = file.getName();
        		 String[] Nombres = NombreArchivo.split("-");
        		 
        		
        		 
        		 datos.setValue(Integer.parseInt(Nombres[1]), "Puntos", Nombres[0]);
             }
        	
        	
        }
        else
        {
        	JOptionPane.showMessageDialog(window, "No existe la fecha");
        }
        JFreeChart Grafico = ChartFactory.createBarChart(
				"Progreso por puntos - Fecha-"+fecha, 
				"Equipos", 
				"Puntos - Fecha-"+fecha, 
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

