
public final class SemaphoreBinaire extends Semaphore {
public SemaphoreBinaire(int valeurInitiale){
	super((valeurInitiale != 0) ? 1:0);
}
public final synchronized void syncSignal(){
	super.syncSignal();
	if (valeur>1) valeur = 1;
}
}

