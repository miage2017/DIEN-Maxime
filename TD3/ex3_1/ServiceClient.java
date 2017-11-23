package ex3_1;
/**
 * Created by M.Dien on 21/11/2017.
 * MIAGE Student (M1)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServiceClient  implements Runnable{

    private  Socket uneConnexion;
    private String Finish=""+(char) 4;
    private  String id;

    public ServiceClient(Socket la_connection, String mid)
    {
        uneConnexion = la_connection;
        id=mid;
        System.out.format("Thread T__%s créé pour traiter la connection\n",id);
    }


    private void interrupt(){
        try{
            if (uneConnexion != null) {
                System.out.format(">>>>>Interruption prévue pour %s\n", id);
                uneConnexion.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public void run(){
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            InputStreamReader isr = new InputStreamReader(uneConnexion.getInputStream(), "UTF-8");
            in = new BufferedReader(isr);
            out = new PrintWriter(uneConnexion.getOutputStream(), true);
            String co_ip = uneConnexion.getInetAddress().toString();
            int co_port= uneConnexion.getPort();

            System.out.format("ID : %s >| client admis IP = %s | port = %d\n", id, co_ip, co_port);
            out.format("ID : %s >| IP = %s | Port = %d, \n" ,  id, co_ip, co_port );
        }
        catch (Exception e1) {
            System.out.println("Erreur initialisation (run de ServiceClient)") ;
            e1.printStackTrace();
        }

        String  message_lu = new String();
        int line_num =0 ;

        //Après initialisation

        while(true)
        {
            try{
                message_lu = in.readLine();
            }
            catch (IOException ioe) {

            }
            if(message_lu == null){
                System.out.println( "ex3_1.Client deconnecté, je termine\n" )  ;
                interrupt();
                return;
            }
            System.out.format( "%s [ligne_%d]--> [%s]]\n", id,line_num, message_lu);
            if(message_lu.contains(Finish) ){
                System.out.format ("[%s] :  [%s] recu, Transmission finie\n",id,message_lu);
                out.println("Fermeture connexion");
                interrupt();
                return;
            }
            line_num++;
        }

    }}
