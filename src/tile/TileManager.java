package tile;

import main.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        loadMap("/maps/Field.txt");
        getTileImage();
    }

    public void getTileImage() {
        tile[0] = new Tile(gp.tileSize, gp.tileSize, null);

        tile[1] = new Tile(gp.tileSize, gp.tileSize, Color.white);
    }

    public void loadMap(String filePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0, row = 0;

            while (row < gp.maxScreenRow) {
                String line = br.readLine();
                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                col = 0;
                row++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < gp.maxScreenCol; i++) {
            for (int j = 0; j < gp.maxScreenRow; j++) {
                int tileNum = mapTileNum[i][j];

                if (tileNum != 0) {
                    int worldX = i * gp.tileSize;
                    int worldY = j * gp.tileSize;
                    g2.setColor(tile[tileNum].color);
                    g2.drawRect(worldX, worldY, tile[tileNum].width, tile[tileNum].height);
                }
            }
        }
    }
}
