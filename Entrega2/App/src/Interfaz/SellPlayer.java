package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Analizador.jugador;
import Analizador.equipo;
import Controller.controller;

public class SellPlayer {
    
    private JFrame window;
    private controller controlador;

    public SellPlayer(controller controller, equipo equipo) throws IOException{
    	this.controlador=controller;
        window = new JFrame();
        window.setTitle("Vender Jugador");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(600, 600);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        JLabel titulo = new JLabel();
        titulo.setText("Vender Jugador");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 0;
        panel.add(titulo,layout);

        ArrayList<jugador> EquiposJugadores = controller.consultarJugadores(equipo);
        if(EquiposJugadores.size()==0) 
        {
            JLabel noplayers = new JLabel();
            noplayers.setText("No tienes jugadores :c");
            layout.fill = GridBagConstraints.HORIZONTAL;
            layout.gridx = 0;
            layout.gridy = 1;
            panel.add(noplayers,layout);
        }
        Object[][] matriz = new Object[EquiposJugadores.size()][];
        int centinela = 0;
        for(jugador j: EquiposJugadores)
        {
        	Object[] fila = {Integer.toString(centinela+1),j.getNombre()};
        	matriz[centinela] = fila;
        	centinela+=1;
        }
        
        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(matriz, new Object[]{"Button", "String"});

        
        JTable table = new JTable(dm);
        table.getColumn("Button").setCellRenderer(new ButtonRenderer2());
        table.getColumn("Button").setCellEditor(new ButtonEditor2(new JCheckBox(), equipo));

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
            	MenuUser anterior;
                anterior = new MenuUser(controller, equipo);
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

class ButtonRenderer2 extends JButton implements TableCellRenderer {

    public ButtonRenderer2() {
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

class ButtonEditor2 extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private equipo equipo;

    public ButtonEditor2(JCheckBox checkBox, equipo equipo) {
        super(checkBox);
        this.equipo = equipo;
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
        	try {
				controller.venderJugador(label, equipo);
                SellPlayer sellPlayer;
				sellPlayer = new SellPlayer(controller, equipo);
                sellPlayer.show();
                window.setVisible(false);
	            JOptionPane.showMessageDialog(button, "Jugador vendido exitosamente");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
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






