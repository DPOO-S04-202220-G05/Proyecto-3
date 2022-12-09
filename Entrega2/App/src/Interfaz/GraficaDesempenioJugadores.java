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
import org.jfree.data.general.DefaultPieDataset;

import Analizador.equipo;
import Analizador.jugador;
import Controller.controller;

public class GraficaDesempenioJugadores {
	private JFrame window;
	public GraficaDesempenioJugadores(controller controller, equipo equipo) throws IOException
	{
		window = new JFrame();
		window.setTitle("Desempenio de jugadores");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(400, 400);
       
        DefaultPieDataset datos = new DefaultPieDataset();
        ArrayList<jugador> jugadores = equipo.getJugadores();
        
        for(int i=0; i<jugadores.size();i++)
        {
        	jugador jugador = jugadores.get(i);
        	datos.setValue(jugador.getNombre(),jugador.getPuntos());
        	
        }
        JFreeChart GraficoPie = ChartFactory.createPieChart("Desempenio de los jugadores por puntos", datos);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        ChartPanel Reporte = new ChartPanel(GraficoPie);
        Reporte.setMouseWheelEnabled(true);
        Reporte.setPreferredSize(new Dimension(400,300));
        panel.add(Reporte);
        
        
        window.add(panel);
	}
	public void show() {
        window.setVisible(true);
    }
}
