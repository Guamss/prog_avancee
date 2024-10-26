package Boulangerie;

public class Main {

    public static void main(String[] args) {
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
    }
}
