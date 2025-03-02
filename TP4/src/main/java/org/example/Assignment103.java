// Estimate the value of Pi using Monte-Carlo Method, using parallel program
package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class PiMonteCarlo1 {
    AtomicInteger nAtomSuccess;
    int nThrows;
    double value;

    class MonteCarlo implements Runnable {
        @Override
        public void run() {
            double x = Math.random();
            double y = Math.random();
            if (x * x + y * y > 1)
                nAtomSuccess.decrementAndGet();
        }
    }

    public PiMonteCarlo1(int i) {
        this.nAtomSuccess = new AtomicInteger(i);
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

public class Assignment103 {

    public static void main(String[] args) throws IOException {
        int nThrow = 10000000;
        int nProc = 16;
        long startTime;
        double value = 0;
        long stopTime;
        long timeDuration;
        ArrayList<Long> times = new ArrayList<>();
        for (int i = 1; i <= nProc; i++) {
            times.clear();
            for (int j = 0; j <= 10; j++) {
                PiMonteCarlo1 PiVal = new PiMonteCarlo1(nThrow*i);
                startTime = System.currentTimeMillis();
                value = PiVal.getPi(i);
                stopTime = System.currentTimeMillis();
                timeDuration = stopTime - startTime;
                times.add(timeDuration);
            }
            Collections.sort(times);
            System.out.println(Arrays.toString(times.toArray()));
            double error = Math.abs((value - Math.PI) / Math.PI);
            System.out.println("Approx value: " + value);
            System.out.println("Difference to exact value of pi: " + (value - Math.PI));
            System.out.println("Error: " + error * 100 + " %");
            System.out.println("Available processors: " + i);
            System.out.println("Time Duration: " + times.get(times.size() / 2) + "ms");

            CsvOutput outputFile = new CsvOutput("./src/main/resources/output_assigment103_.csv");
            outputFile.write(error, nThrow*i, i, times.get(times.size() / 2));
        }
    }
}