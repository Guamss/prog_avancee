package BAL;

public class BoiteAuxLettres {
    private char lettre;
    private boolean avalaible;

    public BoiteAuxLettres(char lettre) {
        this.lettre = lettre;
        this.avalaible = true;
    }

    synchronized public void write(char bufferContent) {
        if (avalaible) {
            avalaible = false;
            lettre = bufferContent;
            avalaible = true;
        }
    }

    synchronized public char read() {
        char lettreRetire = lettre;
        lettre = '\0';
        return lettreRetire;
    }
}
