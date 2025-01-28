package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class CsvOutput {
    private FileWriter csvFile;
    public CsvOutput(String pathToCsvFile) {
        try {
            csvFile = new FileWriter(pathToCsvFile, true);

            if (new File(pathToCsvFile).length() == 0) {
                csvFile.write("Erreur;Ntotal;Nprocessus;Temps(ms)\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String buildLine(Double error, int nTotal, int nProc, long temps) {
        return error + ";" + nTotal + ";" + nProc + ";" + temps + "\n";
    }

    public void write(Double error, int nTotal, int nProc, long temps) throws IOException {
        // data sous forme "erreur;ntot;nproc;temps\n"
        csvFile.write(CsvOutput.buildLine(error, nTotal, nProc, temps));
        csvFile.close();
    }


}
