package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        // Create a new JFrame to hold the game
        JFrame window = new JFrame();
        window.setTitle("JM2DEngine");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        // Create a new Panel object to serve as the game's content
        Panel panel = new Panel();
        // Add the Panel to the window
        window.add(panel);
        // Adjust the window size to fit the Panel's preferred size
        window.pack();
        // Center the window on the screen
        window.setLocationRelativeTo(null);

        window.setVisible(true);

        // Call the setupGame method on the Panel to initialize game resources
        panel.setupGame();

        // Call the startGameThread method on the Panel to start the game loop
        panel.startGameThread();

    }

}
