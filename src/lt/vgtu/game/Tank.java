package lt.vgtu.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tank extends Sprite {
    private final String playerName;
    private int x;
    private int y;
    private int previousX;
    private int previousY;

    private final int up;
    private final int down;
    private final int left;
    private final int right;
    private final int shoot;

    private Direction direction;

    private final ArrayList<String> imageNames;

    private final Color bulletColor;
    private Bullet bullet;

    private boolean isShooting;

    public Tank(String name, int x, int y, int up, int down, int left, int right, int shoot, Color bulletColor, ArrayList<String> imageNames) {
        super(x, y);
        this.playerName = name;
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

    private boolean isMoveControl(int control) {
        return control == up || control == right || control == down || control == left;
    }

    public void startShooting() {
        int stabilizedX = x + getWidth() / 2 - 5;
        int stabilizedY = y + getWidth() / 2 - 5;

        switch (direction) {
            case up -> bullet = new Bullet.BulletBuilder(stabilizedX, y).bulletColor(bulletColor).direction(direction).build();
            case down, right -> bullet = new Bullet.BulletBuilder(stabilizedX, stabilizedY).bulletColor(bulletColor).direction(direction).build();
            case left -> bullet = new Bullet.BulletBuilder(x, stabilizedY).bulletColor(bulletColor).direction(direction).build();
        }

        isShooting = true;
    }

    public void move(int control, GameRules gameRules) {
        if (control == up) {
            direction = Direction.up;
            Rectangle tankBounds = new Rectangle(x, y - 10, getWidth(), getHeight());

            this.setY(y - 10);
            if (gameRules.checkTankCollision(tankBounds)) this.restoreY();

        } else if (control == left) {
            direction = Direction.left;
            Rectangle tankBounds = new Rectangle(x - 10, y, getWidth(), getHeight());

            this.setX(x - 10);
            if (gameRules.checkTankCollision(tankBounds)) this.restoreX();
        } else if (control == down) {
            direction = Direction.down;
            Rectangle tankBounds = new Rectangle(x, y + 10, getWidth(), getHeight());

            this.setY(y + 10);
            if (gameRules.checkTankCollision(tankBounds)) this.restoreY();
        } else if (control == right) {
            direction = Direction.right;
            Rectangle tankBounds = new Rectangle(x + 10, y, getWidth(), getHeight());

            this.setX(x + 10);
            if (gameRules.checkTankCollision(tankBounds)) this.restoreX();
        }
    }

    public void processPlayerCommand(KeyEvent e, GameRules gameRules) {

        if (e.getKeyCode() == shoot && !isShooting) {
            this.startShooting();
        }

        if (isMoveControl(e.getKeyCode())) {
            this.move(e.getKeyCode(), gameRules);
        }
    }

    public void paint(Component c, Graphics g) {
        switch (direction) {
            case left -> {
                loadImage(imageNames.get(0));
                getImageDimensions();
            }
            case up -> {
                loadImage(imageNames.get(1));
                getImageDimensions();
            }
            case down -> {
                loadImage(imageNames.get(2));
                getImageDimensions();
            }
            case right -> {
                loadImage(imageNames.get(3));
                getImageDimensions();
            }
        }

        g.drawImage(getImage(), x, y, c);
    }

    public void disposeBullet() {
        this.bullet = null;
        this.isShooting = false;
    }

    private void setX(int x) {
        this.previousX = this.x;
        this.x = x;
    }

    private void setY(int y) {
        this.previousY = this.y;
        this.y = y;
    }

    private void restoreX() {
            this.x = this.previousX;
    }

    private void restoreY() {
            this.y = this.previousY;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isShooting() {
        return isShooting;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, getWidth(), getHeight());
    }
}