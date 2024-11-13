package BALQueue;

import java.util.Objects;
import java.util.Scanner;

public class Producteur implements Runnable {
    private BoiteAuxLettres boiteAuxLettres;
    private String bufferContent;
    private Semaphore semaphore;

    public Producteur(BoiteAuxLettres boiteAuxLettres, Semaphore semaphore) {
        this.boiteAuxLettres = boiteAuxLettres;
        this.semaphore = semaphore;
        this.bufferContent = "";
    }

    @Override
    public void run() {

        while (true)
        {
            try
            {
                Thread.sleep(1000);
                semaphore.syncWait();
                Scanner scanner = new Scanner(System.in);
                System.out.print("Entrez une lettre : ");
                bufferContent = scanner.next();
                boiteAuxLettres.write(bufferContent);
                System.out.println("Producteur: J'écris '" + bufferContent + "' dans la boîte aux lettres");
                
                semaphore.syncSignal();
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }

        }
    }
}
