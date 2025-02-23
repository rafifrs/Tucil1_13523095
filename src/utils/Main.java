package utils;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Data puzzleData;
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the file name: ");
            String fileName = scanner.nextLine();
            puzzleData = Reader.readFromFile("test/input/" + fileName);

            Board board;
            
            if (puzzleData.caseType == CaseType.DEFAULT) {
                board = new Board(puzzleData.width, puzzleData.height);
                System.out.println(board);
            } else {
                try (BufferedReader reader = new BufferedReader(new FileReader("test/input/" + fileName))) {
                reader.readLine();
                reader.readLine();
                board = Reader.readCustomBoard(reader, puzzleData.width, puzzleData.height);
                System.out.println(board);
                }
            }
            
            Solver solver = new Solver(puzzleData, board);

            long startTime = System.currentTimeMillis();
            boolean hasSolution = solver.solve();
            long endTime = System.currentTimeMillis();
            System.out.println("Time taken: " + (endTime - startTime) + " ms");
            
            if (!hasSolution) {
                System.out.println("No solution found!");
            }

            System.out.println("What format do you want to save the solution? ");
            System.out.println("1. txt format");
            System.out.println("2. jpg format");
            System.out.println("3. Dont save the solution");
            System.out.print("Choose the format: ");

            String answer = scanner.nextLine();
            
            // System.out.println(solver.board);
            if (answer.equalsIgnoreCase("1")) {

                Save.saveToFile(fileName, hasSolution, solver.board, solver.casesChecked, (endTime - startTime));
            }
            else if (answer.equalsIgnoreCase("2")) {
                Save.saveToImg(fileName, solver.board);
            }
            else {
                System.out.println("Solution not saved.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
