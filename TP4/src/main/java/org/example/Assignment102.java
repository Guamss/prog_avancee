// Estimate the value of Pi using Monte-Carlo Method, using parallel program
package org.example;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class PiMonteCarlo {
    AtomicInteger nAtomSuccess;
    int nThrows;
    double value;

    class MonteCarlo implements Runnable {
        @Override
        public void run() {
            double x = Math.random();
            double y = Math.random();
            if (x * x + y * y <= 1)
                nAtomSuccess.incrementAndGet();
        }
    }

    public PiMonteCarlo(int i) {
        this.nAtomSuccess = new AtomicInteger(0);
        this.nThrows = i;
        this.value = 0;
    }

    public double getPi(int nProcessors) {
        ExecutorService executor = Executors.newWorkStealingPool(nProcessors);
        for (int i = 1; i <= nThrows; i++) {
            Runnable worker = new MonteCarlo();
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        value = 4.0 * nAtomSuccess.get() / nThrows;
        return value;
    }
}

public class Assignment102 {
    public static void main(String[] args) throws IOException {
        int nThrow = Integer.parseInt(args[0]);
        int nProc = Integer.parseInt(args[1]);
        PiMonteCarlo PiVal = new PiMonteCarlo(nThrow);
        long startTime = System.currentTimeMillis();
        double value = PiVal.getPi(nProc);
        long stopTime = System.currentTimeMillis();

        Double error = Math.abs((value - Math.PI) / Math.PI);
        long timeDuration = stopTime - startTime;

        System.out.println("Approx value: " + value);
        System.out.println("Difference to exact value of pi: " + (value - Math.PI));
        System.out.println("Error: " + error * 100 + " %");
        System.out.println("Available processors: " + nProc);
        System.out.println("Time Duration: " + timeDuration + "ms");

        CsvOutput outputFile = new CsvOutput("./src/main/resources/output_assignment102.csv");
        outputFile.write(error, nThrow,nProc, timeDuration);
    }
}