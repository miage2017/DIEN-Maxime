/**
 * Created by maxim on 09/11/2017.
 */
import java.io.*;
import java.net.*;

public class Serveur_Jouet {
    private int port = 12000;
    private String host = "127.0.0.1";
    private ServerSocket server = null;
    private boolean isRunning = true;



    public Serveur_Jouet(){
        try {
            server = new ServerSocket(port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Serveur_Jouet(String pHost, int pPort){
        host = pHost;
        port = pPort;
        try {
            server = new ServerSocket(port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //On lance notre serveur
    public void starter(){

        Thread t = new Thread(new Runnable(){
            public void run(){
                while(isRunning == true){

                    try {
                        //On attend une connexion d'un client
                        Socket client = server.accept();

                        //Une fois reçue, on la traite dans un thread séparé
                        System.out.println("Connexion cliente reçue.");
                        Thread t = new Thread(new Connexion_Entrante(client));
                        t.start();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    server = null;
                }
            }
        });

        t.start();
    }

    public void close(){
        isRunning = false;
    }

    public static void main (String args[]){
        String host = "127.0.0.1";
        int port = 1200;
        Serveur_Jouet SJ = new Serveur_Jouet();
        SJ.starter();
        System.out.println("Serveur on");
    }

}
