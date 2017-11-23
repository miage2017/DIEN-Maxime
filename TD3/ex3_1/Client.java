package ex3_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by M.Dien on 21/11/2017.
 * MIAGE Student (M1)
 */
public class Client implements Runnable {
    //Configuration
    private int port = 12222; //free port
    private String servIP = "127.0.0.1"; //local


    private BufferedReader in = null; //tampon de lecture
    private PrintWriter out = null;
    private String id;

    public Client(String id_cli) {
        id=id_cli;
    }

    public void run() {
        Socket la_connection = null;
        try{
            la_connection = new Socket(servIP, port);
            in = new BufferedReader(new InputStreamReader(la_connection.getInputStream()));
            out = new PrintWriter(la_connection.getOutputStream(), true);
        }catch (IOException e) {
            System.out.println(e);System.exit(-1);
        }
        System.out.format("%s : Contact reussi avec %s:%d\n", id, servIP, port);
        for(int i = 0; i < 10; i++) {
            out.format("%s: message %d\n", id, i);
            try{
                Thread.sleep( (int)( 3000*Math.random()));
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        out.format("*****FIN*****\n");

        if (la_connection !=null)
            try {
                la_connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
