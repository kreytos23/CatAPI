package com.mycompany.gatosapi;

import javax.swing.JOptionPane;


public class Inicio {
    
    public static void main(String args[]){
        
        int opcion = 0;
        String [] botones = {"1.- Mostrar Gatitos", "2.-Salir"};
        
        opcion = JOptionPane.showOptionDialog(null, "Elige tu opcion", "Menu Principal"
                ,0,JOptionPane.INFORMATION_MESSAGE,null, botones, botones[0]);
        
        switch(opcion){
            case 0:
                break;
            default:
                break;
        }
    }
}
