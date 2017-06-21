
/**
 * Description:
 *
 * Completion time:
 *
 * @author Jonathan George
 *
 * @version 1.0
 */
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.sound.sampled.Clip;
import java.awt.Color;
import javax.sound.sampled.DataLine.Info;
import java.awt.Dimension;
import java.io.File;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Simon {
	
	
    static JLabel msgLabel;
    JLabel topScoreLabel;
    static JLabel scoreLabel;
    JButton playButton;
    JButton pauseButton;
    JButton soundButton;
    JButton noSoundButton;
    JButton helpButton;
    static JButton topLeft;
    static JButton topRight;
    static JButton bottomLeft;
    static JButton bottomRight;
    ImageIcon play;
    ImageIcon pause;
    ImageIcon sound;
    ImageIcon noSound;
    ImageIcon help;
    JPanel controlPanel;
    AudioInputStream audioStream1;
    AudioInputStream audioStream2;
    AudioInputStream audioStream3;
    AudioInputStream audioStream4;
    AudioInputStream awwAudioStream;
    Clip topLeftClip;
    Clip topRightClip;
    Clip bottomLeftClip;
    Clip BottomRightClip;
    boolean soundOn;

    private void initComponents() {

        //Create header
        topScoreLabel = new JLabel("TOP SCORE:");
        topScoreLabel.setHorizontalAlignment(JLabel.LEFT);
        topScoreLabel.setForeground(Color.WHITE);
        topScoreLabel.setFont(new Font("Lato", Font.BOLD, 24));

        scoreLabel = new JLabel("SCORE: ");
        scoreLabel.setHorizontalAlignment(JLabel.RIGHT);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Lato", Font.BOLD, 24));

        JPanel headerRow = new JPanel();
        topScoreLabel.setForeground(Color.WHITE);
        headerRow.setBackground(new Color(39, 35, 35));
        headerRow.setLayout(new BoxLayout(headerRow, BoxLayout.X_AXIS));
        headerRow.add(Box.createHorizontalGlue());
        headerRow.add(topScoreLabel);
        headerRow.add(Box.createRigidArea(new Dimension(140, 50)));
        headerRow.add(scoreLabel);
        headerRow.add(Box.createHorizontalGlue());

        //Create new game buttons
        topLeft = new SquareButton(new Color(67, 194, 174), 1);
        topRight = new SquareButton(new Color(242, 170, 60), 2);
        bottomLeft = new SquareButton(new Color(18, 79, 123), 3);
        bottomRight = new SquareButton(new Color(243, 86, 50), 4);

        //Add game buttons to grid
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(39, 35, 35));
        buttonPanel.setLayout(new GridLayout(2, 2, 30, 30));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 35, 10, 35));
        buttonPanel.add(topLeft);
        buttonPanel.add(topRight);
        buttonPanel.add(bottomLeft);
        buttonPanel.add(bottomRight);

        //Create ImageIcons and resize for control panel buttons
        play = new ImageIcon(".//src//Buttons//Play.png");
        Image playImageTemp = play.getImage();
        Image playImage = playImageTemp.
                getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        play = new ImageIcon(playImage);

        pause = new ImageIcon(".//src//Buttons//Pause.png");
        Image pauseImageTemp = pause.getImage();
        Image pauseImage = pauseImageTemp.
                getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        pause = new ImageIcon(pauseImage);

        sound = new ImageIcon(".//src//Buttons//Sound.png");
        Image soundImageTemp = sound.getImage();
        Image soundImage = soundImageTemp.
                getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        sound = new ImageIcon(soundImage);

        noSound = new ImageIcon(".//src//Buttons//No_Sound.png");
        Image noSoundImageTemp = noSound.getImage();
        Image noSoundImage = noSoundImageTemp.
                getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        noSound = new ImageIcon(noSoundImage);

        help = new ImageIcon(".//src//Buttons//Help.png");
        Image helpImageTemp = help.getImage();
        Image helpImage = helpImageTemp.
                getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        help = new ImageIcon(helpImage);

        //Create message label and add to panel
        msgLabel = new JLabel();
        msgLabel.setHorizontalAlignment(JLabel.CENTER);
        msgLabel.setForeground(Color.WHITE);
        msgLabel.setFont(new Font("Lato", Font.PLAIN, 18));

        JPanel msgPanel = new JPanel();
        msgPanel.setLayout(new BorderLayout());
        msgPanel.setBackground(new Color(39, 35, 35));
        msgPanel.add(msgLabel);

        //Create buttons and add ImageIcons to buttons
        playButton = new JButton(play);
        playButton.setPreferredSize(new Dimension(125, 75));
        playButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);

        pauseButton = new JButton(pause);
        pauseButton.setPreferredSize(new Dimension(125, 75));
        pauseButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        pauseButton.setOpaque(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setBorderPainted(false);
        pauseButton.setFocusPainted(false);

        soundButton = new JButton(sound);
        soundButton.setPreferredSize(new Dimension(125, 75));
        soundButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        soundButton.setOpaque(false);
        soundButton.setContentAreaFilled(false);
        soundButton.setBorderPainted(false);
        soundButton.setFocusPainted(false);

        noSoundButton = new JButton(noSound);
        noSoundButton.setPreferredSize(new Dimension(125, 75));
        noSoundButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        noSoundButton.setOpaque(false);
        noSoundButton.setContentAreaFilled(false);
        noSoundButton.setBorderPainted(false);
        noSoundButton.setFocusPainted(false);

        helpButton = new JButton(help);
        helpButton.setPreferredSize(new Dimension(125, 75));
        helpButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        helpButton.setOpaque(false);
        helpButton.setContentAreaFilled(false);
        helpButton.setBorderPainted(false);
        helpButton.setFocusPainted(false);

        // Create control panel
        controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        controlPanel.setBackground(new Color(39, 35, 35));
        controlPanel.add(Box.createHorizontalGlue());
        controlPanel.add(playButton);
        controlPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        controlPanel.add(playButton);
        controlPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        controlPanel.add(pauseButton);
        controlPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        controlPanel.add(soundButton);
        controlPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        controlPanel.add(helpButton);
        controlPanel.add(Box.createHorizontalGlue());

        // Create bottom panel
        JPanel bottomRow = new JPanel();
        bottomRow.setLayout(new GridLayout(2, 1, 0, 0));
        bottomRow.add(msgPanel);
        bottomRow.add(controlPanel);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(buttonPanel, BorderLayout.CENTER);
        gamePanel.add(headerRow, BorderLayout.NORTH);
        gamePanel.add(bottomRow, BorderLayout.SOUTH);

        JFrame mainFrame = new JFrame("Simon");
        mainFrame.setSize(550, 550);
        mainFrame.setBackground(new Color(39, 35, 35));
        mainFrame.add(gamePanel);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    private void enableSound() {
        File soundFile1 = new File(".//src//Sounds//a_sharp.wav");
        File soundFile2 = new File(".//src//Sounds//c_sharp.wav");
        File soundFile3 = new File(".//src//Sounds//d_sharp.wav");
        File soundFile4 = new File(".//src//Sounds//g_sharp.wav");
        File awwSoundFile = new File(".//src//Sounds//aww.wav");

        try {
            audioStream1 = AudioSystem.getAudioInputStream(
                    soundFile1);
            audioStream2 = AudioSystem.getAudioInputStream(
                    soundFile2);
            audioStream3 = AudioSystem.getAudioInputStream(
                    soundFile3);
            audioStream4 = AudioSystem.getAudioInputStream(
                    soundFile4);
            awwAudioStream = AudioSystem.getAudioInputStream(
                    awwSoundFile);

            AudioFormat format1 = audioStream1.getFormat();
            AudioFormat format2 = audioStream2.getFormat();
            AudioFormat format3 = audioStream3.getFormat();
            AudioFormat format4 = audioStream4.getFormat();
            AudioFormat awwFormat = awwAudioStream.getFormat();

            DataLine.Info info1 = new DataLine.Info(Clip.class, format1);
            DataLine.Info info2 = new DataLine.Info(Clip.class, format2);
            DataLine.Info info3 = new DataLine.Info(Clip.class, format3);
            DataLine.Info info4 = new DataLine.Info(Clip.class, format4);
            DataLine.Info awwInfo = new DataLine.Info(Clip.class, awwFormat);

            try {
                topLeftClip = (Clip) AudioSystem.getLine(info1);
                topRightClip = (Clip) AudioSystem.getLine(info2);
                bottomLeftClip = (Clip) AudioSystem.getLine(info3);
                BottomRightClip = (Clip) AudioSystem.getLine(info4);
                bottomLeftClip = (Clip) AudioSystem.getLine(awwInfo);
            }
            catch (LineUnavailableException ex) {
                Logger.getLogger(Simon.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        catch (UnsupportedAudioFileException ex) {
            System.out.println("Audio file not supported");
        }
        catch (IOException ex) {
            System.out.println("Audio file not found.");
        }

    }

    private void addActionListeners() {

        //Control Button ActionListeners
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMessage("Play Button Clicked");
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMessage("Pause Button Clicked");
            }
        });

        soundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMessage("Sound Button Clicked");
                soundButton.add(noSoundButton);
                soundOn = false;
            }
        });

        noSoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMessage("No Sound Button Clicked");
                soundButton.remove(noSoundButton);
                soundOn = true;
            }
        });

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMessage("Help Button Clicked");
            }
        });

//////////////////////////////////////////////////////////////////////////
        //Game Button ActionListeners
        topLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimonController.buttonClicked(1);
            }
        });


        topRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimonController.buttonClicked(2);
            }
        });

        bottomLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimonController.buttonClicked(3);
            }
        });

        bottomRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimonController.buttonClicked(4);
            }
        });
    }

    public static void activate(JButton pButton) {
        pButton.doClick();
    }
    
    public static void openingSequence() {
    	activate(topLeft);
    	activate(bottomLeft);
    	activate(topRight);
    	activate(bottomRight);
    	
    }

    public static void setMessage(String pMsg) {
        msgLabel.setText(pMsg);
    }

    public String getMessage() {
        return msgLabel.getText();
    }

    public static void setScore(String pScore) {
        Simon.scoreLabel.setText(pScore);
    }

    public Simon() {
        initComponents();
        addActionListeners();
        setMessage("Welcome to Simon. Press play to start.");
        enableSound();
    }
    
    
}
