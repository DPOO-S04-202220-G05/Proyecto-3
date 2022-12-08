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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.controller;

public class NewAccount {
	
	 private JFrame window;

	    public NewAccount(controller controller, JFrame pestaniaAnterior){
	        window = new JFrame();
	        window.setTitle("Crear Nueva Cuenta");
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

	        JButton nextButton = new JButton("Crear Cuenta");
	        layout.fill = GridBagConstraints.HORIZONTAL;
	        layout.gridx = 0;
	        layout.gridy = 5;
	        panel.add(nextButton, layout);
	        nextButton.addActionListener(new ActionListener(){

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	//AQUI SE CREA LA NUEVA CUENTA
	            	String nombreUsuario  = name.getText();
	            	char[] arrayC = password.getPassword();
	            	String contrasenia = new String(arrayC);
	            	
	            	try 
	            	{
	            		PopUps popUp = new PopUps();
	            		if(controller.newAccount(nombreUsuario, contrasenia))
	            		{
	            			//muestra un popUp diciendo que la cuenta fue creada existosamente
	            			popUp.showCuentaExitosa();
	            			window.setVisible(false);
	            			//Se devulve al login
	            			pestaniaAnterior.setVisible(true);
	            			
	            		}
	            		else
	            		{
	            			//muestra un popUp diciendo que la cuenta no fue creada existosamente
	            			popUp.showCuentaNoCreada();
	            			//Se devulve al login
	            			pestaniaAnterior.setVisible(true);
	            			
	            		}
					} 
	            	
	            	catch (IOException e1) 
	            	
	            	{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            	
	         
	            }
	            
	        });

	        window.add(panel);
	    }

	    public void show() {
	        window.setVisible(true);
	    }
}
