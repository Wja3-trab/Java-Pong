package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity {

    public int xPos2, yPos2;
    GamePanel gamePanel;

    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.width = gamePanel.tileSize;
        this.height = gamePanel.tileSize * 3;
        this.xPos = (int) (0.5 * gamePanel.tileSize);
        this.yPos = 4 * width;
        this.xPos2 = xPos + width;
        this.yPos2 = yPos + height;
        this.area = new Rectangle(xPos, yPos, width, height);
        this.speed = 4;
    }

    public void update() {
        if (keyHandler.upPressed || keyHandler.downPressed) {
            if (keyHandler.upPressed) {
                this.yPos -= speed;
                this.yPos = Math.max(0, yPos);
            } else {
                this.yPos += speed;
                this.yPos = Math.min(gamePanel.screenHeight - height - 1, yPos);
            }
            this.yPos2 = yPos + height;
            this.area = new Rectangle(xPos, yPos, width, height);
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.magenta);
        graphics2D.draw(area);
    }
}
