import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class Tamagotchi {
    private String name;
    private int hunger;
    private int happiness;
    private boolean isAlive;

    private JLabel hungerLabel;
    private JLabel happinessLabel;
    private JButton feedButton;
    private JButton playButton;
    private JButton sleepButton;
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
        feedButton = new JButton("Feed");
        playButton = new JButton("Play");
        sleepButton = new JButton("Sleep");
        spritePanel = new JPanel(); // Panel to display the sprite image

        // Set the layout manager for the main panel
        panel.setLayout(new BorderLayout());

        // Add action listeners to the buttons
        feedButton.addActionListener(e -> {
            if (askArithmeticQuestion()) {
                feed();
            }
        });
        playButton.addActionListener(e -> {
            if (askArithmeticQuestion()) {
                play();
            }
        });
        sleepButton.addActionListener(e -> {
            if (askArithmeticQuestion()) {
                sleep();
            }
        });

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

    private boolean askArithmeticQuestion() {
        Random random = new Random();
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        int result;

        // Generate a random addition or subtraction problem
        if (random.nextBoolean()) {
            result = num1 + num2;
            return askQuestion("What is " + num1 + " + " + num2 + "?", result);
        } else {
            result = num1 - num2;
            return askQuestion("What is " + num1 + " - " + num2 + "?", result);
        }
    }

    private boolean askQuestion(String question, int correctAnswer) {
        String userAnswer = JOptionPane.showInputDialog(question);
        if (userAnswer == null) {
            return false;  // User canceled the input dialog
        }

        try {
            int answer = Integer.parseInt(userAnswer);
            return answer == correctAnswer;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
            return false;
        }
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
            BufferedImage spriteImage = ImageIO.read(new File("img/icon3.png"));
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
