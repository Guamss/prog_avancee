package BALQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BoiteAuxLettres {
    private BlockingQueue<String> lettre;
    private boolean avalaible;

    public BoiteAuxLettres() {
        lettre =  new ArrayBlockingQueue<>(1);
        this.avalaible = true;
    }

    synchronized public void write(String bufferContent) throws InterruptedException {
        if (avalaible) {
            avalaible = false;
            lettre.put(bufferContent);
            avalaible = true;
        }
    }

    synchronized public String read() throws InterruptedException {
        if (lettre.size() == 0)
        {
            return "";
        }
        return lettre.take();
    }
}
