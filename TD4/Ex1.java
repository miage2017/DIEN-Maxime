import java.util.ArrayList;

/**
 * Created by M.Dien on 05/12/2017.
 * MIAGE Student (M1)
 */
public class Ex1 {
    public static int nbPhilosophe = 100;

    public static void main(String[] args) {
        ArrayList<Philosophe> listPhilosophes = new ArrayList<>();
        ArrayList<Baguette> listBaguettes = new ArrayList<>();
        ArrayList<Thread> allThread = new ArrayList<>();

        for(int i=0;i<Ex1.nbPhilosophe;i++) {
            listBaguettes.add(new Baguette());
        }
        int j=0;
        for(int i=0;i<Ex1.nbPhilosophe;i++) {

            if(j+1<listBaguettes.toArray().length) {
                listPhilosophes.add(new Philosophe("philosophe --> n°"+ i +" ||",(Baguette) listBaguettes.toArray()[j],(Baguette) listBaguettes.toArray()[j+1]));
                if(j+2!=listBaguettes.toArray().length) {
                    j+=2;
                }else {
                    j++;
                }
            }else {
                listPhilosophes.add(new Philosophe("philosophe --> n°"+ i +" ||",(Baguette) listBaguettes.toArray()[j],(Baguette) listBaguettes.toArray()[0]));
            }

    }
        Thread t;
        for(int i=0;i<listPhilosophes.toArray().length;i++) {
            t= new Thread((Philosophe) listPhilosophes.toArray()[i]);
            allThread.add(t);
            t.start();
        }

        t = new Thread(new Runnable() {

            @Override
            public void run() {
                int a =  10* (int)  (Math.random()  * 1000 );
                System.out.println("SLEEP : "+a+"ms");
                try {
                    Thread.currentThread().sleep(a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0;i<allThread.size();i++) {
            ((Thread) allThread.toArray()[i]).interrupt();
        }
        for(int i=0;i<listPhilosophes.toArray().length;i++) {
            Philosophe p = (Philosophe) listPhilosophes.toArray()[i];
            System.out.println(p.getName()+" j'ai mangé "+p.getNbManger()+" j'ai "+p.NbBaguetteDetenu()+" baguette(s)");
        }

    }
}
