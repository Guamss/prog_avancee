package BAL;

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
                String bufferContent = scanner.next();
                boiteAuxLettres.write(bufferContent);
                if (!Objects.equals(boiteAuxLettres.read(), "")) {
                    System.out.println("Producteur: J'écris '" + bufferContent + "' dans la boîte aux lettres");
                }
                else
                {
                    System.out.println("Producteur: La boîte aux lettres est pleines");
                }
                semaphore.syncSignal();
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }

        }
    }
}
