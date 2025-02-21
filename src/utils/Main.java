package utils;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Data puzzleData;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the file name: ");
            String fileName = scanner.nextLine();
            puzzleData = Reader.readFromFile("test/input/" + fileName);
            
            // System.out.println("Initial empty board:");
            Board board = new Board(puzzleData.width, puzzleData.height);
            // System.out.println(board.toString());
            
            Solver solver = new Solver(puzzleData);

            long startTime = System.currentTimeMillis(); // Start time
            boolean hasSolution = solver.solve();
            long endTime = System.currentTimeMillis(); // End time
            System.out.println("Time taken: " + (endTime - startTime) + " ms");
            
            if (!hasSolution) {
                System.out.println("No solution found!");
            }

            System.out.print("Wanna save the solution? (y/n): ");
            String answer = scanner.nextLine();
            
            // System.out.println(solver.board);
            if (answer.equalsIgnoreCase("y")) {

                Save.saveToFile(fileName, hasSolution, solver.board, solver.casesChecked, (endTime - startTime));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
