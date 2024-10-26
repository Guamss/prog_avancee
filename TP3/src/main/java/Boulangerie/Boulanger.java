package Boulangerie;

public class Boulanger implements Runnable{

    private Boulangerie boulangerie;
    public Boulanger(Boulangerie boulangerie)
    {
        this.boulangerie = boulangerie;
    }

    @Override
    public void run() {

        while (true)
        {
            try
            {
                Thread.sleep(1000);
                boulangerie.deposer(new Pain());
                System.out.println("Boulanger: Je dépose un pain, il en reste maintenant " + boulangerie.getStock());
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }

    }
}
