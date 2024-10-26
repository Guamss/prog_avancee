package BAL;

public class BoiteAuxLettres {
    private String lettre;
    private boolean avalaible;

    public BoiteAuxLettres() {
        this.lettre = "";
        this.avalaible = true;
    }

    synchronized public void write(String bufferContent) {
        if (avalaible) {
            avalaible = false;
            lettre = bufferContent;
            avalaible = true;
        }
    }

    synchronized public String read() {
        return lettre;
    }
}
