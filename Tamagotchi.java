import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Tamagotchi {
    private String name;
    private int hunger;
    private int happiness;
    private int tiredness;
    private boolean isAlive;
    private boolean isDarkMode;

    private JLabel hungerLabel;
    private JLabel happinessLabel;
    private JLabel tirednessLabel;
    private JPanel spritePanel;
    private JLabel spriteLabel;
    private JPanel buttonPanel;

    private JFrame frame;
    private Timer timer;

    // intro the Tamogotchi Class and sets stats to 0 by default
    public Tamagotchi(String name) {
        this.name = name;
        this.hunger = 0;
        this.happiness = 0;
        this.tiredness = 0;
        this.isAlive = true;
        this.isDarkMode = false;

        frame = new JFrame("Tamagotchi");
        JPanel panel = new JPanel(new BorderLayout());
        hungerLabel = new JLabel(name + "'s hunger: " + hunger);
        happinessLabel = new JLabel(name + "'s happiness: " + happiness);
        tirednessLabel = new JLabel(name + "'s tiredness: " + tiredness);

        ImageIcon feedIcon = new ImageIcon("img/feed.png");
        ImageIcon playIcon = new ImageIcon("img/play.png");
        ImageIcon sleepIcon = new ImageIcon("img/sleep.png");

        JButton feedButton = new JButton(feedIcon);
        JButton playButton = new JButton(playIcon);
        JButton sleepButton = new JButton(sleepIcon);

        feedButton.setBorderPainted(false);
        feedButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        sleepButton.setBorderPainted(false);
        sleepButton.setContentAreaFilled(false);

        spritePanel = new JPanel();
        buttonPanel = new JPanel();

        buttonPanel.add(feedButton);
        buttonPanel.add(playButton);
        buttonPanel.add(sleepButton);
        buttonPanel.add(hungerLabel);
        buttonPanel.add(happinessLabel);
        buttonPanel.add(tirednessLabel); // Add tiredness Label

        feedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                feed();
            }
        });

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                play();
            }
        });

        sleepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sleep();
            }
        });

        JToggleButton darkModeSwitch = new JToggleButton("Dark Mode");
        darkModeSwitch.setPreferredSize(new Dimension(80, 100));

        darkModeSwitch.addActionListener(e -> {
            if (darkModeSwitch.isSelected()) {
                setDarkMode();
            } else {
                setDefaultMode();
            }
        });

        JPanel darkModeButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        darkModeButtonPanel.add(darkModeSwitch);

        panel.add(darkModeButtonPanel, BorderLayout.NORTH);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(spritePanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setSize(800, 600);
        frame.setVisible(true);

        displaySprite();

        // Start the timer for periodic updates
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePetStatus();
            }
        });
        timer.start();
    }

    public void feed() {
        if (isAlive) {
            if (hunger > 0) {
                hunger--;
            } else {
                happiness--;
                if (happiness < -3) {
                    isAlive = false;
                    JOptionPane.showMessageDialog(null, name + " has passed away. Game over💀.");
                }
            }
            if (happiness < 4) {
                happiness++;
            }
            updateLabels();
            checkStatus();
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, " + name + " is no longer alive💀.");
        }
    }

    public void play() {
        if (isAlive) {
            if (hunger < 4) {
                hunger++;
            }
            if (tiredness < 4) {
                tiredness++;
            }
            if (happiness < 4) {
                happiness++;
            }
            updateLabels();
            checkStatus();
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, " + name + " is no longer alive💀.");
        }
    }

    public void sleep() {
        if (isAlive) {
            if (hunger < 4) {
                hunger++;
            }
            if (tiredness > -4) {
                tiredness--;
            }
            if (happiness > -4) {
                happiness--;
            }
            updateLabels();
            checkStatus();
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, " + name + " is no longer alive💀.");
        }
    }

    private void checkStatus() {
        if (hunger > 4 || happiness < -3) {
            isAlive = false;
            JOptionPane.showMessageDialog(null, name + " has passed away. Game over💀.");
        }
    }

    private void updateLabels() {
        hungerLabel.setText(name + "'s hunger: " + hunger);
        happinessLabel.setText(name + "'s happiness: " + happiness);
        tirednessLabel.setText(name + "'s tiredness: " + tiredness);
    }

    private void displaySprite() {
        try {
            BufferedImage spriteImage;
            if (isDarkMode) {
                spriteImage = ImageIO.read(getClass().getResource("img/afton.png"));
            } else {
                spriteImage = ImageIO.read(getClass().getResource("img/icon3.png"));
            }
            spriteLabel = new JLabel(new ImageIcon(spriteImage));
            spritePanel.removeAll();
            spritePanel.add(spriteLabel);
            spritePanel.revalidate();
            spritePanel.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String name = JOptionPane.showInputDialog("Enter your Tamagotchi's name:");
            new Tamagotchi(name);
        });
    }
}
