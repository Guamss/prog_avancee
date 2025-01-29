package org.example;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * Approximates PI using the Monte Carlo method.  Demonstrates
 * use of Callables, Futures, and thread pools.
 */
public class Pi {
    public static void main(String[] args) throws Exception {
        long total = 0;
        // 10 workers, 50000 iterations each
        total = new Master().doRun(100000000, 16);
        System.out.println("total from Master = " + total);
    }
}

/**
 * Creates workers to run the Monte Carlo simulation
 * and aggregates the results.
 */
class Master {
    public ArrayList<Long> elaspsedTimeArray = new ArrayList<>();
    public long total = 0;
    public long doRun(int totalCount, int numWorkers) throws InterruptedException, ExecutionException, IOException {
        for (int x = 0; x < 10; x++) {
            long startTime = System.currentTimeMillis();

            // Create a collection of tasks
            List<Callable<Long>> tasks = new ArrayList<Callable<Long>>();
            for (int i = 0; i < numWorkers; ++i) {
                tasks.add(new Worker(totalCount));
            }

            // Run them and receive a collection of Futures
            ExecutorService exec = Executors.newFixedThreadPool(numWorkers);
            List<Future<Long>> results = exec.invokeAll(tasks);
            total = 0;

            // Assemble the results.
            for (Future<Long> f : results) {
                // Call to get() is an implicit barrier.  This will block
                // until result from corresponding worker is ready.
                total += f.get();
            }
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            elaspsedTimeArray.add(elapsedTime);
            exec.shutdown();
        }

        double pi = 4.0 * total / totalCount / numWorkers;
        double error = (Math.abs((pi - Math.PI)) / Math.PI);
        int nTot = totalCount * numWorkers;
        Collections.sort(elaspsedTimeArray);
        System.out.println(Arrays.toString(elaspsedTimeArray.toArray()));
        long meanElapsedTime = elaspsedTimeArray.get(elaspsedTimeArray.size() / 2);

        System.out.println("\nPi : " + pi);
        System.out.println("Error: " + error + "\n");

        System.out.println("Ntot: " + nTot);
        System.out.println("Available processors: " + numWorkers);
        System.out.println("Time Duration (ms): " + meanElapsedTime + "\n");

        new CsvOutput("./src/main/resources/output_pi_faible_monpc.csv").write(error, nTot, numWorkers, meanElapsedTime);

        return total;
    }
}

/**
 * Task for running the Monte Carlo simulation.
 */
class Worker implements Callable<Long> {
    private int numIterations;

    public Worker(int num) {
        this.numIterations = num;
    }

    @Override
    public Long call() {
        long circleCount = 0;
        Random prng = new Random();
        for (int j = 0; j < numIterations; j++) {
            double x = prng.nextDouble();
            double y = prng.nextDouble();
            if ((x * x + y * y) < 1) ++circleCount;
        }
        return circleCount;
    }
}
