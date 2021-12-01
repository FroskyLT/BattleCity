package lt.vgtu.game;

import java.awt.*;
import java.util.ArrayList;

public class GameRules {
    private final ArrayList<Tank> tanks;
    private final Map map;

    private boolean gameOver;
    private String winner;

    public GameRules(ArrayList<Tank> tanks, Map map) {
        this.tanks = tanks;
        this.map = map;
    }

    public void processBulletCollision(Tank tank) {
        if(checkBulletBlockCollision(tank.getBullet())) {
            tank.disposeBullet();
        } else if (checkBulletTankCollision(tank)) {
            tank.disposeBullet();
            winner = tank.getPlayerName() + " won";
            gameOver = true;
        }
    }

    private boolean checkBulletTankCollision(Tank currTank) {
        Rectangle bulletBounds = currTank.getBullet().getBounds();

        for (Tank tank : tanks) {
            Rectangle tankBounds = tank.getBounds();

            if (bulletBounds.intersects(tankBounds) && currTank.getX() != tank.getX() && currTank.getY() != tank.getY()) {
                return true;
            }
        }

        return false;
    }

     private boolean checkBulletBlockCollision(Bullet bullet) {
        Rectangle bulletBounds = bullet.getBounds();

        for (Block block : map.getAllBlocks()) {
            Rectangle blockBounds = block.getBounds();

            if (bulletBounds.intersects(blockBounds) && block.isVisible()) {
                if (block.getType() == BlockType.BRICK) {
                    block.setVisible(false);
                }

                return true;
            }
        }

        return false;
    }

    public boolean checkTankCollision(Rectangle tankBounds) {
        for (Block block : map.getAllBlocks()) {
            Rectangle blockBounds = block.getBounds();

            if (tankBounds.intersects(blockBounds) && block.isVisible()) {
                return true;
            }
        }

        return false;
    }

    public String getWinner() {
        return winner;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
