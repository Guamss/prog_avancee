package org.example;

import java.awt.*;
import org.example.Semaphore;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile mobile1;
    UnMobile mobile2;
    UnMobile mobile3;
    Semaphore semaphore;
    private final int LARG=800, HAUT=250;
    
    public UneFenetre()
    {
        super("le Mobile");
        setLayout (new GridLayout(3, 3));
        semaphore = new SemaphoreGeneral(1);

        mobile1 = new UnMobile(LARG, HAUT, semaphore);
        mobile2 = new UnMobile(LARG, HAUT, semaphore);
        mobile3 = new UnMobile(LARG, HAUT, semaphore);

        add(mobile1);
        add(mobile2);
        add(mobile3);

        Thread thread1 = new Thread(mobile1);
        Thread thread2 = new Thread(mobile2);
        Thread thread3 = new Thread(mobile3);

        thread1.start();
        thread2.start();
        thread3.start();
        setSize(LARG, HAUT);
        setVisible(true);
    }

}
