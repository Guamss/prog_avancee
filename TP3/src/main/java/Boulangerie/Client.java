package Boulangerie;

public class Client implements Runnable{

    private String nom;
    private Boulangerie boulangerie;
    public Client(Boulangerie boulangerie, String nom)
    {
        this.nom = nom;
        this.boulangerie = boulangerie;
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep(3500);
                int stock = boulangerie.getStock();
                Pain pain = boulangerie.acheter();
                if (pain == Pain.PAIN_EMPOISONNE) {
                    System.out.println("Client " + nom + ": JE MEURS");
                    Thread.currentThread().interrupt();
                }
                System.out.println("Client " + nom +": J'achète du pain");

                if (stock > 0)
                {
                    System.out.println("Client " + nom +": MIAM MIAM");
                }
                else
                {
                    System.out.println("Client " + nom +": IL N'Y A PLUS DE PAIN !!! J'AI FAIM !!!");
                }
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
