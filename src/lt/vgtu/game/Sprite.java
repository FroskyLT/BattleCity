package lt.vgtu.game;

import javax.swing.*;
import java.awt.*;

public class Sprite {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Image image;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        visible = true;
    }

    protected void getImageDimensions() {
        if (image != null) {
            width = image.getWidth(null);
            height = image.getHeight(null);
        }
    }

    protected void loadImage(String imageName) {
        ImageIcon i = new ImageIcon(imageName);
        image = i.getImage();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x * width;
    }

    public int getY() {
        return y * height;
    }

    public boolean isVisible() {
        return visible;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), width, height);
    }
}
