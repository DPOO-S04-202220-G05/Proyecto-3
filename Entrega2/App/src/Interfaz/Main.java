package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Controller.controller;


public class Main {
    
    private JFrame window;

    public Main(controller controller) {
        window = new JFrame();
        window.setTitle("USER TYPE");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(350, 400);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        JLabel label = new JLabel();
        label.setText("Seleccione su tipo de usuario: ");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.insets = new Insets(0, 0, 50, 0);
        panel.add(label, layout);

        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 1;
        layout.insets = new Insets(0, 0, 50, 0);

        JButton userButton = new JButton("USUARIO");
        panel.add(userButton, layout);
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 2;
        userButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                UserLogin login = new UserLogin(controller);
                login.show();
                window.setVisible(false);
            }
            
        });
        
        JButton adminButton = new JButton("ADMIN");
        panel.add(adminButton, layout);
        adminButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLogin login = new AdminLogin(controller);
                login.show();
                window.setVisible(false);
            }
            
        });

        window.add(panel);
    }

    public void show() {
        window.setVisible(true);
    }
}
