package BAL;

public class Main {
    public static void main(String[] args) {
        BoiteAuxLettres boiteAuxLettres = new BoiteAuxLettres('\0');
        Lecteur lecteur = new Lecteur(boiteAuxLettres);
        Producteur producteur = new Producteur(boiteAuxLettres, 'a');
        Thread threadProd = new Thread(producteur);
        Thread threadCon = new Thread(lecteur);
        threadCon.start();
        threadProd.start();
    }
}