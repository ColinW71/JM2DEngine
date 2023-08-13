package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.Object;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

// Define the Panel class that extends JPanel and implements Runnable
public class Panel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int tiles = 16; // Number of tiles
    final int scale = 3;  // Scaling factor

    // Calculate tile size based on tiles and scale
    public final int tileSize = tiles * scale;

    // Define maximum screen columns and rows
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    // Calculate screen width and height based on tileSize and maxScreenCol / Row
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    public final int maxWorldCol = 50; // Maximum world columns
    public final int maxWorldRow = 50; // Maximum world rows

    // Frames Per Second
    int FPS = 60; // Frames per second
    TileManager tileM = new TileManager(this);
    InputManager input = new InputManager();
    AudioManager audioManager = new AudioManager();
    public Collision collisionCheck = new Collision(this);
    public Assets setAsset = new Assets(this);
    Thread mainThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, input); // Create player object
    public Object obj[] = new Object[10]; // Array to hold object instances

    // Constructor for Panel
    public Panel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(input);
        this.setFocusable(true);
    }

    // Method to set up game resources
    public void setupGame() {
        setAsset.setObject();
        playMusic(0);
    }

    // Method to start the game thread
    public void startGameThread() {
        mainThread = new Thread(this);
        mainThread.start();
    }

    // Main game loop
    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long time;

        while (mainThread != null) {
            time = System.nanoTime();
            delta += (time - lastTime) / drawInterval;
            lastTime = time;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    // Method to update game logic
    public void update() {
        player.update();
    }

    // Method to draw the game components on the panel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tileM.draw(g2d);

        // Draw objects
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2d, this);
            }
        }

        // Draw player
        player.draw(g2d);
        g2d.dispose();
    }

    // Method to play background music
    public void playMusic(int i) {
        audioManager.setFile(i);
        audioManager.play();
        audioManager.loop();
    }

    // Method to stop background music
    public void stopMusic() {
        audioManager.stop();
    }

    // Method to play sounds
    public void playSE(int i) {
        audioManager.setFile(i);
        audioManager.play();
    }
}
