/**
 * Created by M.Dien on 05/12/2017.
 * MIAGE Student (M1)
 */
public class Philosophe implements Runnable {
    private String name;
    private Baguette droite;
    private Baguette gauche;
    private int nbManger;
    private boolean baguetteDroite;
    private boolean baguetteGauche;

    public Philosophe(String name, Baguette droite, Baguette gauche) {
        this.name=name;
        this.droite=droite;
        this.gauche=gauche;
        this.nbManger=0;
        this.baguetteDroite=false;
        this.baguetteGauche=false;
    }

    public int getNbManger() {
        return this.nbManger;
    }

    public int NbBaguetteDetenu() {

        int baguettesEnPossessions=0;
        if(this.baguetteGauche) {
            baguettesEnPossessions++;
        }
        if(this.baguetteDroite) {
            baguettesEnPossessions++;
        }
        return baguettesEnPossessions;
    }

    public String getName() {
        return this.name;
    }

    private void prendreBaguettes() {
        int a = (int)  (Math.random()  * 2 );
        if(a==0) {
            if(!this.baguetteDroite) {
                this.baguetteDroite = this.droite.prendre(this);
            }
        }else {
            if(!this.baguetteGauche) {
                this.baguetteGauche= this.gauche.prendre(this);
            }
        }
    }

    private void manger() {
        if(baguetteDroite && baguetteGauche) {
            nbManger++;
            this.droite.poser();
            this.baguetteDroite=false;
            this.gauche.poser();
            this.baguetteGauche=false;
        }
    }



    @Override
    public void run() {
        Thread.currentThread();
        while(!Thread.interrupted()) {
            this.prendreBaguettes();
            this.manger();
        }
    }

}
