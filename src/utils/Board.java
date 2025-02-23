package utils;

public class Board {
    public char[][] grid;
    final int width;
    final int height;
    
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[width][height];
        clear();
    }
    
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
                if (grid[x][y] == '.') {
                    grid[x][y] = '.';
                }
                else{
                    grid[x][y] = '#';
                }
            }
        }
    }

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
    
    public void placeBlock(Block block, int x, int y) {
        for (Point p : block.coordinates) {
            grid[x + p.x][y + p.y] = block.type;
        }

    }
    
    public void removeBlock(Block block, int x, int y) {
        
        for (Point p : block.coordinates) {
            if (grid[x + p.x][y + p.y] == '.') {
                grid[x + p.x][y + p.y] = '.';
            }
            else{
                grid[x + p.x][y + p.y] = '#';
            }
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
