import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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

<<<<<<< Updated upstream
=======
    private JPanel spritePanel;
    private JLabel spriteLabel;
    private JPanel buttonPanel;

    private JFrame frame;
    private Timer timer;
    private Clip backgroundMusic;

    // intro the Tamogotchi Class and sets stats to 0 by default
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
        displaySprite(); // Display the sprite image
=======
        displaySprite();

        // Start playing the background music
        playBackgroundMusic();

        // Start the timer for periodic updates
        timer = new Timer(30000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePetStatus();
            }
        });
        timer.start();
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
=======
    private void handleIncorrectAnswer() {
        JOptionPane.showMessageDialog(null, "Oops! That's incorrect. Your Tamagotchi is not happy.");
        // Adjust Tamagotchi stats for incorrect answer
        if (isAlive) {
            happiness--;
            checkStatus();
        }
    }

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
            JOptionPane.showMessageDialog(null, "Sorry, " + name + " is no longer alive.");
=======
            JOptionPane.showMessageDialog(null, "Sorry, " + name + " is no longer alive💀."); // If none of these check out then your pet is dead and will show this message.
>>>>>>> Stashed changes
        }
    }

    private void checkStatus() {
        if (hunger > 5 || happiness < -5) {
            isAlive = false;
            JOptionPane.showMessageDialog(null, name + " has passed away. Game over.");
        }
    }

    private void updateLabels() {
<<<<<<< Updated upstream
        hungerLabel.setText(name + "'s hunger: " + hunger);
        happinessLabel.setText(name + "'s happiness: " + happiness);
=======
        hungerProgressBar.setString(name + "'s hunger: " + hunger);
        happinessProgressBar.setString(name + "'s happiness: " + happiness);
        tirednessProgressBar.setString(name + "'s tiredness: " + tiredness);
        hungerProgressBar.setValue(hunger);
        happinessProgressBar.setValue(happiness);
        tirednessProgressBar.setValue(tiredness);
        
        // Force a screen update
        frame.revalidate();
        frame.repaint();
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
=======
    private void setDarkMode() {
        setComponentDarkMode(frame.getContentPane());
        isDarkMode = true;
        displaySprite();
    }

    private void setDefaultMode() {
        setComponentDefaultMode(frame.getContentPane());
        isDarkMode = false;
        displaySprite();
    }

    private void setComponentDarkMode(Component component) {
        if (component instanceof Container) {
            Container container = (Container) component;
            container.setBackground(Color.BLACK);
            container.setForeground(Color.WHITE);
            for (Component child : container.getComponents()) {
                setComponentDarkMode(child);
            }
        }
    }

    private void setComponentDefaultMode(Component component) {
        if (component instanceof Container) {
            Container container = (Container) component;
            container.setBackground(null);
            container.setForeground(null);
            for (Component child : container.getComponents()) {
                setComponentDefaultMode(child);
            }
        }
    }

    private void updatePetStatus() {
        if (isAlive) {
            if (hunger < 4) {
                hunger++;
            }
            if (tiredness < 4) {
                tiredness++;
            }
            if (happiness > -4) {
                happiness--;
            }
            updateLabels();
            checkStatus();
        }
    }

    private void playBackgroundMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/Last-Breath.wav"));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }


>>>>>>> Stashed changes
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String name = JOptionPane.showInputDialog("Enter your Tamagotchi's name:");
            new Tamagotchi(name);
        });
    }
}
