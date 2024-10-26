package BAL;

public class Lecteur implements Runnable {

    private BoiteAuxLettres boiteAuxLettres;
    private char bufferContent;

    public Lecteur(BoiteAuxLettres boiteAuxLettres) {
        this.boiteAuxLettres = boiteAuxLettres;
        bufferContent = '\0';
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                bufferContent = boiteAuxLettres.read();
                if (bufferContent != '\0') {
                    System.out.println("Lecteur: Je lis '" + bufferContent + "'");
                } else {
                    System.out.println("Lecteur: Il n'y a rien dans la boîte aux lettres");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
