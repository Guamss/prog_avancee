package org.example;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Master is a client. It makes requests to numWorkers.
 */
public class MasterSocket {
    static int maxServer = 16;
    static final int[] tab_port = new int[maxServer];
    static String[] tab_total_workers = new String[maxServer];
    static final String ip = "127.0.0.1";
    static BufferedReader[] reader = new BufferedReader[maxServer];
    static PrintWriter[] writer = new PrintWriter[maxServer];
    static Socket[] sockets = new Socket[maxServer];


    public static void main(String[] args) throws Exception {

        // MC parameters
        ArrayList<Long> elapsedTimeArray = new ArrayList<>();
        int totalCount = 100000000; // total number of throws on a Worker
        int total = 0; // total number of throws inside quarter of disk
        double pi;

        int numWorkers = maxServer;
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String s; // for bufferRead

        System.out.println("#########################################");
        System.out.println("# Computation of PI by MC method        #");
        System.out.println("#########################################");

        System.out.println("\n How many workers for computing PI (< maxServer): ");
        try {
            s = bufferRead.readLine();
            numWorkers = Integer.parseInt(s);
            System.out.println(numWorkers);
        } catch (IOException ioE) {
            ioE.printStackTrace();
        }

        for (int i = 0; i < numWorkers; i++) {
            System.out.println("Enter worker" + i + " port : ");
            try {
                s = bufferRead.readLine();
                tab_port[i] = Integer.parseInt(s);
                System.out.println("You select " + s);
            } catch (IOException ioE) {
                ioE.printStackTrace();
            }
        }

        //create worker's socket
        for (int i = 0; i < numWorkers; i++) {
            sockets[i] = new Socket(ip, tab_port[i]);
            System.out.println("SOCKET = " + sockets[i]);

            reader[i] = new BufferedReader(new InputStreamReader(sockets[i].getInputStream()));
            writer[i] = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sockets[i].getOutputStream())), true);
        }

        totalCount = totalCount / numWorkers;
        String message_to_send;
        message_to_send = String.valueOf(totalCount);

        long stopTime, startTime;

        total = 0;
        for (int repeat = 0; repeat < 10; repeat++) {
            startTime = System.currentTimeMillis();
            // initialize workers
            for (int i = 0; i < numWorkers; i++) {
                writer[i].println(message_to_send);          // send a message to each worker
            }

            // listen to workers' messages
            for (int i = 0; i < numWorkers; i++) {
                tab_total_workers[i] = reader[i].readLine();      // read message from server
                System.out.println("Client sent: " + tab_total_workers[i]);
            }
            stopTime = System.currentTimeMillis();
            long timeDuration = (stopTime - startTime);
            elapsedTimeArray.add(timeDuration);
        }

            // compute PI with the result of each worker
            for (int i = 0; i < numWorkers; i++) {
                total += Integer.parseInt(tab_total_workers[i]);
            }
            pi = 4.0 * total / totalCount / numWorkers;

            double error = (Math.abs((pi - Math.PI)) / Math.PI);
            Collections.sort(elapsedTimeArray);
            System.out.println(Arrays.toString(elapsedTimeArray.toArray()));
            long elapsedTimeMedian = elapsedTimeArray.get(elapsedTimeArray.size() / 2);

            System.out.println("\nPi : " + pi);
            System.out.println("Error: " + error + "\n");

            System.out.println("Ntot: " + totalCount * numWorkers);
            System.out.println("Available processors: " + numWorkers);
            System.out.println("Time Duration (ms): " + elapsedTimeMedian + "\n");

            System.out.println((Math.abs((pi - Math.PI)) / Math.PI) + " " + totalCount * numWorkers + " " + numWorkers + " " + elapsedTimeMedian);

            System.out.println("\n Repeat computation (y/N): ");

            CsvOutput outputFile = new CsvOutput("./src/main/resources/output_forte_piSocket_machine_G26.csv");
            outputFile.write(error, totalCount * numWorkers, numWorkers, elapsedTimeMedian);

        for (int i = 0; i < numWorkers; i++) {
            System.out.println("END");     // Send ending message
            writer[i].println("END");
            reader[i].close();
            writer[i].close();
            sockets[i].close();
        }
    }
}