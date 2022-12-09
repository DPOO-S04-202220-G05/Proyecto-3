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
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Analizador.equipo;
import Controller.controller;
import Interfaz.UserTeams.ButtonEditor3;
import Interfaz.UserTeams.ButtonRenderer3;

public class MostrarEquiposParaReporte {
	private JFrame window;
    private controller controlador;

    public MostrarEquiposParaReporte(controller controller, JFrame anterior) throws IOException{

        controlador = controller;

        window = new JFrame();
        window.setTitle("USER TEAMS");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(600, 600);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.insets = new Insets(0, 50, 50, 50);

        JLabel titulo = new JLabel();
        titulo.setText("Elegir Equipo");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 0;
        panel.add(titulo,layout);

        ArrayList<equipo> EquiposUsuario = controller.obtenerEquiposFantasia();
        
        Object[][] matriz = new Object[EquiposUsuario.size()][];
        int centinela = 0;
        for(equipo equipo: EquiposUsuario)
        {
        	Object[] fila = {Integer.toString(centinela+1),equipo.getNombre()};
        	matriz[centinela] = fila;
        	centinela+=1;
        }
        
        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(matriz, new Object[]{"Button", "String"});

        
        JTable table = new JTable(dm);
        table.getColumn("Button").setCellRenderer((TableCellRenderer) new ButtonRenderer3());
        table.getColumn("Button").setCellEditor(new ButtonEditor3(new JCheckBox(), EquiposUsuario));

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

class ButtonRenderer3 extends JButton implements TableCellRenderer {

    public ButtonRenderer3() {
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

class ButtonEditor3 extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private ArrayList<equipo> equipos;

    public ButtonEditor3(JCheckBox checkBox, ArrayList<equipo> EquiposUsuario) {
        super(checkBox);
        this.equipos = EquiposUsuario;
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
    	controller controller = controlador;
        if (isPushed) {
				equipo equipo = equipos.get(Integer.parseInt(label)-1);
                try {
					GraficaDesempenioJugadores grafico = new GraficaDesempenioJugadores(controller, equipo);
					grafico.show();
                } catch (IOException e) {
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
