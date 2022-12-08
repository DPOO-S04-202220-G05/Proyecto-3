package Interfaz;

import javax.swing.SwingUtilities;
import Controller.controller;

public class TLauncher {
    
    private static controller controller = new controller();
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                Main typeSelect = new Main(controller);
                typeSelect.show();
            }
        });
    }
}
