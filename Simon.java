
/**
 * Description:
 *
 * Completion time:
 *
 * @author Jonathan George
 *
 * @version 1.0
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.AudioSystem;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.sound.sampled.Clip;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.Timer;

import sun.applet.Main;

import javax.sound.sampled.UnsupportedAudioFileException;

public class Simon {
	
	SimonController controller;
    static JLabel msgLabel;
    JButton playButton;
    JButton stopButton;
    JButton soundButton;
    JButton noSoundButton;
    JButton helpButton;
    static SquareButton topLeft;
    static SquareButton topRight;
    static SquareButton bottomLeft;
    static SquareButton bottomRight;
    ImageIcon play;
    ImageIcon stop;
    ImageIcon sound;
    ImageIcon noSound;
    ImageIcon help;
    JPanel controlPanel;
    File soundFile1 = new File("/Sounds/a_sharp.wav");
    File soundFile2 = new File("/Sounds/c_sharp.wav");
    File soundFile3 = new File("/Sounds/d_sharp.wav");
    File soundFile4 = new File("/Sounds/g_sharp.wav");
    File awwSoundFile = new File("/Sounds/aww.wav");
    boolean soundOn = true;

    private void initComponents() {

        /**Create header
        topScoreLabel = new JLabel("TOP SCORE:");
        topScoreLabel.setHorizontalAlignment(JLabel.LEFT);
        topScoreLabel.setForeground(Color.WHITE);
        topScoreLabel.setFont(new Font("Lato", Font.BOLD, 24));

        scoreLabel = new JLabel("SCORE: ");
        scoreLabel.setHorizontalAlignment(JLabel.RIGHT);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Lato", Font.BOLD, 24));
        */

        JPanel headerRow = new JPanel();
        //topScoreLabel.setForeground(Color.WHITE);
        headerRow.setBackground(new Color(39, 35, 35));
        headerRow.setLayout(new BoxLayout(headerRow, BoxLayout.X_AXIS));
        headerRow.add(Box.createHorizontalGlue());
        //headerRow.add(topScoreLabel);
        headerRow.add(Box.createRigidArea(new Dimension(140, 50)));
        //headerRow.add(scoreLabel);
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
        play = new ImageIcon(Main.class.getResource("/Buttons/Play.png"));
        Image playImageTemp = play.getImage();
        Image playImage = playImageTemp.
                getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        play = new ImageIcon(playImage);

        stop = new ImageIcon(Main.class.getResource("/Buttons/Pause.png"));
        Image pauseImageTemp = stop.getImage();
        Image pauseImage = pauseImageTemp.
                getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        stop = new ImageIcon(pauseImage);

        sound = new ImageIcon(Main.class.getResource("/Buttons/Sound.png"));
        Image soundImageTemp = sound.getImage();
        Image soundImage = soundImageTemp.
                getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        sound = new ImageIcon(soundImage);

        noSound = new ImageIcon(Main.class.getResource("/Buttons/No_Sound.png"));
        Image noSoundImageTemp = noSound.getImage();
        Image noSoundImage = noSoundImageTemp.
                getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        noSound = new ImageIcon(noSoundImage);

        help = new ImageIcon(Main.class.getResource("/Buttons/Help.png"));
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

        stopButton = new JButton(stop);
        stopButton.setPreferredSize(new Dimension(125, 75));
        stopButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        stopButton.setOpaque(false);
        stopButton.setContentAreaFilled(false);
        stopButton.setBorderPainted(false);
        stopButton.setFocusPainted(false);

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
        controlPanel.add(stopButton);
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
        
        // Create game panel
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(buttonPanel, BorderLayout.CENTER);
        gamePanel.add(headerRow, BorderLayout.NORTH);
        gamePanel.add(bottomRow, BorderLayout.SOUTH);

        // Create frame
        JFrame mainFrame = new JFrame("Simon");
        mainFrame.setSize(550, 550);
        mainFrame.setBackground(new Color(39, 35, 35));
        mainFrame.add(gamePanel);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    /**
     * Plays sounds on a new Thread
     */
    class Sound implements Runnable {

        File inputFile;

        @Override
        public void run() {
            playSound(inputFile);
        }

        public Sound(File pWavFile) {
            inputFile = pWavFile;
        }

        private void playSound(File wavFile) {
            try {
                Clip soundSample = AudioSystem.getClip();
                soundSample.open(AudioSystem.getAudioInputStream(wavFile));
                soundSample.start();
                Thread.sleep(soundSample.getMicrosecondLength() / 1000);

            }
            catch (UnsupportedAudioFileException ex) {
                System.out.println("There has been an error in playSound method "
                        + "- Unsupported File");
            }
            catch (IOException ex) {
                System.out.println("There has been an error in playSound method"
                        + " - IO Exception");
            }
            catch (LineUnavailableException ex) {
                System.out.println("There has been an error in playSound method"
                        + " - Line Unavailable");
            }
            catch (InterruptedException ex) {
                System.out.println("There has been an error in playSound method"
                        + " - Iterrupted Exception");
            }
        }
    }

   /**
    * Adds action listeners to all buttons
    */
    private void addActionListeners() {

        //Control Button ActionListeners
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	controller.playButtonPushed = true;
                setMessage("Here we go!!");
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.resetGame();
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

        //Game Button ActionListeners
        topLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buttonClicked(1);

                activate(topLeft);
            }
        });

        topRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buttonClicked(2);
                //if (soundOn) {
                //    Thread sound = new Thread(new Sound(soundFile2));
                //    sound.start();
                //}
                activate(topRight);
            }
        });

        bottomLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buttonClicked(3);

                activate(bottomLeft);
            }
        });

        bottomRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buttonClicked(4);
     
                activate(bottomRight);
            }
        });
    }

    //------------------------------------------------------------------------
    // Declare the activate method:
    // This method will cause the clicked button to flash.
    // @param pButton SquareButton that will flash when activated
    // @return int value of game button for game controller
    //------------------------------------------------------------------------
    public int activate(SquareButton pButton) {

        Color temp = pButton.getColor();
        pButton.setBackground(Color.WHITE);
        Timer flashTimer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ex) {
                pButton.setBackground(temp);
            }
        });
        flashTimer.setRepeats(false);
        flashTimer.start();
        
        if (soundOn) {
        	switch (pButton.getValue()){
        	case 1: Thread sound1 = new Thread(new Sound(soundFile1));
        		sound1.start();
        		break;
        	case 2: Thread sound2 = new Thread(new Sound(soundFile2));
        		sound2.start();
        		break;
        	case 3: Thread sound3 = new Thread(new Sound(soundFile3));
        		sound3.start();
        		break;
        	case 4: Thread sound4 = new Thread(new Sound(soundFile4));
        		sound4.start();
        		break;
        	}
        }
        
        return pButton.getValue();
    } // End of activate method ###############################################

    public void setMessage(String pMsg) {
        msgLabel.setText(pMsg);
    }

    public String getMessage() {
        return msgLabel.getText();
    }

    public Simon(SimonController cont) {
        initComponents();
        addActionListeners();
        setMessage("Welcome to Simon. Press play to start.");
        this.controller = cont;
    }
}
