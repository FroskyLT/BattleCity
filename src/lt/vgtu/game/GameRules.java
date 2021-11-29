package lt.vgtu.game;

import java.awt.*;
import java.util.ArrayList;

public class GameRules {
    private ArrayList<Tank> tanks;
    private Map map;

    public GameRules(ArrayList<Tank> tanks, Map map) {
        this.tanks = tanks;
        this.map = map;
    }

    public boolean checkBulletCollision(Rectangle bulletBounds) {
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

    public boolean isGameOver() {
        return false;
    }
}
