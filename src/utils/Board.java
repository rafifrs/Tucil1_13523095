package utils;

public class Board {
    private char[][] grid;
    final int width;
    final int height;
    
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[width][height];
        clear();
    }
    
    // Deep copy constructor
    public Board(Board other) {
        this.width = other.width;
        this.height = other.height;
        this.grid = new char[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++)  {
                this.grid[x][y] = other.grid[x][y];
            }
        }
    }
    
    public void clear() {
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++)  {
                grid[x][y] = '#';
            }
        }
    }
    
    // Cek apakah blok bisa ditempatkan pada posisi (x,y)
    public boolean canPlace(Block block, int x, int y) {
        for (Point p : block.coordinates) {
            int newX = x + p.x;
            int newY = y + p.y;
            
            if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
                return false;
            }
            
            if (grid[newX][newY] != '#') {
                return false;
            }
        }
        return true;
    }
    
    // Tempatkan blok pada posisi (x,y)
    public void placeBlock(Block block, int x, int y) {
        for (Point p : block.coordinates) {
            grid[x + p.x][y + p.y] = block.type;
        }

    }
    
    // Hapus blok dari posisi (x,y)
    public void removeBlock(Block block, int x, int y) {
        for (Point p : block.coordinates) {
            grid[x + p.x][y + p.y] = '#';
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(grid[x][y]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public String toColoredString() {
        return Coloring.colorize(toString());
    }
}
