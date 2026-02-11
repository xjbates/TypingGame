import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.util.Random;
public class typingGame implements ActionListener 
{
    private JFrame frame;
    private JPanel panel;
    private JButton playButton;
    private JButton optionsButton;
    private JButton quitButton;
    private Random rand = new Random();
    private JLabel title;

    
    class backgroundPanel extends JPanel{
        private Image backgroundImage;

        public backgroundPanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage(); 
            System.out.println(new File("background.jpg").exists());

        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    public typingGame() 
    {
        playButton = new JButton("Play");
        playButton.addActionListener(this);
        playButton.setPreferredSize(new Dimension(100,30));
        playButton.setAlignmentX((JButton.CENTER_ALIGNMENT));
        playButton.setOpaque(false);
        playButton.setBorderPainted(false);

        optionsButton = new JButton("Options");
        optionsButton.addActionListener(this);
        optionsButton.setPreferredSize(new Dimension(100,30));
        optionsButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        optionsButton.setOpaque(false);
        optionsButton.setBorderPainted(false);

        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
        quitButton.setPreferredSize(new Dimension(100,30));
        quitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        quitButton.setOpaque(false);
        quitButton.setBorderPainted(false);

        title = new JLabel("TYPING GAME");
        title.setForeground(Color.pink);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        panel = new backgroundPanel("background.jpg");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        panel.setBorder(BorderFactory.createEmptyBorder(30,10,10,10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(playButton);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(optionsButton);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(quitButton);

        frame = new JFrame();
        frame.add(panel);
        frame.setTitle("menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(new Dimension(1000,1000));
    }

    private JPanel gamePanel;
    private JLabel wordLabel;
    private JLabel gameScore;
    private JTextField ansField;
    private int score;

    private void startGame() {
        gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        wordLabel = new JLabel(getRandomWord());
        wordLabel.setFont(new Font("Arial", Font.ITALIC, 24));
        wordLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        gameScore = new JLabel("Score : 0");
        gameScore.setFont(new Font("Arial", Font.BOLD, 16));
        gameScore.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        ansField = new JTextField();
        ansField.setMaximumSize(new Dimension(200,30));
        ansField.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        ansField.addActionListener(this);
        
        gamePanel.add(wordLabel);
        gamePanel.add(Box.createRigidArea(new Dimension(0,20)));
        gamePanel.add(gameScore);
        gamePanel.add(Box.createRigidArea(new Dimension(0,10)));
        gamePanel.add(ansField);
        gamePanel.add(Box.createRigidArea(new Dimension(0,10)));

        gamePanel.setBackground(Color.LIGHT_GRAY);

        frame.getContentPane().removeAll();        
        frame.add(gamePanel);
        frame.revalidate();
        frame.repaint();
    }

    String[] words = {
        "java", "swing", "keyboard", "window", "object",
        "method", "class", "random", "button", "panel",
        "frame", "thread", "array", "string", "loop",
        "input", "output", "event", "listener", "action",
        "compile", "execute", "debug", "syntax", "variable",
        "public", "private", "static", "void", "return"
    };
    
    private String getRandomWord () {
        return words[rand.nextInt(words.length)];
    }


    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == playButton) {
        startGame();
    }
    else if (e.getSource() == quitButton) {
        System.exit(0);
    }
    else if (e.getSource() == optionsButton) {
        JOptionPane.showMessageDialog(frame, "there are no options here.");
    }
    else if(e.getSource() == ansField) {
        String typed = ansField.getText().trim();
        if (typed.equals(wordLabel.getText())) {
            score++;
            gameScore.setText("Score: " + score);
            wordLabel.setText(getRandomWord());
            ansField.setText("");
        }
        else {
            JOptionPane.showMessageDialog(frame, "incorrect, your output: " + ansField.getText());
        }
    }

}

    public static void main(String[] args) {
        new typingGame();
    }

}


