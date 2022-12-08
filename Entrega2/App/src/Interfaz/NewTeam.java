package Interfaz;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.controller;

public class NewTeam {

    private JFrame window;

    public NewTeam(controller controller, String userName){
        window = new JFrame();
        window.setTitle("ADMIN LOGIN");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(300, 350);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        JLabel label = new JLabel();
        label.setText("Ingrese el nombre del equipo: ");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.insets = new Insets(0, 0, 50, 0);
        panel.add(label, layout);

        JTextField nomEquipo = new JTextField();
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 1;
        panel.add(nomEquipo, layout);

        JButton nextButton = new JButton("CONTINUAR");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 2;
        panel.add(nextButton, layout);
        nextButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	String nombreEquipo  = nomEquipo.getText();
            	try {
					controller.crearEquipo(nombreEquipo);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	PopUps popUp = new PopUps();
            	popUp.showCreacionEqExitoso();
				TeamMenu menu = new TeamMenu(controller, userName);
				menu.show();
				window.setVisible(false);
               
            }
            
        });
        
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
        layout.gridy = 3;
        layout.insets = new Insets(0, 0, 50, 0);

        panel.add(returnButton, layout);

        window.add(panel);
    }

    public void show() {
        window.setVisible(true);
    }

}
