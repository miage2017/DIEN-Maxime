import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by M.Dien on 05/12/2017.
 * MIAGE Student (M1)
 */
public class Baguette extends ReentrantLock{
    private Philosophe philosophe;

    public synchronized boolean prendre(Philosophe unPhilosophe) {
        if(this.philosophe!=null) {
            return false;
        }else {
            this.philosophe = unPhilosophe;
            return true;

        }
    }

    public synchronized boolean poser() {
        if(this.philosophe!=null) {
            this.philosophe=null;
            return true;
        }else {
            return false;
        }
    }

}
