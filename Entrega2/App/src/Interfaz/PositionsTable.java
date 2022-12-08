package Interfaz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import Controller.controller;
import Analizador.equipo;

public class PositionsTable {

    private JFrame window;
    private controller controlador;

    public PositionsTable(controller controller) throws IOException{
        this.controlador = controller;
        window = new JFrame();
        window.setTitle("POSITIONS TABLE");
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

        ArrayList<equipo> teams = controller.tablaPosiciones();

        Object[][] matriz = new Object[teams.size()][];
        int count = 0;
        for(equipo equipo: teams)
        {
            Object[] row = {Integer.toString(count+1), Integer.toString(equipo.getPuntos()), equipo.getNombre()};
            matriz[count] = row;
            count += 1;
        }

        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(matriz, new Object[]{"Button", "String", "String"});

        JTable table = new JTable(dm);
        table.getColumn("Button").setCellRenderer(new ButtonRenderer());
        table.getColumn("Button").setCellEditor(new ButtonEditor(new JCheckBox(), teams));
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.getColumnModel().getColumn(0).setPreferredWidth(100);

        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 1;
        layout.insets = new Insets(0, 0, 50, 0);
        panel.add(table, layout);

        JButton returnButton = new JButton("Volver");
        returnButton.addActionListener((ActionListener) new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            	menuEstadisticas anterior;
                anterior = new menuEstadisticas(controller);
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

    class ButtonRenderer extends JButton implements TableCellRenderer{

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
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
        private ArrayList<equipo> equipos;
    
        public ButtonEditor(JCheckBox checkBox, ArrayList<equipo> equipos) {
            super(checkBox);
            this.equipos = equipos;
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
                int index = Integer.parseInt(label)-1;
                equipo equipo = equipos.get(index);
                TeamStatistics teamStatistics = new TeamStatistics(controller, equipo);
                teamStatistics.show();
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

    public void show() {
        window.setVisible(true);
    }
    
}
