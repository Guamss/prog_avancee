package Boulangerie;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Boulangerie boulangerie = new Boulangerie(20);
        Boulanger boulanger = new Boulanger(boulangerie);
        Client baptiste = new Client(boulangerie, "Baptiste");
        Client alexis = new Client(boulangerie, "Alexis");
        Client seb = new Client(boulangerie, "Seb");
        Thread boulangerThread = new Thread(boulanger);
        Thread baptisteThread = new Thread(baptiste);
        Thread alexisThread = new Thread(alexis);
        Thread sebThread = new Thread(seb);
        boulangerThread.start();
        baptisteThread.start();
        alexisThread.start();
        sebThread.start();

        try {
            Thread.sleep(10000);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        boulangerThread.interrupt();

        for (int i = 0; i < 3; i++) {
            boulangerie.deposerPainEmpoisonne();
        }
    }
}
