package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball extends Entity {
    GamePanel gamePanel;
    final int r;
    protected String direction;
    int centerX, centerY;
    int vx, vy;

    public Ball(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.width = gamePanel.tileSize/2;
        this.height = gamePanel.tileSize/2;
        this.r = width / 2;
        this.speed = 4;
        startingPosition();
        this.area = new Ellipse2D.Double(xPos, yPos, width, height);
    }

    public void startingPosition() {
        this.xPos = 7 * gamePanel.tileSize;
        this.yPos = 4 * gamePanel.tileSize;
        this.vx = speed;
        this.vy = speed;
        this.direction = "left";
        this.centerX = xPos + r;
        this.centerY = yPos + r;
    }

    public void update() {
        xPos -= vx;
        yPos -= vy;
        if (xPos <= 0 || xPos >= gamePanel.screenWidth - width - 1) {
            startingPosition();
        }

        if (yPos <= 0 || yPos >= gamePanel.screenHeight - height - 1) {
            vy *= -1;
        }

        this.centerX = xPos + r;
        this.centerY = yPos + r;

        if (centerX >= gamePanel.player.xPos && centerX <= gamePanel.player.xPos2 + r && centerY >= gamePanel.player.yPos - r && centerY <= gamePanel.player.yPos2 + r) {
            if (xPos == gamePanel.player.xPos2) {
                vx *= -1;
                xPos -= vx*4;
            }
            else {
                vy *= -1;
                yPos -= vy*4;
            }
        }

        if (centerX + r >= gamePanel.opponent.xPos && centerX + r <= gamePanel.opponent.xPos2 && centerY >= gamePanel.opponent.yPos - r && centerY <= gamePanel.opponent.yPos2 + r) {
            if (centerX + r == gamePanel.opponent.xPos) {
                vx *= -1;
                xPos -= vx*4;
            }
            else {
                vy *= -1;
                yPos -= vy*4;
            }
        }
        direction = vx > 0 ? "left" : "right";
        this.area = new Ellipse2D.Double(xPos, yPos, width, height);
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.cyan);
        graphics2D.draw(area);
    }
}
