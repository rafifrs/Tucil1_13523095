package utils;
import java.io.*;
import java.util.*;

class Reader {

    public static Data readFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Baca dimensi dan tipe case
            String[] dimensions = reader.readLine().trim().split("\\s+");
            if (dimensions.length != 3) {
                throw new IOException("Format dimensi tidak valid. Dibutuhkan: width height blockCount");
            }
    
            int height = Integer.parseInt(dimensions[0]);
            int width = Integer.parseInt(dimensions[1]);
            int blockCount = Integer.parseInt(dimensions[2]);
    
            String caseType = reader.readLine().trim().toUpperCase();
            CaseType puzzleCase;
            try {
                puzzleCase = CaseType.valueOf(caseType);
            } catch (IllegalArgumentException e) {
                throw new IOException("Tipe case tidak valid. Pilihan: DEFAULT, PYRAMID, CUSTOM");
            }

            List<Block> blocks;

            if (puzzleCase == CaseType.DEFAULT) {
                blocks = readBlocks(reader, blockCount);
            } else{
                for (int i = 0; i < height; i++) {
                    reader.readLine();
                }
                blocks = readBlocks(reader, blockCount);
            }
    
            return new Data(width, height, blockCount, puzzleCase, blocks);
        }
    }

    public static List<Block> readBlocks(BufferedReader reader, int blockCount) throws IOException {
        List<Block> blocks = new ArrayList<>();
        String line;
        List<String> shapeLines = new ArrayList<>();
        char currentType = '\0';
    
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) continue; 
    
            char type = line.trim().charAt(0); 
    
            if (type != currentType) {
                if (!shapeLines.isEmpty()) {
                    blocks.add(parseBlock(currentType, shapeLines));
                }
                shapeLines.clear();
                currentType = type;
            }
            shapeLines.add(line);
        }
    
        if (!shapeLines.isEmpty()) {
            blocks.add(parseBlock(currentType, shapeLines));
        }
    
        return blocks;
    }
    

    public static Block parseBlock(char type, List<String> shapeLines) {
        List<Point> points = new ArrayList<>();
    
        int height = shapeLines.size(); 
    
        for (int row = 0; row < height; row++) {
            String line = shapeLines.get(row);
            for (int col = 0; col < line.length(); col++) {
                if (line.charAt(col) != ' ') {
                    points.add(new Point(col, height - 1 - row));
                }
            }
        }
    
        return new Block(type, normalizeCoordinates(points));
    }
    

    public static List<Point> normalizeCoordinates(List<Point> points) {
        if (points.isEmpty()) return points;

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (Point p : points) {
            minX = Math.min(minX, p.x);
            minY = Math.min(minY, p.y);
        }

        List<Point> normalized = new ArrayList<>();
        for (Point p : points) {
            normalized.add(new Point(p.x - minX, p.y - minY));
        }

        return normalized;
    }

    public static Board readCustomBoard(BufferedReader reader, int width, int height) throws IOException {
        Board board = new Board(width, height);
        for (int y = height - 1; y >= 0; y--) {
            String line = reader.readLine();
            for (int x = 0; x < width; x++) {
                board.grid[x][y] = line.charAt(x);
            }
        }
        return board;
    }
}