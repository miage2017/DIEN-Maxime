package ex2b;

/**
 * Created by M.Dien on 16/11/2017.
 * MIAGE Student (M1)
 */

public class ObjetEntier {
    private int ma_valeur;
    public ObjetEntier()    {
        ma_valeur=0;
        System.out.format("Valeur partagee initialisee a %d\n", ma_valeur);
    }
    public String  toString(){
        return Integer.toString (ma_valeur);
    }
    public  synchronized  void add(int i) {
        ma_valeur+=i;
    }

}
