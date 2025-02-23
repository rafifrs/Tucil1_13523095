package utils;

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Save {
    private static final String OUTPUT_DIR = "test/output/";
    private static final int CELL_SIZE = 50;
    private static final int PADDING = 10;

    public static void saveToFile(String inputFileName, boolean hasSolution, Board solution, long totalCases, long timeTaken) {
        try {
            new File(OUTPUT_DIR).mkdirs();
            String outputFile = OUTPUT_DIR + "solution_" + inputFileName;
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
            
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile));
            writer.print(output.toString());
            writer.close();
            
        } catch (IOException e) {
            System.err.println("Error saving output: " + e.getMessage());
        }
    }

    public static void saveToImg(String inputFileName, Board board) {
        try {
            int width = board.width;
            int height = board.height;
            
            BufferedImage image = new BufferedImage(
                width * CELL_SIZE + 2 * PADDING, 
                height * CELL_SIZE + 2 * PADDING, 
                BufferedImage.TYPE_INT_RGB
            );
            
            Graphics2D g2d = image.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
            
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    char block = board.grid[x][y]; 
                    if (block != ' ') {
                        g2d.setColor(Coloring.getColorForChar(block));
                        g2d.fillRoundRect(x * CELL_SIZE + PADDING, y * CELL_SIZE + PADDING, CELL_SIZE - 2, CELL_SIZE - 2, 10, 10);
                        g2d.setColor(Color.WHITE);
                        g2d.drawRoundRect(x * CELL_SIZE + PADDING, y * CELL_SIZE + PADDING, CELL_SIZE - 2, CELL_SIZE - 2, 10, 10);
                    }
                }
            }
            
            g2d.dispose();
            new File(OUTPUT_DIR).mkdirs();
            ImageIO.write(image, "jpg", new File(OUTPUT_DIR + "solution_" + inputFileName.replaceAll("\\.txt$", "") + ".jpg"));
            
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
        }
    }
}