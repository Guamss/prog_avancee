package BAL;

public class Main {
    public static void main(String[] args) {
        BoiteAuxLettres boiteAuxLettres = new BoiteAuxLettres();
        Semaphore sem = new SemaphoreGeneral(1);
        Lecteur lecteur = new Lecteur(boiteAuxLettres, sem);
        Producteur producteur = new Producteur(boiteAuxLettres, sem);
        Thread threadProd = new Thread(producteur);
        Thread threadCon = new Thread(lecteur);
        threadCon.start();
        threadProd.start();
    }
}