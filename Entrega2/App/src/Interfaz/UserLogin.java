package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import Controller.controller;

public class UserLogin {

    private JFrame window;

    public UserLogin(controller controller){
        window = new JFrame();
        window.setTitle("USER LOGIN");
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
        layout.insets = new Insets(0, 0, 30, 0);
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
                //
            	String nombreUsuario  = name.getText();
            	char[] arrayC = password.getPassword();
            	String contrasenia = new String(arrayC);
            	
            	
            	try 
            	{
            		PopUps popUp = new PopUps();
					if(controller.iniciarSesion(nombreUsuario, contrasenia, "UsuarioN"))
					{
						
						MenuUser menu = new MenuUser(controller);
						popUp.showInicioExitoso();
						menu.show();
				        int puntos = controller.actualizarPuntos();
				        PopUps popup = new PopUps();
				        popup.puntosUsuario(puntos);
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
        
        JLabel NoCuentaLabel = new JLabel();
        NoCuentaLabel.setText("¿No tienes una cuenta?");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 6;
        panel.add(NoCuentaLabel, layout);
        
        //Crear cuenta
        JLabel crearCuentaLabel = new JLabel();
        crearCuentaLabel.setText("           Crea una :)");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 7;
        Font cursiva = new Font( "Arial",Font.ITALIC,13 );
        crearCuentaLabel.setFont(cursiva);
        crearCuentaLabel.setForeground(Color.BLUE);
        panel.add(crearCuentaLabel, layout);
        crearCuentaLabel.addMouseListener(new MouseInputListener() {
     
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				NewAccount crearCuenta = new NewAccount(controller,window);
                crearCuenta.show();
                window.setVisible(false);
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
        layout.gridy = 8;
        layout.insets = new Insets(0, 0, 50, 0);

        panel.add(returnButton, layout);
       
     
        window.add(panel);
    }
    
   
    
    public void show() {
        window.setVisible(true);
    }
    
}
