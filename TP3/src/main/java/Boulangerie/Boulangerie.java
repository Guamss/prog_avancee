package Boulangerie;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Boulangerie {
    private BlockingQueue<Pain> panier;

    public Boulangerie(int taillePanier)
    {
        panier = new ArrayBlockingQueue<>(taillePanier) ;
    }

    public Pain acheter() throws InterruptedException {
        return panier.take();
    }

    public void deposerPainEmpoisonne() throws InterruptedException {
        panier.put(Pain.PAIN_EMPOISONNE);
    }

    public void deposer(Pain pain) throws InterruptedException {
        panier.put(pain);
    }

    public int getStock() {
        return panier.size();
    }
}
