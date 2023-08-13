package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Panel;

public class Object {

    public BufferedImage image;
    public String ID;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2d, Panel panel) {

        int screenX = worldX - panel.player.worldX + panel.player.screenX;
        int screenY = worldY - panel.player.worldY + panel.player.screenY;

        if(worldX + panel.tileSize > panel.player.worldX - panel.player.screenX &&
                worldX - panel.tileSize < panel.player.worldX + panel.player.screenX &&
                worldY + panel.tileSize > panel.player.worldY - panel.player.screenY &&
                worldY - panel.tileSize< panel.player.worldY + panel.player.screenY)
        {
            g2d.drawImage(image, screenX, screenY, panel.tileSize, panel.tileSize, null);
        }
    }
}
