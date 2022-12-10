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

import javax.swing.*;
import javax.swing.table.*;

import Analizador.equipo;
import Controller.controller;

public class bestTeam {
    
    private JFrame window;

    public bestTeam(controller controller, equipo equipo) throws IOException {
        window = new JFrame();
        window.setTitle("USER TYPE");
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
            	menuEstadisticas anterior;
                anterior = new menuEstadisticas(controller, equipo);
                anterior.show();
                window.setVisible(false);
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

}

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
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
    	String mejor="";
    	try {
			mejor = controller.consultarMejorEquipoFecha(Integer.parseInt(label));
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (isPushed) {
        	if (mejor=="") {JOptionPane.showMessageDialog(button, "En la fecha no hubo un mejor equipo");}
        	else {
            JOptionPane.showMessageDialog(button, "El mejor equipo de la Fecha " + label + " es: " + mejor);
        }}
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}