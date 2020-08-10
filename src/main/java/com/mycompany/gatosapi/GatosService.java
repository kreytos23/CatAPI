package com.mycompany.gatosapi;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class GatosService {

    public static void verGatitos() throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .method("GET", null)
                .build();  
            Response response = client.newCall(request).execute();
            
        String json = response.body().string();
        json = json.substring(1,json.length()-1);
        
        Gson gson = new Gson(); 
        Gatos gatos = gson.fromJson(json, Gatos.class);
        
        Image imagen = null;
        try{
            URL url = new URL(gatos.getUrl());
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.addRequestProperty("User-Agent", "");
            BufferedImage bufferedImage = ImageIO.read(http.getInputStream());
            ImageIcon fondoGato = new ImageIcon(bufferedImage);
            
            if(fondoGato.getIconWidth() > 800 || fondoGato.getIconHeight() > 400){
                
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800, 400, java.awt.Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);
            }   
                
            String menu =  "Opciones: \n1.- Cambiar Gatitos \n2.-Favorito \n3.-Volver ";
            String botones[] = { "Ver Otra imagen", "Favoritos", "Volver"};
            String id_gato = gatos.getID();
            String opcion = (String) JOptionPane.showInputDialog(null,menu,id_gato,JOptionPane.INFORMATION_MESSAGE
                    ,fondoGato,botones,botones[0]);
            
            int seleccion = -1;
            
            for(int i = 0; i < botones.length;i++){
                if(opcion.equals(botones[i])){
                    seleccion = i;
                }
            }
            
            switch(seleccion){
                case 0:
                    verGatitos();
                    break;
                case 1:
                    favoritos(gatos);
                    break;
                default:
                    break;             
            }
            
        }catch(IOException e){
            System.out.println(e);
        }
          
        
    }
    
    public static void favoritos (Gatos gatos){
        try {
            OkHttpClient client = new OkHttpClient();
              MediaType mediaType = MediaType.parse("application/json");
              RequestBody body = RequestBody.create(mediaType, "{\n\t\"image_id\": \""+gatos.getID()+"\"\n}");
              Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", gatos.getAPIkey())
                .build();
              Response response = client.newCall(request).execute();
              JOptionPane.showMessageDialog(null, "Añadido a Favoritos");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public static void verFavoritos (String apiKey){
       
         try {
          OkHttpClient client = new OkHttpClient();
          Request request = new Request.Builder()
            .url("https://api.thecatapi.com/v1/favourites")
            .method("GET", null)
            .addHeader("x-api-key",apiKey)
            .build();
       
            Response response = client.newCall(request).execute();
            
            String cadenaJson = response.body().string();
            
            Gson gson = new Gson();
            
            GatosFav[] gatosArray = gson.fromJson(cadenaJson,GatosFav[].class);
            
             System.out.println(gatosArray.length);
            if(gatosArray.length > 0){
                int min = 1;
                int max = gatosArray.length;
                int aleatorio = (int) (Math.random() * ((max-min)+1)) + min;
                int indice = aleatorio -1;
                
                GatosFav gatoFav = gatosArray[indice];
                
                Image imagen = null;
                
                try{
                    URL url = new URL(gatoFav.getImage().getUrl());
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    http.addRequestProperty("User-Agent", "");
                    BufferedImage bufferedImage = ImageIO.read(http.getInputStream());
                    ImageIcon fondoGato = new ImageIcon(bufferedImage);

                    if(fondoGato.getIconWidth() > 800 || fondoGato.getIconHeight() > 400){

                        Image fondo = fondoGato.getImage();
                        Image modificada = fondo.getScaledInstance(800, 400, java.awt.Image.SCALE_SMOOTH);
                        fondoGato = new ImageIcon(modificada);
                    }   

                    String menu =  "Opciones: \n1.- Cambiar Gatitos \n2.-Eliminar Favorito \n3.-Volver ";
                    String botones[] = { "Ver Otra imagen", "Eliminar", "Volver"};
                    String id_gato = gatoFav.getId();
                    String opcion = (String) JOptionPane.showInputDialog(null,menu,id_gato,JOptionPane.INFORMATION_MESSAGE
                            ,fondoGato,botones,botones[0]);

                    int seleccion = -1;

                    for(int i = 0; i < botones.length;i++){
                        if(opcion.equals(botones[i])){
                            seleccion = i;
                        }
                    }

                    switch(seleccion){
                        case 0:
                            verFavoritos(apiKey);
                            break;
                        case 1:
                            borrarFavorito(gatoFav);
                            break;
                        default:
                            break;             
                    }

                }catch(IOException e){
                    System.out.println(e);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GatosService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void borrarFavorito(GatosFav gatoFav){
        try {
            OkHttpClient client = new OkHttpClient();
            
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites/"+gatoFav.getId()+"")
                    .delete(null)
                    .addHeader("Content-Type", "application/json ")
                    .addHeader("x-api-key", gatoFav.getApiKey())
                    .build();
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
