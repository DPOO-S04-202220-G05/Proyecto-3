package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import Controller.controller;

public class LoadMatch {

    private JFrame window;

    public LoadMatch(controller controller){
        window = new JFrame();
        window.setTitle("LOAD TEAMS");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(550, 400);
        window.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();

        JLabel label = new JLabel();
        label.setText("CARGAR PARTIDO");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.insets = new Insets(0, 0, 30, 0);
        panel.add(label, layout);

        JButton matchButton = new JButton("CARGAR PARTIDO");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 2;
        panel.add(matchButton, layout);
        matchButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==matchButton) {
			
                    JFileChooser fileChooser = new JFileChooser();
                    
                    fileChooser.setCurrentDirectory(new File(".")); //sets current directory
                    
                    int response = fileChooser.showOpenDialog(null); //select file to open
                    //int response = fileChooser.showSaveDialog(null); //select file to save
                    
                    if(response == JFileChooser.APPROVE_OPTION) {
                        File matchFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                        try {
                            controller.loadMatch(matchFile);
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            }
            
        });
        JButton returnButton = new JButton("Volver");
        returnButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                MenuAdmin anterior;
                anterior = new MenuAdmin(controller);
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
    

