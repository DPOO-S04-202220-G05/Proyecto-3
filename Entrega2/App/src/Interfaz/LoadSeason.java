package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

import Controller.controller;

public class LoadSeason {

    private JFrame window;
    private File teamsFile = null;
    private File seasonFile = null;

    public LoadSeason(controller controller) throws IOException{
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
        label.setText("CARGAR TEMPORADA Y EQUIPO");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 0;
        layout.insets = new Insets(0, 0, 30, 0);
        panel.add(label, layout);

        JButton seasonButton = new JButton("CARGAR TEMPORADA");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 1;
        panel.add(seasonButton, layout);
        seasonButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==seasonButton) {
			
                    JFileChooser fileChooser = new JFileChooser();
                    
                    fileChooser.setCurrentDirectory(new File(".")); //sets current directory
                    
                    int response = fileChooser.showOpenDialog(null); //select file to open
                    //int response = fileChooser.showSaveDialog(null); //select file to save
                    
                    if(response == JFileChooser.APPROVE_OPTION) {
                        seasonFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    }
                }
            }
            
        });

        JButton teamsButton = new JButton("CARGAR EQUIPOS");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 2;
        panel.add(teamsButton, layout);
        teamsButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==teamsButton) {
			
                    JFileChooser fileChooser = new JFileChooser();
                    
                    fileChooser.setCurrentDirectory(new File(".")); //sets current directory
                    
                    int response = fileChooser.showOpenDialog(null); //select file to open
                    //int response = fileChooser.showSaveDialog(null); //select file to save
                    
                    if(response == JFileChooser.APPROVE_OPTION) {
                        teamsFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    }
                }
            }
            
        });

        JButton sendButton = new JButton("CARGAR DATOS");
        layout.fill = GridBagConstraints.HORIZONTAL;
        layout.gridx = 0;
        layout.gridy = 3;
        panel.add(sendButton, layout);
        sendButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.loadSeason(teamsFile, seasonFile);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
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
        layout.gridy = 4;
        layout.insets = new Insets(0, 0, 50, 0);

        panel.add(returnButton, layout);

        window.add(panel);
    }

    public void show() {
        window.setVisible(true);
    }
}

