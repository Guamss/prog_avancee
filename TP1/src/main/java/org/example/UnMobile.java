package org.example;

import java.util.Random;
import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable {
	private int saLargeur, saHauteur, sonDebDessin;
	private final int sonPas = 10, sonCote = 40;
	private UneFenetre fenetre;
	private int sonTemps;
	private int mobileID;

	UnMobile(int id, int telleLargeur, int telleHauteur, UneFenetre fenetre) {
		super();
		Random r = new Random();
		mobileID = id;
		saLargeur = telleLargeur;
		saHauteur = telleHauteur;
		setSize(telleLargeur, telleHauteur);
		this.fenetre = fenetre;
		sonTemps = 30 + r.nextInt(2201);
	}

	public void run() {
		while (true) {
			for (sonDebDessin = 0; sonDebDessin < saLargeur / 3; sonDebDessin += sonPas) {
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (sonDebDessin = saLargeur; sonDebDessin < saLargeur / 3; sonDebDessin -= sonPas) {
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			try {
				fenetre.semaphore.acquire();
				for (; sonDebDessin < 2 * saLargeur / 3; sonDebDessin += sonPas) {
					repaint();
					Thread.sleep(sonTemps);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				fenetre.semaphore.release();
			}

			for (; sonDebDessin < saLargeur; sonDebDessin += sonPas) {
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public void paintComponent(Graphics telCG) {
		super.paintComponent(telCG);
		telCG.fillRect(sonDebDessin, saHauteur / 2, sonCote, sonCote);
	}
}