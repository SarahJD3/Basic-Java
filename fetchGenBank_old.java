/* 
File name: fetchGenBank.java
Written By: Sarah Schoem
Date: 28Jul2024 

*/

import java.util.Scanner;
import org.biojava.bio.BioException;
import org.biojavax.bio.db.ncbi.GenbankRichSequenceDB;
import org.biojavax.bio.db.ncbi.GenpeptRichSequenceDB;
import org.biojavax.bio.seq.RichSequence;

public class fetchGenBank {

    public static void main(String[] args) {
        RichSequence rs = null;
        Scanner input = new Scanner(System.in);

        try {
            System.out.println("Enter a GenBank accession number or gi number: ");
            String id = input.nextLine();

            System.out.println("Is the ID for a DNA or protein sequence? (DNA/protein): ");
            String type = input.nextLine().toLowerCase();

            if (type.equals("dna")) {
                GenbankRichSequenceDB grsdb = new GenbankRichSequenceDB();
                rs = grsdb.getRichSequence(id);
                System.out.println("Retrieved DNA Sequence:");
            } else if (type.equals("protein")) {
                GenpeptRichSequenceDB grsdb = new GenpeptRichSequenceDB();
                rs = grsdb.getRichSequence(id);
                System.out.println("Retrieved Protein Sequence:");
            } else {
                System.out.println("Invalid sequence type. Please enter 'DNA' or 'protein'.");
                System.exit(-1);
            }

            System.out.println(rs.getName() + " | " + rs.getDescription());
            System.out.println(rs.seqString());
            

        } catch (BioException be) {
            System.exit(-1);
        }
    }
}
