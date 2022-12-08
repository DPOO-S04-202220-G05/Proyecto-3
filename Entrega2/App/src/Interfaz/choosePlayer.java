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
import java.util.HashMap;
import java.util.Set;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Analizador.equipo;
import Analizador.jugador;
import Controller.controller;

public class choosePlayer {
    
    private JFrame window;
    private controller controlador;

    public choosePlayer(controller controller, String EquipoElegido, equipo equipoUsuario) throws IOException{
    	this.controlador=controller;
        window = new JFrame();
        window.setTitle("BUY PLAYER");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(600, 600);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        JLabel titulo = new JLabel();
        titulo.setText("Comprar Jugador");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 0;
        panel.add(titulo,layout);

        HashMap<String, equipo> EquiposJugadores = controller.mostrarEquiposReales();
		Set<String> Llaves = EquiposJugadores.keySet();
		ArrayList<String> NombresJ = new ArrayList<>(Llaves);
		String nombreE = NombresJ.get(Integer.parseInt(EquipoElegido)-1);
		equipo equipo = EquiposJugadores.get(nombreE);
		ArrayList<jugador> jugadoresE= equipo.getJugadores();
        
        Object[][] matriz = new Object[EquiposJugadores.size()][];
        int centinela = 0;
        for(jugador jugador: jugadoresE)
        {
        	Object[] fila = {Integer.toString(centinela+1),jugador.getNombre()};
        	matriz[centinela] = fila;
        	centinela+=1;
        }
        
        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(matriz, new Object[]{"Button", "String"});

        
        JTable table = new JTable(dm);
        table.getColumn("Button").setCellRenderer(new ButtonRenderer4());
        table.getColumn("Button").setCellEditor(new ButtonEditor4(new JCheckBox(), jugadoresE, equipoUsuario));

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
            	BuyPlayer anterior;
                try {
					anterior = new BuyPlayer(controller, equipoUsuario);
	                anterior.show();
	                window.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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

class ButtonRenderer4 extends JButton implements TableCellRenderer {

    public ButtonRenderer4() {
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

class ButtonEditor4 extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private ArrayList<jugador> jugadores;
    private equipo equipoUsuario;

    public ButtonEditor4(JCheckBox checkBox, ArrayList<jugador> equipo, equipo equipoUsuario) {
        super(checkBox);
        this.equipoUsuario = equipoUsuario;
        this.jugadores=equipo;
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
				controller.comprarJugador(label,jugadores, equipoUsuario);
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
