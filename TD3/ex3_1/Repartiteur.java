package ex3_1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by M.Dien on 21/11/2017.
 * MIAGE Student (M1)
 */
public class Repartiteur extends ServerSocket {

    private final static int port = 12222;

    public Repartiteur() throws IOException {
        super(port);
        System.out.println("<SERVEUR>----- : Serveur lancé sur " + port);
    }

    public void execute() throws IOException {
        Socket uneConnexion;
        while (true) {
            System.out.println("<SERVEUR>----- :  Attente d'une connexion");
            uneConnexion = accept();
            String c_ip = uneConnexion.getInetAddress().toString();
            int c_port = uneConnexion.getPort();
            System.out.format("<SERVEUR>----- : Arr. ex3_1.Client IP %s sur %d\n", c_ip, c_port);
            System.out.format("<SERVEUR>----- : Creation du thread T_%d\n", c_port);

            new Thread(new ServiceClient(uneConnexion, "T_" + c_port)).start();
        }
    }

    public static void main(String[] args) throws IOException {
        Repartiteur gererConnexion = new Repartiteur();
        gererConnexion.execute();
    }

}
