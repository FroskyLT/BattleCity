package lt.vgtu.game;

import java.awt.*;

public class Bullet {
    private int x;
    private int y;

    private Color bulletColor;
    private Direction direction;

    private Bullet(BulletBuilder builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.bulletColor = builder.bulletColor;
        this.direction = builder.direction;
    }

    public static class BulletBuilder {
        private final int x;
        private final int y;

        private Color bulletColor;
        private Direction direction;

        public BulletBuilder(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public BulletBuilder bulletColor(Color bulletColor) {
            this.bulletColor = bulletColor;
            return this;
        }

        public BulletBuilder direction(Direction direction) {
            this.direction = direction;
            return this;
        }

        public Bullet build() {
            return new Bullet(this);
        }

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
