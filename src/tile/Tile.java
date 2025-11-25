package tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public boolean collision;

    public int width, height;

    public Color color;

    public Tile(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }
}
