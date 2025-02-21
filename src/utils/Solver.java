package utils;

import java.util.*;

public class Solver {
    public final Board board;
    private final List<Block> blocks;
    public int casesChecked = 0;
    
    public Solver(Data data) {
        this.board = new Board(data.width, data.height);
        this.blocks = data.blocks;
    }
    
    public boolean solve() {
        boolean foundSolution = solveRecursive(0);
        System.out.println("\nTotal cases checked: " + casesChecked);
        return foundSolution;
    }
    
    private boolean solveRecursive(int blockIndex) {
        // Base case: semua blok sudah ditempatkan
        if (blockIndex >= blocks.size()) {
            System.out.println("\nSolution found:");
            System.out.println(board.toColoredString());
            return true;
        }
        
        Block currentBlock = blocks.get(blockIndex);
        
        // Coba semua kemungkinan orientasi
        List<Block> orientations = getAllOrientations(currentBlock);
        // Coba setiap orientasi di setiap posisi
        for (Block orientation : orientations) {
            for (int x = board.width - 1; x >= 0; x--) {
                for (int y = 0; y < board.height; y++)  {
                    if (board.canPlace(orientation, x, y)) {
                        board.placeBlock(orientation, x, y);
                        if (solveRecursive(blockIndex + 1)) {
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
        
        // Original block
        Block current = new Block(block);
        
        // Try all rotations and flips
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