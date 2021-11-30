package lt.vgtu.game.tests;

import lt.vgtu.game.*;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

public class UnitTests {

    @Test
    public void testBulletCollisionCheck() {
        Bullet bullet = new Bullet.BulletBuilder(10 , 10).build();
        Block block = new Block(1, 1, "wall_brick.png", BlockType.BRICK);
        Block block2 = new Block(30, 30, "wall_brick.png", BlockType.BRICK);

        boolean isIntersects = bullet.getBounds().intersects(block.getBounds());
        assertTrue(isIntersects);

        isIntersects = bullet.getBounds().intersects(block2.getBounds());
        assertFalse(isIntersects);
    }

    @Test
    public void testTankDirection() {
        Tank tank = new Tank(
                null,
                1,
                1,
                KeyEvent.VK_W,
                KeyEvent.VK_S,
                KeyEvent.VK_A,
                KeyEvent.VK_D,
                KeyEvent.VK_SPACE,
                null,
                null
        );
        Map map = new Map();

        assertSame(Direction.up, tank.getDirection());

        tank.move(KeyEvent.VK_S, map);

        assertSame(Direction.down, tank.getDirection());
    }

    @Test
    public void testIsTankShooting() {
        Tank tank = new Tank(
                null,
                1,
                1,
                KeyEvent.VK_W,
                KeyEvent.VK_S,
                KeyEvent.VK_A,
                KeyEvent.VK_D,
                KeyEvent.VK_SPACE,
                Color.yellow,
                null
        );

        assertFalse(tank.isShooting());

        tank.startShooting();

        assertTrue(tank.isShooting());
    }
}
