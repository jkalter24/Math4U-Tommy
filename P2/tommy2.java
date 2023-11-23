package P2;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tamagotchi {
    private String name;
    private int hunger;
    private int happiness;
    private boolean isAlive;

    private JLabel hungerLabel;
    private JLabel happinessLabel;
    private JPanel spritePanel; // Panel to display the sprite image
    private JLabel spriteLabel; // Label to hold the sprite image

    public Tamagotchi(String name) {
        this.name = name;
        this.hunger = 0;
        this.happiness = 0;
        this.isAlive = true;

        // Create the GUI components
        JFrame frame = new JFrame("Tamagotchi");
        JPanel panel = new JPanel();
        hungerLabel = new JLabel(name + "'s hunger: " + hunger);
        happinessLabel = new JLabel(name + "'s happiness: " + happiness);
        spritePanel = new JPanel(); // Panel to display the sprite image

        // Set the layout manager for the main panel
        panel.setLayout(new BorderLayout());

        // Create image buttons
        JButton feedButton = new JButton(new ImageIcon("path/to/feed_image.png"));
        JButton playButton = new JButton(new ImageIcon("path/to/play_image.png"));
        JButton sleepButton = new JButton(new ImageIcon("path/to/sleep_image.png"));

        // Add action listeners to the image buttons
        feedButton.addActionListener(e -> feed());
        playButton.addActionListener(e -> play());
        sleepButton.addActionListener(e -> sleep());

        // Add the components to the panel
        panel.add(hungerLabel, BorderLayout.NORTH);
        panel.add(happinessLabel, BorderLayout.CENTER);
        panel.add(feedButton, BorderLayout.WEST);
        panel.add(playButton, BorderLayout.CENTER);
        panel.add(sleepButton, BorderLayout.EAST);
        panel.add(spritePanel, BorderLayout.SOUTH); // Add the sprite panel to the bottom

        // Set up the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setSize(800, 600); // Set the window size to 800x600
        frame.setVisible(true);

        displaySprite(); // Display the sprite image
    }

    public void feed() {
        if (isAlive) {
            hunger--;
            happiness++;
            updateLabels();
            checkStatus();
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, " + name + " is no longer alive.");
        }
    }

    public void play() {
        if (isAlive) {
            hunger++;
            happiness++;
            updateLabels();
            checkStatus();
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, " + name + " is no longer alive.");
        }
    }

    public void sleep() {
        if (isAlive) {
            hunger++;
            happiness--;
            updateLabels();
            checkStatus();
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, " + name + " is no longer alive.");
        }
    }

    private void checkStatus() {
        if (hunger > 5 || happiness < -5) {
            isAlive = false;
            JOptionPane.showMessageDialog(null, name + " has passed away. Game over.");
        }
    }

    private void updateLabels() {
        hungerLabel.setText(name + "'s hunger: " + hunger);
        happinessLabel.setText(name + "'s happiness: " + happiness);
    }

    private void displaySprite() {
        try {
            BufferedImage spriteImage = ImageIO.read(new File("path/to/sprite_image.png"));
            spriteLabel = new JLabel(new ImageIcon(spriteImage));
            spritePanel.removeAll(); // Clear the existing components in the sprite panel
            spritePanel.add(spriteLabel); // Add the sprite label to the sprite panel
            spritePanel.revalidate(); // Refresh the layout of the sprite panel
            spritePanel.repaint(); // Repaint the sprite panel
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String name = JOptionPane.showInputDialog("Enter your Tamagotchi's name:");
            new Tamagotchi(name);
        });
    }
}
