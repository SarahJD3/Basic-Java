/*
Class Name: BIFS618
Week 7 Exercise
File name: FastaSequenceCounter.java
Program author name: Sarah Schoem
*/ 



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FastaSequenceCounter {

    public static void main(String[] args) {
        BufferedReader reader = null;
        int sequenceCount = 0;

        try {
            // Prompt the user for the sequence file name
            System.out.print("Enter the name of the sequence file: ");
            String fileName = System.console().readLine();

            // Open the file and read line by line
            reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                // Check if the line is a header line (starts with '>')
                if (line.startsWith(">")) {
                    sequenceCount++;
                }
            }

            // Print the result
            System.out.println("File " + fileName + " contains " + sequenceCount + " sequences");

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing the file: " + e.getMessage());
            }
        }
    }
}

