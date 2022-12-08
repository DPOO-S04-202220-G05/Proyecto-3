package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import Controller.controller;

public class AdminLogin {

    private JFrame window;

    public AdminLogin(controller controller){
        window = new JFrame();
        window.setTitle("ADMIN LOGIN");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(400, 550);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        JLabel label = new JLabel();
        label.setText("Ingrese sus datos: ");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.insets = new Insets(0, 0, 50, 0);
        panel.add(label, layout);

        JLabel labelName = new JLabel();
        labelName.setText("Ingrese su usuario");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 1;
        layout.insets = new Insets(20, 0, 0, 0);
        panel.add(labelName, layout);

        JTextField name = new JTextField();
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 2;
        panel.add(name, layout);

        JLabel labelPassword = new JLabel();
        labelPassword.setText("Ingrese su contraseña");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 3;
        layout.insets = new Insets(25, 0, 0, 0);
        panel.add(labelPassword, layout);

        JPasswordField password = new JPasswordField();
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 4;
        panel.add(password, layout);

        JButton nextButton = new JButton("CONTINUAR");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 5;
        panel.add(nextButton, layout);
        nextButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	String nombreUsuario  = name.getText();
            	char[] arrayC = password.getPassword();
            	String contrasenia = new String(arrayC);
            	
            	PopUps popUp = new PopUps();
            	
            	try 
            	{
					if(controller.iniciarSesion(nombreUsuario, contrasenia, "Admin"))
					{
						
						MenuAdmin menu = new MenuAdmin(controller);
						popUp.showInicioExitoso();
						menu.show();
						window.setVisible(false);
						
					}
					
					else
					{
						popUp.showInicioNoExitoso();
					}
				} 
            	catch (IOException e1) 
            	{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
               
            }
            
        });
        
        JButton returnButton = new JButton("Volver");
        returnButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            	Main anterior;
                anterior = new Main(controller);
                anterior.show();
                window.setVisible(false);
            }
            
        });
        
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 6;
        layout.insets = new Insets(0, 0, 50, 0);

        panel.add(returnButton, layout);

        window.add(panel);
    }

    public void show() {
        window.setVisible(true);
    }
    
}
