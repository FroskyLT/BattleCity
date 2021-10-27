package lt.vgtu.game;

import javax.swing.*;
import java.awt.*;

public class Start extends JFrame {

    public Start() throws HeadlessException {
        Game game = new Game();

        game.addKeyListener(game);
        game.setFocusable(true);
        add(game);
        setVisible(true);

        setResizable(false);
        setTitle("Battle City");
        setBackground(Color.gray);
        setBounds(10, 10, 670, 670);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Start();
    }
}
