
public final class SemaphoreBinaire extends Semaphore {
    public SemaphoreBinaire(int valeurInitiale) {
        super((valeurInitiale != 0) ? 1 : 0);
        System.out.println("J'entre dans la section critique");
    }

    public final synchronized void syncSignal() {
        super.syncSignal();
        if (valeur > 1)
            valeur = 1;
        System.out.println("Je sors de la section critique");
    }
}
