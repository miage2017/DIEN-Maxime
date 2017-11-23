package ex2a;

/**
 * Created by M.Dien on 16/11/2017.
 * MIAGE Student (M1)
 */

public class TestOrdre0 implements Runnable
{
    int pasDeComptage = 0;
    String nom = null;
    int maxV = 0;

    public TestOrdre0(String nom, int increment, int max)
    {
        this.pasDeComptage = increment;
        this.nom = nom;
        this.maxV = max;
    }

    public void run()
    {
        System.out.format("Initialisation [%s]\n", nom);
        for (int i = 0; i < maxV; i++)
        {
            waithere((int)Math.random()*(500));
            System.out.format("[%s] renvoie  %d\n", nom, i);
            i += pasDeComptage;

        }
    }

    public static void main(String[] args) throws Exception
    {
        if (args.length != 2)
        {
            System.out.println("2 arguments");
            System.exit( - 1);
        }

        int nbThreads = Integer.parseInt(args[0]);
        int maxValue = Integer.parseInt(args[1]);

        Thread[] mesJobs = new Thread[100];
        for (int t = 0; t < nbThreads; t++)
        {
            String jobName = String.format("Job_%d", t);
            TestOrdre0 tiJob = new TestOrdre0(jobName, t, maxValue);
            System.out.format("Création du thread %s\n", jobName);
            mesJobs[t] = new Thread(tiJob);
            mesJobs[t].start();
        }

        System.out.format("Main :Fini ici  !\n");
    }




    public  void waithere(int num)
    {
        try
        {
            int a =  num * (int)  (Math.random()  * 1000 );
            System.out.format("[%s] je vais dors  %d, milliseconded!\n", nom,a);
            Thread.sleep(a);
            System.out.format("[%s] Yop superbien dormis!\n", nom);

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
