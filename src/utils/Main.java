package utils;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Data puzzleData;
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.print("\033[33mEnter the file name: \033[0m");
            String fileName = scanner.nextLine();
            puzzleData = Reader.readFromFile("test/input/" + fileName);

            Board board;
            
            if (puzzleData.caseType == CaseType.DEFAULT) {
                board = new Board(puzzleData.width, puzzleData.height);
            } else {
                try (BufferedReader reader = new BufferedReader(new FileReader("test/input/" + fileName))) {
                reader.readLine();
                reader.readLine();
                board = Reader.readCustomBoard(reader, puzzleData.width, puzzleData.height);
                }
            }
            
            Solver solver = new Solver(puzzleData, board);

            long startTime = System.currentTimeMillis();
            boolean hasSolution = solver.solve();
            long endTime = System.currentTimeMillis();
            System.out.println("\033[32mTime taken: " + (endTime - startTime) + " ms\033[0m");
            
            if (!hasSolution) {
                System.err.println("\033[31mNo solution found!\033[0m");
            }
            System.out.println();
            System.out.println("\033[34mWhat format do you want to save the solution?\033[0m");
            System.out.println("1. txt format");
            System.out.println("2. jpg format");
            System.out.println("3. Dont save the solution");
            System.out.print("\033[33mChoose the format: \033[0m");

            String answer = scanner.nextLine();
            
            // System.out.println(solver.board);
            if (answer.equalsIgnoreCase("1")) {

                Save.saveToFile(fileName, hasSolution, solver.board, solver.casesChecked, (endTime - startTime));
                System.out.println("\033[32mSolution saved as text file.\033[0m"); 
            }
            else if (answer.equalsIgnoreCase("2")) {
                Save.saveToImg(fileName, solver.board);
                System.out.println("\033[32mSolution saved as image.\033[0m");
            }
            else {
                System.out.println("\033[31mSolution not saved.\033[0m");
            }
        } catch (IOException e) {
            System.err.println("\033[31m" + e.getMessage() + "\033[0m"); 
        }

    }
}
