package Interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Analizador.equipo;
import Controller.controller;

public class FechasParaReporte {
	 private JFrame window;

	    public FechasParaReporte(controller controller, JFrame anterior) throws IOException {
	        window = new JFrame();
	        window.setTitle("Fechas");
	        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        window.setSize(600, 600);
	        window.setLocationRelativeTo(null);

	        JPanel panel = new JPanel();
	        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
	        panel.setBackground(Color.WHITE);
	        panel.setLayout(new GridBagLayout());
	        GridBagConstraints layout = new GridBagConstraints();

	        JLabel label = new JLabel();
	        label.setText("Seleccione la fecha que quiere Consultar: ");
	        layout.fill = GridBagConstraints.HORIZONTAL;
	        layout.gridx = 0;
	        layout.gridy = 0;
	        layout.insets = new Insets(0, 0, 50, 0);
	        panel.add(label, layout);

	        ArrayList<String> fechas = controller.consultarFechasCerradas();
	        fechas.remove(0);
	        Object[][] matriz = new Object[fechas.size()][];
	        int centinela = 0;
	        for(String fecha: fechas)
	        {
	        	Object[] fila = {Integer.toString(centinela+1),fecha};
	        	matriz[centinela] = fila;
	        	centinela+=1;
	        }
	        
	        DefaultTableModel dm = new DefaultTableModel();
	        dm.setDataVector(matriz, new Object[]{"Button", "String"});

	        
	        JTable table = new JTable(dm);
	        table.getColumn("Button").setCellRenderer(new ButtonRenderer());
	        table.getColumn("Button").setCellEditor(new ButtonEditor(new JCheckBox()));

	        table.setPreferredScrollableViewportSize(table.getPreferredSize());//thanks mKorbel +1 http://stackoverflow.com/questions/10551995/how-to-set-jscrollpane-layout-to-be-the-same-as-jtable

	        table.getColumnModel().getColumn(0).setPreferredWidth(100);//so buttons will fit and not be shown butto..
	        
	        layout.fill = GridBagConstraints.HORIZONTAL;
	        layout.gridx = 0;
	        layout.gridy = 1;
	        layout.insets = new Insets(0, 0, 50, 0);
	        panel.add(table, layout);

	        JButton returnButton = new JButton("Volver");
	        returnButton.addActionListener(new ActionListener(){

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	window.setVisible(false);
	                anterior.setVisible(true);
	            }
	            
	        });
	        
	        layout.fill = GridBagConstraints.HORIZONTAL;
	        layout.gridx = 0;
	        layout.gridy = 2;
	        layout.insets = new Insets(0, 0, 50, 0);

	        panel.add(returnButton, layout);

	        window.add(panel);
	    }

	    public void show() {
	        window.setVisible(true);
	    }
	    class ButtonEditor extends DefaultCellEditor {
	    	
	        protected JButton button;
	        private String label;
	        private boolean isPushed;
	        	
	        public ButtonEditor(JCheckBox checkBox) {
	            super(checkBox);
	            button = new JButton();
	            button.setOpaque(true);
	            button.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    fireEditingStopped();
	                }
	            });
	        }

	        @Override
	        public Component getTableCellEditorComponent(JTable table, Object value,
	                boolean isSelected, int row, int column) {
	            if (isSelected) {
	                button.setForeground(table.getSelectionForeground());
	                button.setBackground(table.getSelectionBackground());
	            } else {
	                button.setForeground(table.getForeground());
	                button.setBackground(table.getBackground());
	            }
	            label = (value == null) ? "" : value.toString();
	            button.setText(label);
	            isPushed = true;
	            return button;
	        }

	        @Override
	        public Object getCellEditorValue() {
	        	controller controller = new controller();
	            if (isPushed) 
	            
	            {
	            	
	            	try {
						ReportePorPuntosFechas reporte = new ReportePorPuntosFechas(controller, Integer.parseInt(label));
						reporte.show();
	            	
	            	} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	
	            	
	            	
	            }
	            isPushed = false;
	            return label;
	        }

	        @Override
	        public boolean stopCellEditing() {
	            isPushed = false;
	            return super.stopCellEditing();
	        }
	    }

}

