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

            List<Block> blocks = readBlocks(reader, blockCount);
    
            return new Data(width, height, blockCount, puzzleCase, blocks);
        }
    }

    public static List<Block> readBlocks(BufferedReader reader, int blockCount) throws IOException {
        List<Block> blocks = new ArrayList<>();
        String line;
        List<String> shapeLines = new ArrayList<>();
        char currentType = '\0';
    
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) continue; // Skip baris kosong
    
            char type = line.trim().charAt(0); // Ambil huruf pertama sebagai tipe blok
    
            if (type != currentType) {
                if (!shapeLines.isEmpty()) {
                    blocks.add(parseBlock(currentType, shapeLines));
                }
                shapeLines.clear();
                currentType = type;
            }
            shapeLines.add(line);
        }
    
        // Tambahkan blok terakhir setelah membaca file
        if (!shapeLines.isEmpty()) {
            blocks.add(parseBlock(currentType, shapeLines));
        }
    
        return blocks;
    }
    

    public static Block parseBlock(char type, List<String> shapeLines) {
        List<Point> points = new ArrayList<>();
    
        int height = shapeLines.size(); // Ukuran vertikal blok
    
        for (int row = 0; row < height; row++) {
            String line = shapeLines.get(row);
            for (int col = 0; col < line.length(); col++) {
                if (line.charAt(col) != ' ') {
                    // Balik koordinat Y agar sesuai dengan kuadran 1
                    points.add(new Point(col, height - 1 - row));
                }
            }
        }
    
        return new Block(type, normalizeCoordinates(points));
    }
    

    public static List<Point> normalizeCoordinates(List<Point> points) {
        if (points.isEmpty()) return points;

        // Cari nilai minimum x dan y untuk normalisasi
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (Point p : points) {
            minX = Math.min(minX, p.x);
            minY = Math.min(minY, p.y);
        }

        // Geser semua titik agar berada di kuadran 1
        List<Point> normalized = new ArrayList<>();
        for (Point p : points) {
            normalized.add(new Point(p.x - minX, p.y - minY));
        }

        return normalized;
    }
}