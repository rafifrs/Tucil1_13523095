package utils;

public class Coloring {
    public static final String[] COLORS = {
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
    
    public static final String RESET = "\u001B[0m";
    
    public static String getColorForChar(char c) {
        if (!Character.isLetter(c)) {
            return "";
        }
        int index = Character.toUpperCase(c) - 'A';
        if (index >= 0 && index < COLORS.length) {
            return COLORS[index];
        }
        return "";
    }
    
    // New method to convert board string to colored string
    public static String colorize(String boardString) {
        StringBuilder coloredSb = new StringBuilder();
        String[] lines = boardString.split("\n");
        
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            for (char c : line.toCharArray()) {
                coloredSb.append(getColorForChar(c))
                        .append(c)
                        .append(RESET);
            }
            if (i < lines.length - 1) {  // Don't add newline after last line
                coloredSb.append('\n');
            }
        }
        return coloredSb.toString();
    }
}