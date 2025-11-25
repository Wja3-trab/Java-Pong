package main;

import entity.Ball;
import entity.Opponent;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public final int originalTileSize = 16;
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16, maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    Thread gameThread;
    final int FPS = 60;

    int[] delays = {3, 5, 7};

    public int middleHeight = screenHeight / 2;

    public KeyHandler keyHandler = new KeyHandler();
    public TileManager tileManager;
    public Player player = new Player(this, keyHandler);
    public Opponent opponent = new Opponent(this);
    public Ball ball = new Ball(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.tileManager = new TileManager(this);
    }

    public void startThread() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(int frames) {
        player.update();
        int index = (int) (Math.random() * delays.length);
        if (frames % delays[index] != 0) {
            opponent.update();
        }
        ball.update();
    }

    @Override
    public void run() {
        double drawInterVal = 1_000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterVal;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta > 1) {
                update(drawCount);
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1_000_000_000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        tileManager.draw(graphics2D);

        player.draw(graphics2D);

        opponent.draw(graphics2D);

        ball.draw(graphics2D);


        graphics2D.dispose();
    }
}
