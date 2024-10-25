public class Producteur implements Runnable {
    private BoiteAuxLettres boiteAuxLettres;
    private char bufferContent;

    public Producteur(BoiteAuxLettres boiteAuxLettres, char bufferContent) {
        this.boiteAuxLettres = boiteAuxLettres;
        this.bufferContent = bufferContent;
    }

    @Override
    public void run() {
        boiteAuxLettres.write(bufferContent);
    }
}
