public class Main {

    public static void main(String[] args) {

        Semaphore sem = new SemaphoreBinaire(1);

        Affichage TA = new Affichage("AAAA", sem);
        Affichage TB = new Affichage("BBBB", sem);
        Affichage TC = new Affichage("CCCC", sem);
        Affichage TD = new Affichage("DDDD", sem);

        TA.start();
        TC.start();
        TB.start();
        TD.start();


    }

}
