
/**
 * Description: This class defines the help window that provides player with instructions
 *
 * @author Joshua Hughs
 *
 * @version 1.0
 */
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimonHelp extends JFrame {

    public SimonHelp() {
        helpComponents();
    }

    public void helpComponents() {

        ImageIcon close = new ImageIcon(this.getClass().getResource(
                "/Buttons/Close.png"));
        Image closeImageTemp = close.getImage();
        Image closeImage = closeImageTemp.getScaledInstance(32, 32,
                Image.SCALE_SMOOTH);
        close = new ImageIcon(closeImage);

        JButton closeButton = new JButton(close);
        closeButton.setPreferredSize(new Dimension(125, 75));
        closeButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JTextArea helpInfo = new JTextArea();
        helpInfo.setForeground(Color.WHITE);
        helpInfo.setFont(new Font("Lato", Font.PLAIN, 15));
        helpInfo.setBackground(new Color(39, 35, 35));
        helpInfo.setSize(500, 500);
        helpInfo.setWrapStyleWord(true);
        helpInfo.setLineWrap(true);
        helpInfo.setText(
                "Objective:\nSuccessfully repeat the pattern displayed "
                + "for as many rounds as you can.\n\nHow to play:\n- "
                + "Begin the game by clicking the play button.\n- After the "
                + "pattern is displayed, repeat the pattern by clicking the"
                + " squares in \n   the order they were displayed.\n\nButtons:\n"
                + "Play Game         - Click the play icon\n"
                + "Stop Game        - Click the stop icon\n"
                + "Turn Sound On  - Click the speaker icon On\n"
                + "Turn Sound Off - Click the speaker icon Off\n"
                + "How To Play      - Click the question mark");

        JPanel helpPanel = new JPanel();
        helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.Y_AXIS));
        helpPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        helpPanel.setBackground(new Color(39, 35, 35));
        helpPanel.add(helpInfo);
        helpPanel.add(closeButton);

        setBackground(new Color(39, 35, 35));
        setSize(500, 425);
        setResizable(false);
        add(helpPanel);
        setLocation(new Point(SimonController.simonWindow.mainFrame.getX()
                + SimonController.simonWindow.mainFrame.getWidth(),
                SimonController.simonWindow.mainFrame.getY()));
        setVisible(true);
    }
}
