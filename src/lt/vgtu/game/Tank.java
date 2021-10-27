package lt.vgtu.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tank {
    private String name;
    private int x;
    private int y;

    private final int up;
    private final int down;
    private final int left;
    private final int right;
    private final int shoot;

    private Direction direction;
    private Image image;
    private int height;
    private int width;

    private final ArrayList<String> imageNames;

    private final Color bulletColor;
    private Bullet bullet;

    private boolean isShooting;

    public Tank(String name, int x, int y, int up, int down, int left, int right, int shoot, Color bulletColor, ArrayList<String> imageNames) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.shoot = shoot;
        this.imageNames = imageNames;

        this.bulletColor = bulletColor;
        this.direction = Direction.up;
    }

    public void playerCommand(KeyEvent e) {

        if (e.getKeyCode() == shoot) {
            if (!isShooting) {
                if (direction == Direction.up) {
                    bullet = new Bullet(x + 20, y, bulletColor, direction);
                } else if (direction == Direction.down) {
                    bullet = new Bullet(x + 20, y + 40, bulletColor, direction);
                } else if (direction == Direction.right) {
                    bullet = new Bullet(x + 40, y + 20, bulletColor, direction);
                } else if (direction == Direction.left) {
                    bullet = new Bullet(x, y + 20, bulletColor, direction);
                }

                isShooting = true;
            }
        }

        if (e.getKeyCode() == up) {
            direction = Direction.up;

            if (!(y < 10)) y -= 10;

        }

        if (e.getKeyCode() == left) {
            direction = Direction.left;

            if (!(x < 10)) x -= 10;
        }

        if (e.getKeyCode() == down) {
            direction = Direction.down;

            if (!(y > 540)) y += 10;
        }

        if (e.getKeyCode() == right) {
            direction = Direction.right;

            if (!(x > 590)) x += 10;
        }
    }

    public void paint(Component c, Graphics g) {
        ImageIcon i;
        switch (direction) {
            case left -> {
                i = new ImageIcon(imageNames.get(0));
                image = i.getImage();
                width = image.getWidth(null);
                height = image.getHeight(null);
            }
            case up -> {
                i = new ImageIcon(imageNames.get(1));
                image = i.getImage();
                width = image.getWidth(null);
                height = image.getHeight(null);
            }
            case down -> {
                i = new ImageIcon(imageNames.get(2));
                image = i.getImage();
                width = image.getWidth(null);
                height = image.getHeight(null);
            }
            case right -> {
                i = new ImageIcon(imageNames.get(3));
                image = i.getImage();
                width = image.getWidth(null);
                height = image.getHeight(null);
            }
        }

        g.drawImage(image, x, y, c);
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }
}