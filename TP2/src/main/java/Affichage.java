public class Affichage extends Thread {
	String texte;
	Semaphore semaphore;

	public Affichage(String txt, Semaphore semaphore) {
		texte = txt;
		this.semaphore = semaphore;
	}

	public void run() {
		semaphore.syncWait();
		for (int i = 0; i < texte.length(); i++) {
			System.out.print(texte.charAt(i));
			try {
				sleep(100);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		semaphore.syncSignal();
	}
}
