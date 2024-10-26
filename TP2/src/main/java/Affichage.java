/**
 * 
 */
import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;

class Exclusion{};
public class Affichage extends Thread{
	String texte; 
	Semaphore semaphore;

     static Exclusion exclusionMutuelle = new Exclusion();

	public Affichage (String txt, Semaphore semaphore){
		texte=txt;
		this.semaphore = semaphore;
	}
	
	public void run() {
		semaphore.syncWait();
		for (int i = 0; i < texte.length(); i++)
		{
			System.out.print(texte.charAt(i));
			try
			{
				sleep(100);
			}
			catch (InterruptedException e)
			{
				System.out.println(e);
			}
		}
		semaphore.syncSignal();
	}
}
