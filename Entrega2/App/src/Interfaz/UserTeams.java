package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Controller.controller;
import Analizador.equipo;

public class UserTeams {

    private JFrame window;
    private controller controlador;

    public UserTeams(controller controller, String userName) throws IOException{

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

        ArrayList<equipo> EquiposUsuario = controller.consultarEquipos(userName);
        
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
            	TeamMenu anterior;
                anterior = new TeamMenu(controller, userName);
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
                MenuUser menu = new MenuUser(controller, equipo);
                try {
                    int puntos = controller.actualizarPuntos(equipo);
				    PopUps popup = new PopUps();
				    popup.puntosUsuario(puntos);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                menu.show();
                window.setVisible(false);
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
