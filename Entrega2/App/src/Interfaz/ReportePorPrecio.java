package Interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

public class ReportePorPrecio {
	private JFrame window;
	public ReportePorPrecio(controller controller) throws IOException
	{
		window = new JFrame();
		window.setTitle("Reporte por precio");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(400, 400);
       
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        ArrayList<equipo> equiposfantasia = controller.obtenerEquiposFantasia();
        
        for(int i=0; i<equiposfantasia.size();i++)
        {
        	equipo equipofantasia = equiposfantasia.get(i);
        	datos.setValue((100000000-equipofantasia.getPresupuesto()), "Precio", equipofantasia.getNombre());
        	//100000000
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
