package lt.vgtu.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Game extends JPanel implements ActionListener, KeyListener {
    private final Timer timer;

    private Map map;
    private ArrayList<Tank> tanks;
    private GameRules gameRules;

    public Game() {
        map = new Map();
        tanks = new ArrayList<>();
        Tank tank1 = new Tank(
                "Player 1",
                180,
                540,
                KeyEvent.VK_W,
                KeyEvent.VK_S,
                KeyEvent.VK_A,
                KeyEvent.VK_D,
                KeyEvent.VK_SPACE,
                Color.yellow,
                new ArrayList<>(Arrays.asList(
                        "player_tank_left.png",
                        "player_tank_up.png",
                        "player_tank_down.png",
                        "player_tank_right.png"
                ))
        );
        Tank tank2 = new Tank(
                "Player 2",
                430,
                540,
                KeyEvent.VK_UP,
                KeyEvent.VK_DOWN,
                KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT,
                KeyEvent.VK_ENTER,
                Color.lightGray,
                new ArrayList<>(Arrays.asList(
                        "tank_basic_left.png",
                        "tank_basic_up.png",
                        "tank_basic_down.png",
                        "tank_basic_right.png"
                ))
        );
        tanks.add(tank1);
        tanks.add(tank2);

        gameRules = new GameRules(tanks, map);

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        int delay = 10;
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // paint map
        g.setColor(Color.black);
        g.fillRect(0, 0, 670, 670);
        map.paint(this, g);
        for (Tank tank: tanks)
            tank.paint(this, g);

        if (!gameRules.isGameOver()) {
            tanks.forEach(tank -> {
                if (tank.isShooting()) {
                    Bullet bullet = tank.getBullet();

                    bullet.move();
                    bullet.paint(g);

                    gameRules.processBulletCollision(tank);
                }
            });

        } else {
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.BOLD, 60));
            g.drawString("Game Over", 160, 270);
            g.drawString(gameRules.getWinner(), 140, 350);
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.BOLD, 30));
            g.drawString("(Space to Restart)", 190, 400);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        for(Tank tank: tanks) {
            tank.processPlayerCommand(e, gameRules);
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && gameRules.isGameOver()) {
            map = new Map();
            tanks = new ArrayList<>();
            Tank tank1 = new Tank(
                    "Player 1",
                    180,
                    540,
                    KeyEvent.VK_W,
                    KeyEvent.VK_S,
                    KeyEvent.VK_A,
                    KeyEvent.VK_D,
                    KeyEvent.VK_SPACE,
                    Color.yellow,
                    new ArrayList<>(Arrays.asList(
                            "player_tank_left.png",
                            "player_tank_up.png",
                            "player_tank_down.png",
                            "player_tank_right.png"
                    ))
            );
            Tank tank2 = new Tank(
                    "Player 2",
                    430,
                    540,
                    KeyEvent.VK_UP,
                    KeyEvent.VK_DOWN,
                    KeyEvent.VK_LEFT,
                    KeyEvent.VK_RIGHT,
                    KeyEvent.VK_ENTER,
                    Color.lightGray,
                    new ArrayList<>(Arrays.asList(
                            "tank_basic_left.png",
                            "tank_basic_up.png",
                            "tank_basic_down.png",
                            "tank_basic_right.png"
                    ))
            );
            tanks.add(tank1);
            tanks.add(tank2);

            gameRules = new GameRules(tanks, map);

            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}

