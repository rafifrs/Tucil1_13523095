package utils;

import java.awt.Color;

public class Coloring {
    private static final String[] ANSI_COLORS = {
        "\u001B[91m",    
        "\u001B[92m",   
        "\u001B[94m",   
        "\u001B[93m",   
        "\u001B[95m",    
        "\u001B[96m", 
        "\u001B[31m", 
        "\u001B[32m",   
        "\u001B[34m",   
        "\u001B[33m",   
        "\u001B[35m", 
        "\u001B[36m",    
        "\u001B[41m",  
        "\u001B[42m", 
        "\u001B[44m",   
        "\u001B[43m",
        "\u001B[45m", 
        "\u001B[46m", 
        "\u001B[101m",
        "\u001B[102m", 
        "\u001B[104m", 
        "\u001B[103m", 
        "\u001B[105m", 
        "\u001B[106m",
        "\u001B[90m",
        "\u001B[97m"  
    };

    public static final Color[] COLORS = {
        new Color(255, 102, 102),
        new Color(102, 255, 102),
        new Color(102, 102, 255),
        new Color(255, 255, 102),
        new Color(255, 102, 255),
        new Color(102, 255, 255),
        new Color(255, 153, 51), 
        new Color(153, 102, 255),
        new Color(255, 204, 102),
        new Color(204, 255, 102),
        new Color(102, 204, 255),
        new Color(255, 102, 204),
        new Color(204, 153, 255), 
        new Color(255, 178, 102),
        new Color(178, 255, 102),
        new Color(102, 255, 178),
        new Color(255, 102, 153),
        new Color(255, 153, 178),
        new Color(102, 255, 153),
        new Color(204, 102, 255),
        new Color(255, 229, 204),
        new Color(153, 255, 229),
        new Color(229, 153, 255),
        new Color(255, 255, 178),
        new Color(255, 204, 229),
        new Color(178, 204, 255)
    };
    
    public static final String RESET = "\u001B[0m";
    
    public static Color getColorForChar(char c) {
        int index = Character.toUpperCase(c) - 'A';
        if (index >= 0 && index < COLORS.length) {
            return COLORS[index];
        }
        return Color.BLACK;
    }
    
    public static String getAnsiColorForChar(char c) {
        if (!Character.isLetter(c)) {
            return "";
        }
        int index = Character.toUpperCase(c) - 'A';
        if (index >= 0 && index < ANSI_COLORS.length) {
            return ANSI_COLORS[index];
        }
        return "";
    }
    
    public static String colorize(String boardString) {
        StringBuilder coloredSb = new StringBuilder();
        String[] lines = boardString.split("\n");
        
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            for (char c : line.toCharArray()) {
                coloredSb.append(getAnsiColorForChar(c))
                        .append(c)
                        .append(RESET);
            }
            if (i < lines.length - 1) {
                coloredSb.append('\n');
            }
        }
        return coloredSb.toString();
    }
}