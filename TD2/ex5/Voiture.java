package ex5;

/**
 * Created by M.Dien on 16/11/2017.
 * MIAGE Student (M1)
 */

public class Voiture implements Runnable {
    String nom;
    Parking park;
    public Voiture(String name, Parking park){
        this.nom=name;
        this.park=park;
    }

    public void run(){
        System.out.format("<[%s]> : Démarrage  \n", this.nom);
        while(true){
            try {
                Thread.sleep((long)  (4000* Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.format("<[%s]> : Demande pour RENTRER  \n", this.nom);
            try {
                this.rentrer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.format("<[%s]> : Docking \n", this.nom);
            try {
                Thread.sleep((long)  (4000* Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.format("<[%s]> : Demande pour SORTIR \n", this.nom);
            this.park.leave(this);
        }
    }

    public void rentrer() throws InterruptedException{
        while (!(this.park.accept(this)))
        {
            Thread.sleep((long)  (2000* Math.random()));
            System.out.format("<[%s]>  : Je redemande à RENTRER  \n", this.nom);
        }
    }

    public static void main(String[] args) {
        int tailleParking=8;
        int nbVoitures=15;
        Parking leParking = new Parking(tailleParking);
        Thread DesVoitures[] = new Thread[nbVoitures];
        for (int i =0; i< nbVoitures; i++){
            DesVoitures[i]= new Thread(new Voiture(String.format("Voiture <NUM %d> ", i) , leParking));
            DesVoitures[i].start();
        }
    }
}
