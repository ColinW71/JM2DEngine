package entity;

import main.Panel;
import main.InputManager;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity{

    Panel panel;
    InputManager input;
    public final int screenX;
    public final int screenY;

    public Player(Panel panel, InputManager input) {

        this.panel = panel;
        this.input = input;

        screenX = panel.screenWidth / 2 - (panel.tileSize / 2);
        screenY = panel.screenHeight / 2 - (panel.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = panel.tileSize * 23;
        worldY = panel.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("player/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("player/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("player/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("player/right2.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(input.upPressed == true || input.downPressed == true ||
                input.leftPressed == true || input.rightPressed == true) {
            if(input.upPressed == true) {
                direction = "up";
            }
            else if(input.downPressed == true) {
                direction = "down";
            }
            else if(input.leftPressed == true) {
                direction = "left";
            }
            else if(input.rightPressed == true) {
                direction = "right";
            }

            // TILE COLLISION
            collisionOn = false;
            panel.collisionCheck.checkTile(this);

            // OBJECT COLLISION
            int objIndex = panel.collisionCheck.checkObject(this, true);
            pickUpObject(objIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false) {
                switch(direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }


    }

    public void pickUpObject(int i) {

        if(i != 999) {

            String objectName = panel.obj[i].ID;

            switch(objectName) {
                case "Boots":
                    panel.playSE(2);
                    speed += 1;
                    panel.obj[i] = null;
                    break;
            }
        }
    }
    public void draw(Graphics2D g2d) {

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2d.drawImage(image, screenX, screenY, panel.tileSize, panel.tileSize, null);
    }
}
