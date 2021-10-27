package lt.vgtu.game;

import javax.swing.*;
import java.awt.*;

public class Block {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    private boolean visible;

    private BlockType type;

    public Block(int x, int y, String imageName, BlockType type) {
        ImageIcon i = new ImageIcon(imageName);
        this.image = i.getImage();
        this.width = this.image.getWidth(null);
        this.height = this.image.getHeight(null);
        this.x = x * this.width;
        this.y = y * this.height;
        this.type = type;

        this.visible = true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
