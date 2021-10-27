package lt.vgtu.game;

import java.awt.*;

public class Bullet {
    private int x;
    private int y;

    private Color bulletColor;
    private Direction direction;

    public Bullet(int x, int y, Color bulletColor, Direction direction) {
        this.x = x;
        this.y = y;

        this.bulletColor = bulletColor;
        this.direction = direction;
    }

    public void move() {
        if (direction == Direction.right)
            x += 5;
        else if (direction == Direction.left)
            x -= 5;
        else if (direction == Direction.up)
            y -= 5;
        else
            y += 5;
    }

    public void paint(Graphics g) {
        g.setColor(bulletColor);
        g.fillOval(x, y, 10, 10);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
