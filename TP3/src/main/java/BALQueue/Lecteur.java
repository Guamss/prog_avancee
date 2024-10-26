package BALQueue;

import java.util.Objects;

import static java.lang.System.exit;

public class Lecteur implements Runnable {

    private BoiteAuxLettres boiteAuxLettres;
    private String bufferContent;
    private Semaphore semaphore;

    public Lecteur(BoiteAuxLettres boiteAuxLettres, Semaphore semaphore) {
        this.boiteAuxLettres = boiteAuxLettres;
        this.semaphore = semaphore;
        bufferContent = "";
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                semaphore.syncWait();
                bufferContent = boiteAuxLettres.read();
                if (Objects.equals(bufferContent.toLowerCase(), "q"))
                {
                    System.out.println("Lecteur: Ok, je m'arrête");
                    exit(0);
                }
                else if (!bufferContent.equals(""))
                {
                    System.out.println("Lecteur: Je lis '" + bufferContent + "'");
                }
                else
                {
                    System.out.println("Lecteur: Il n'y a rien dans la boîte aux lettres");
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
