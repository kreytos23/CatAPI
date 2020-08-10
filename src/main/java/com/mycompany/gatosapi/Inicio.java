package com.mycompany.gatosapi;

import java.io.IOException;
import javax.swing.JOptionPane;

public class Inicio {
    
    public static void main(String args[]) throws IOException{
        
        int opcion = 0;
        String [] botones = {"1.- Mostrar Gatitos", "2.-Favoritos", "3.- Salir"};
        
        do{
        opcion = JOptionPane.showOptionDialog(null, "Elige tu opcion", "Menu Principal"
                ,0,JOptionPane.INFORMATION_MESSAGE,null, botones, botones[0]);
        
        switch(opcion){
            case 0:
                GatosService.verGatitos();
                break;
            case 1:
                Gatos gato = new Gatos();
                GatosService.verFavoritos(gato.getAPIkey());
            default:
                break;
        }
        }while(opcion != 2);
    }
}
