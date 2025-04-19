/*
File name: FastaReader.java
Written By: Sarah Schoem
Date: 29Jul2024
Edit: 17Apr2025
Description: This code reads a FASTA file and stores each sequence in a custom FastaRecord object without printing.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Custom class to hold FASTA data
class FastaRecord {
    private String header;
    private String sequence;

    public FastaRecord(String header, String sequence) {
        this.header = header;
        this.sequence = sequence;
    }

    public String getHeader() {
        return header;
    }

    public String getSequence() {
        return sequence;
    }
}

public class FastaReader {

    // Reads FASTA file and returns list of FastaRecord objects
    public static List<FastaRecord> readFasta(String filePath) throws IOException {
        List<FastaRecord> records = new ArrayList<>();
        String header = null;
        StringBuilder sequence = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.startsWith(">")) {
                    if (header != null) {
                        records.add(new FastaRecord(header, sequence.toString()));
                    }
                    header = line.substring(1).trim(); // Remove '>'
                    sequence.setLength(0); // Clear sequence builder
                } else {
                    sequence.append(line.trim());
                }
            }

            if (header != null) {
                records.add(new FastaRecord(header, sequence.toString())); // Add final record
            }
        }

        return records;
    }

    // Main method - loads data but does not print
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the path to the FASTA file: ");
        String filePath = scanner.nextLine();

        try {
            List<FastaRecord> records = readFasta(filePath);
            // Data stored in `records` but not printed
            // You can access them like: records.get(0).getSequence()
        } catch (IOException e) {
            System.out.println("Error reading FASTA file: " + e.getMessage());
        }

        scanner.close();
    }
}
