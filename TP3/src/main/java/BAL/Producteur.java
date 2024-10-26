package BAL;

public class Producteur implements Runnable {
    private BoiteAuxLettres boiteAuxLettres;
    private char bufferContent;

    public Producteur(BoiteAuxLettres boiteAuxLettres, char bufferContent) {
        this.boiteAuxLettres = boiteAuxLettres;
        this.bufferContent = bufferContent;
    }

    @Override
    public void run() {

        while (true)
        {
            try
            {
                Thread.sleep(1000);
                boiteAuxLettres.write(bufferContent);
                System.out.println("Producteur: J'écris '"+ bufferContent +"' dans la boîte aux lettres");
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }

        }
    }
}
