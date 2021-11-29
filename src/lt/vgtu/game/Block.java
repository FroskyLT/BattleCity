package lt.vgtu.game;

public class Block extends Sprite {
    private final BlockType type;

    public Block(int x, int y, String imageName, BlockType type) {
        super(x, y);
        loadImage(imageName);
        getImageDimensions();

        this.type = type;
    }

    public BlockType getType() {
        return type;
    }
}
