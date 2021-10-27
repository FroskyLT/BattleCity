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
    private Map map;

    private final Timer timer;
    private final int delay = 10;

    private Tank tank1;
    private Tank tank2;

    private ArrayList<Tank> tanks;

    private boolean gameOver;
    private String winner;

    public Game() {
        map = new Map();
        tanks = new ArrayList<>();
        tank1 = new Tank(
                "Player 1",
                200,
                550,
                KeyEvent.VK_W,
                KeyEvent.VK_S,
                KeyEvent.VK_A,
                KeyEvent.VK_D,
                KeyEvent.VK_SPACE,
                Color.yellow,
                new ArrayList<>(Arrays.asList(
                        "player1_tank_left.png",
                        "player1_tank_up.png",
                        "player1_tank_down.png",
                        "player1_tank_right.png"
                ))
        );
        tank2 = new Tank(
                "Player 2",
                400,
                550,
                KeyEvent.VK_UP,
                KeyEvent.VK_DOWN,
                KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT,
                KeyEvent.VK_ENTER,
                Color.red,
                new ArrayList<>(Arrays.asList(
                        "player2_tank_left.png",
                        "player2_tank_up.png",
                        "player2_tank_down.png",
                        "player2_tank_right.png"
                ))
        );
        tanks.add(tank1);
        tanks.add(tank2);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer = new Timer(delay, this);
        timer.start();

        gameOver = false;
    }

    public void paint(Graphics g) {
        // paint map
        g.setColor(Color.black);
        g.fillRect(0, 0, 670, 670);
        map.paint(this, g);

        if (!gameOver) {
            for (int i = 0; i < tanks.size(); i++) {
                Tank currTank = tanks.get(i);
                Tank enemyTank = i == 0 ? tanks.get(1) : tanks.get(0);

                currTank.paint(this, g);

                if (currTank.isShooting()) {
                    Bullet bullet = currTank.getBullet();

                    bullet.move();
                    bullet.paint(g);

                    if (bullet.getBounds().intersects(enemyTank.getBounds())) { // ???
                        currTank.setBullet(null);
                        currTank.setShooting(false);
                        winner = currTank.getName() + " won";
                        gameOver = true;
                    } else if (map.checkCollision(bullet.getBounds())) {
                        currTank.setBullet(null);
                        currTank.setShooting(false);
                    }
                }
            }
//            for (Tank tank : tanks) {
//                tank.paint(this, g);
//
//                if (tank.isShooting()) {
//                    Bullet bullet = tank.getBullet();
//
//                    bullet.move();
//                    bullet.paint(g);
//
//                    if (bullet.getBounds().intersects(tank.getBounds())) { // ???
//                        tank.setBullet(null);
//                        tank.setShooting(false);
//                        winner = tank.getName() + " won";
//                        gameOver = true;
//                    } else if (map.checkCollision(bullet.getBounds())) {
//                        tank.setBullet(null);
//                        tank.setShooting(false);
//                    }
//                }
//            }
        } else {
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.BOLD, 60));
            g.drawString("Game Over", 160, 270);
            g.drawString(winner, 140, 350);
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
        tank1.playerCommand(e);
        tank2.playerCommand(e);

        if (e.getKeyCode() == KeyEvent.VK_SPACE && gameOver) {
            map = new Map();
            tanks = new ArrayList<>();
            tank1 = new Tank(
                    "Player 1",
                    200,
                    550,
                    KeyEvent.VK_W,
                    KeyEvent.VK_S,
                    KeyEvent.VK_A,
                    KeyEvent.VK_D,
                    KeyEvent.VK_SPACE,
                    Color.yellow,
                    new ArrayList<>(Arrays.asList(
                            "player1_tank_left.png",
                            "player1_tank_up.png",
                            "player1_tank_down.png",
                            "player1_tank_right.png"
                    ))
            );
            tank2 = new Tank(
                    "Player 2",
                    400,
                    550,
                    KeyEvent.VK_UP,
                    KeyEvent.VK_DOWN,
                    KeyEvent.VK_LEFT,
                    KeyEvent.VK_RIGHT,
                    KeyEvent.VK_ENTER,
                    Color.red,
                    new ArrayList<>(Arrays.asList(
                            "player2_tank_left.png",
                            "player2_tank_up.png",
                            "player2_tank_down.png",
                            "player2_tank_right.png"
                    ))
            );
            tanks.add(tank1);
            tanks.add(tank2);

            gameOver = false;
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}

