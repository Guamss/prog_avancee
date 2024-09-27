package org.example;

import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile sonMobile;
    private final int LARG=400, HAUT=250;
    
    public UneFenetre()
    {
        super("le Mobile");
        Container container = getContentPane();
        sonMobile = new UnMobile(LARG, HAUT);
        container.add(sonMobile);
        Thread thread = new Thread(sonMobile);
        thread.start();
        setSize(LARG, HAUT);
        setVisible(true);
    }
}
