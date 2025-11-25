package entity;

import main.GamePanel;

import java.awt.*;

public class Opponent extends Entity {
    public int xPos2, yPos2, yDelta;
    GamePanel gamePanel;

    public Opponent(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.width = gamePanel.tileSize;
        this.height = gamePanel.tileSize * 3;
        this.xPos = (int) (14.5 * gamePanel.tileSize);
        this.yPos = 4 * width;
        this.xPos2 = xPos + width;
        this.yPos2 = yPos + height;
        this.yDelta = yPos + (height / 2);
        this.area = new Rectangle(xPos, yPos, width, height);
        this.speed = 4;
    }

    public void update() {
        this.yDelta = yPos + (height / 2);
        if (gamePanel.ball.direction.equals("right")) {
            if (gamePanel.ball.centerY < yDelta) {
                this.yPos -= (speed);
            } else {
                this.yPos += (speed);
            }
        } else {
            int mHeight = gamePanel.middleHeight;
            if (yDelta != mHeight) {
                if (yDelta < mHeight) {
                    this.yPos += speed;
                } else {
                    this.yPos -= speed;
                }
            }
        }
        this.yPos = Math.max(0, Math.min(gamePanel.screenHeight - height - 1, yPos));
        this.yPos2 = yPos + height;
        this.area = new Rectangle(xPos, yPos, width, height);
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLUE);
        graphics2D.draw(area);
    }
}
