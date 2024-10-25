public class Lecteur implements Runnable {

    private BoiteAuxLettres boiteAuxLettres;
    private char bufferContent;

    public Lecteur(BoiteAuxLettres boiteAuxLettres) {
        this.boiteAuxLettres = boiteAuxLettres;
        bufferContent = '\0';
    }

    @Override
    public void run() {
        bufferContent = boiteAuxLettres.read();
    }
}
