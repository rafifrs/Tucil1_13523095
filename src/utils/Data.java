package utils;
import java.util.*;

enum CaseType {
    DEFAULT,
    PYRAMID,
    CUSTOM
}

class Data {
    public final int width;
    public final int height;
    public final int blockCount;
    public final CaseType caseType;
    public final List<Block> blocks;
    
    public Data(int width, int height, int blockCount, CaseType caseType, List<Block> blocks) {
        this.width = width;
        this.height = height;
        this.blockCount = blockCount;
        this.caseType = caseType;
        this.blocks = blocks;
    }
}
