package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import Controller.controller;

public class UserTeams {

    private JFrame window;

    public UserTeams(controller controller, String userName){

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

        window.add(panel);

    }

    public void show() {
        window.setVisible(true);
    }
}
