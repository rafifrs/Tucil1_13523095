package utils;

import java.util.*;

public class Solver {
    public final Board board;
    private final List<Block> blocks;
    public int casesChecked = 0;
    
    public Solver(Data data, Board board) {
        this.board = board;
        this.blocks = data.blocks;
    }
    
    public boolean solve() {
        boolean foundSolution = solveRecursive(0, board);
        System.out.println("\nTotal cases checked: " + casesChecked);
        return foundSolution;
    }
    
    private boolean solveRecursive(int blockIndex, Board board) {
        if (blockIndex >= blocks.size()) {
            System.out.println("\nSolution found:");
            System.out.println(board.toColoredString());
            return true;
        }
        
        Block currentBlock = blocks.get(blockIndex);
        
        List<Block> orientations = getAllOrientations(currentBlock);

        for (Block orientation : orientations) {
            for (int x = board.width - 1; x >= 0; x--) {
                for (int y = 0; y < board.height; y++)  {
                    if (board.canPlace(orientation, x, y)) {
                        board.placeBlock(orientation, x, y);
                        if (solveRecursive(blockIndex + 1, board)) {
                            return true;
                        }
                        board.removeBlock(orientation, x, y);
                    }
                }
            }
        }
        casesChecked++;
        return false;
    }
    
    private List<Block> getAllOrientations(Block block) {
        List<Block> orientations = new ArrayList<>();
        
        Block current = new Block(block);
        
        for (int flip = 0; flip < 2; flip++) {
            for (int rot = 0; rot < 4; rot++) {
                orientations.add(new Block(current));
                current.rotate();
            }
            current.flip();
        }
        // System.out.println("INI ORIENTASI HASIL GET ALL" + orientations);
        return orientations;
    }
}