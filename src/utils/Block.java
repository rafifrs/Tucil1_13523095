package utils;
import java.util.*;

public class Block {
    char type;
    List<Point> coordinates;;
    private boolean isFlipped;
    private int rotation;
    
    public Block(char type, List<Point> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
        this.isFlipped = false;
        this.rotation = 0;
    }
    
    public Block(Block other) {
        this.type = other.type;
        this.coordinates = new ArrayList<>();
        for (Point p : other.coordinates) {
            this.coordinates.add(new Point(p.x, p.y));
        }
        this.isFlipped = other.isFlipped;
        this.rotation = other.rotation;
    }
    
    // Rotasi 90 derajat searah jarum jam
    public void rotate() {
        rotation = (rotation + 90) % 360;
        for (Point p : coordinates) {
            int temp = p.y;
            p.y = -p.x;
            p.x = temp;
        }
        normalizeToOrigin();
    }
    
    // Pencerminan horizontal
    public void flip() {
        isFlipped = !isFlipped;
        for (Point p : coordinates) {
            p.x = -p.x;
        }
        normalizeToOrigin();
    }

    // Normalisasi koordinat ke titik (0,0)
    private void normalizeToOrigin() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        
        for (Point p : coordinates) {
            minX = Math.min(minX, p.x);
            minY = Math.min(minY, p.y);
        }
        
        for (Point p : coordinates) {
            p.x -= minX;
            p.y -= minY;
        }
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Block ").append(type).append(":\n");
        sb.append("Rotation: ").append(rotation).append("°\n");
        sb.append("Flipped: ").append(isFlipped).append("\n");
        sb.append("Coordinates:\n");
        for (Point p : coordinates) {
            sb.append("(").append(p.x).append(",").append(p.y).append(")\n");
        }
        return sb.toString();
    }
}
