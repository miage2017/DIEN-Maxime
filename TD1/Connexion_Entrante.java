/**
 * Created by M.Dien on 09/11/2017.
 */

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class Connexion_Entrante implements Runnable{

    private Socket sock;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;

    public Connexion_Entrante(Socket pSock){
        sock = pSock;
    }

    public void run(){
        System.err.println("Lancement du traitement de la connexion cliente");

        boolean closeConnexion = false;
        //tant que la connexion est active, on traite les demandes
        while(!sock.isClosed()){

            try {

                writer = new PrintWriter(sock.getOutputStream());
                reader = new BufferedInputStream(sock.getInputStream());

                String response = read();
                InetSocketAddress remote = (InetSocketAddress)sock.getRemoteSocketAddress();
                writer.write(response);
                writer.flush();

                if(closeConnexion){
                    System.err.println("COMMANDE CLOSE DETECTEE ! ");
                    writer = null;
                    reader = null;
                    sock.close();
                    break;
                }
            }catch(SocketException e){
                System.err.println("CONNEXION INTERROMPUE ! ");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String read() throws IOException{
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);

        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        //System.out.println(response);
        System.out.println(stream + in.readLine());
        return response;
    }

}