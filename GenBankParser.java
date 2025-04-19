/* 
File name: GenBankParser.java
Written By: Sarah Schoem
Date: 28Jul2024
Edit: 19Apr2025
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class GenBankParser {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Allows users to enter own filepath
        System.out.print("Enter the full path to the GenBank file: ");
        String fileName = scanner.nextLine();
        scanner.close();
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean inSequence = false;

            while ((line = br.readLine()) != null) {
                // Process header lines
                if (line.startsWith("LOCUS") || line.startsWith("DEFINITION") ||
                    line.startsWith("ACCESSION") || line.startsWith("VERSION") ||
                    line.startsWith("KEYWORDS") || line.startsWith("SOURCE")) {
                    System.out.println(line);
                }

                // Handle sequence lines
                if (line.startsWith("ORIGIN")) {
                    inSequence = true;
                } else if (inSequence) {
                    if (line.trim().isEmpty()) {
                        inSequence = false; // End of sequence section
                    } else {
                        // Remove spaces and digits
                        line = line.replaceAll("\\s+|\\d+", "");
                        System.out.println("Sequence: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " +e.getMessage());
        }
    }
}







