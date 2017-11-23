package ex3_1;

/**
 * Created by M.Dien on 21/11/2017.
 * MIAGE Student (M1)
 */
public class GenClients {
    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            String mon_id = String.format("Client [[%d]]", i);
            Client client_courant = new Client(mon_id);
            Thread cliThread= new Thread(client_courant);
            cliThread.start();
        }

    }
}
