package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class UnMobile extends JPanel implements Runnable {
    int saLargeur, saHauteur, sonDebutDessin, sonTemps;
    final int sonPas = 10, sonCote = 40;
    Semaphore semaphore;

    public UnMobile(int telleLargeur, int telleHauteur, Semaphore semaphore) {
        super();
        this.semaphore = semaphore;
        Random r = new Random();
        sonTemps = 50 + r.nextInt(150);
        saLargeur = telleLargeur;
        saHauteur = telleHauteur;
        setSize(telleLargeur, telleHauteur);
    }

    @Override
    public void run() {
        for (sonDebutDessin = 0; sonDebutDessin * 3 < saLargeur - sonPas; sonDebutDessin += sonPas) {
            repaint();
            try {
                Thread.sleep(sonTemps);
            } catch (InterruptedException telleExcp) {
                telleExcp.printStackTrace();
            }
        }
        semaphore.syncWait(); // Le mobile entre dans la zone critique en avancant
        for (sonDebutDessin = saLargeur / 3; (3 * sonDebutDessin / 2) < saLargeur - sonPas; sonDebutDessin += sonPas) {
            repaint();
            try {
                Thread.sleep(sonTemps);
            } catch (InterruptedException telleExcp) {
                telleExcp.printStackTrace();
            }
        }
        semaphore.syncSignal(); // Le mobile sort de la zone critique en avancant
        for (sonDebutDessin = 2 * saLargeur / 3; sonDebutDessin < saLargeur - sonPas; sonDebutDessin += sonPas) {
            repaint();
            try {
                Thread.sleep(sonTemps);
            } catch (InterruptedException telleExcp) {
                telleExcp.printStackTrace();
            }
        }
        for (sonDebutDessin = saLargeur; sonDebutDessin > (saLargeur / 3) * 2; sonDebutDessin -= sonPas) {
            repaint();
            try {
                Thread.sleep(sonTemps);
            } catch (InterruptedException telleExcp) {
                telleExcp.printStackTrace();
            }
        }
        semaphore.syncWait(); // Le mobile entre dans la zone critique en reculant
        for (sonDebutDessin = (saLargeur / 3) * 2; sonDebutDessin > saLargeur / 3; sonDebutDessin -= sonPas) {
            repaint();
            try {
                Thread.sleep(sonTemps);
            } catch (InterruptedException telleExcp) {
                telleExcp.printStackTrace();
            }
        }
        semaphore.syncSignal(); // Le mobile sort de zone critique en reculant
        for (sonDebutDessin = saLargeur / 3; sonDebutDessin > sonPas; sonDebutDessin -= sonPas) {
            repaint();
            try {
                Thread.sleep(sonTemps);
            } catch (InterruptedException telleExcp) {
                telleExcp.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics telContexteGraphique) {
        super.paintComponent(telContexteGraphique);
        telContexteGraphique.fillRect(sonDebutDessin, saHauteur / 2, sonCote, sonCote);
    }

}