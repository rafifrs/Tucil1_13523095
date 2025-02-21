package utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Save {
    private static final String OUTPUT_DIR = "test/output/";

    public static void saveToFile(String inputFileName, boolean hasSolution, Board solution, long totalCases, long timeTaken) {
        try {
            // Create output directory if doesn't exist
            new File(OUTPUT_DIR).mkdirs();
            
            // Generate filename with timestamp
            String outputFile = OUTPUT_DIR + "solution_" + inputFileName;
            
            // Create output content
            StringBuilder output = new StringBuilder();
            if (hasSolution) {
                output.append("Solution found:\n");
                output.append(solution);
            }
            output.append("Total cases checked: ").append(totalCases).append("\n");
            output.append("Time taken: ").append(timeTaken).append(" milliseconds");
            if (!hasSolution) {
                output.append("\nNo solution found!");
            }
            
            // Write output
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile));
            writer.print(output.toString());
            writer.close();
            
        } catch (IOException e) {
            System.err.println("Error saving output: " + e.getMessage());
        }
    }
}