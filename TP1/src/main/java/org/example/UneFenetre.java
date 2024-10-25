package org.example;

import java.awt.*;
import java.util.concurrent.Semaphore;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile mobile1;
    UnMobile mobile2;
    UnMobile mobile3;
    Semaphore semaphore;
    private final int LARG=400, HAUT=250;
    
    public UneFenetre()
    {
        super("le Mobile");
        setLayout (new GridLayout(3, 3));
        mobile1 = new UnMobile(1, LARG, HAUT, this);
        mobile2 = new UnMobile(2, LARG, HAUT, this);
        mobile3 = new UnMobile(3, LARG, HAUT, this);
        add(mobile1);
        add(mobile2);
        add(mobile3);
        semaphore = new Semaphore(1);
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
