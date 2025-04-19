/* 
File name: fetchGenBank.java
Written By: Sarah Schoem
Date: 28Jul2024 
Description: This code searches the NCBI GenBank for accession numbers provided and prints on the screen the sequence associated.
Example Accession Number used for testing program functions: U49845
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class modified_fetchGenBank {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a GenBank accession number: ");
        String accession = input.nextLine().trim();

        try {
            // NCBI efetch URL (GenBank format)
            String efetchURL = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi" +
                               "?db=nuccore&id=" + accession +
                               "&rettype=gb&retmode=text";

            URL url = new URL(efetchURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            System.out.println("\n--- GenBank Entry ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
            input.close();

            System.out.println("\nProgram executed by Sarah Schoem");

        } catch (Exception e) {
            System.err.println("Error fetching sequence: " + e.getMessage());
        }
    }
}
