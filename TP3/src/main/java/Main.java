public class Main {
    public static void main(String[] args) {
        BoiteAuxLettres boiteAuxLettres = new BoiteAuxLettres('a');
        Lecteur lecteur1 = new Lecteur(boiteAuxLettres);
        Lecteur lecteur2 = new Lecteur(boiteAuxLettres);
        Producteur producteur1 = new Producteur(boiteAuxLettres, 'b');
        Producteur producteur2 = new Producteur(boiteAuxLettres, 'c');
        Thread threadProd1 = new Thread(producteur1);
        Thread threadProd2 = new Thread(producteur2);
        Thread threadCon1 = new Thread(lecteur1);
        Thread threadCon2 = new Thread(lecteur2);
        threadCon1.start();
        threadCon2.start();
        threadProd1.start();
        threadProd2.start();
    }
}