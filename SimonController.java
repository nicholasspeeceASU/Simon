
import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;

/**
 * Description: This class controls game functions
 *
 * @author Nicholas Speece
 *
 * @version 1.0
 */

public class SimonController implements ActionListener {

    int round;
    int pressedButton;
    int indexNumber;
    static Simon simonWindow;
    Boolean playButtonPushed;
    Boolean stopButtonPushed;
    LinkedList<Integer> playerActions;
    static Boolean isDebug = false;

    public static void main(String[] args) {

        new SimonController();

    }

    /**
     * Constructs a SimonController object
     */
    public SimonController() {

        pressedButton = 0;
        indexNumber = 0;
        playerActions = new LinkedList<Integer>();
        playButtonPushed = false;
        stopButtonPushed = false;
        round = 0;

        // Displays the Simon JFrame.
        try {
            simonWindow = new Simon(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Creates Swing Timer object to fire the event monitor
        Timer timer = new Timer(20, this);
        timer.start();
    }

    /**
     * Responds to triggers from timer, to handle game events and logic.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (playButtonPushed) {
            if (round > 0) {
                if (pressedButton != 0) {
                    if (isDebug) {
                        System.out.println("pressedButton is " + pressedButton);
                        System.out.println("Array size is " + playerActions.size());
                        System.out.println("Index is " + indexNumber);
                    }
                    if (indexNumber == playerActions.size() - 1) {
                        if (isDebug) {
                            System.out.println("End of array");
                            System.out.println("Checking " + pressedButton + " "
                                    + playerActions.get(indexNumber));
                        }
                        if (pressedButton == playerActions.get(indexNumber)) {
                            if (isDebug) {
                                System.out.println("Round is won!");
                            }
                            round++;
                            simonWindow.updateRound(getRound());
                            generateAction();
                            indexNumber = 0;
                            pressedButton = 0;
                            Thread player = new Thread(new PlayActions(
                                    playerActions, simonWindow));
                            player.start();
                        }
                        else {
                            System.out.println("Nope");

                            try {
                                Thread.sleep(20);
                            }
                            catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            stopGame();
                        }
                    }
                    else if (indexNumber < playerActions.size() - 1) {
                        if (pressedButton == playerActions.get(indexNumber)) {
                            indexNumber++;
                            pressedButton = 0;
                        }
                        else {
                            if (isDebug) {
                                System.out.println("Nope");
                            }
                            try {
                                Thread.sleep(50);
                            }
                            catch (InterruptedException ex) {

                            }
                            stopGame();
                        }
                    }
                }
            }
            else if (round == 0) {
                generateAction();
                Thread player = new Thread(new PlayActions(
                        playerActions, simonWindow));
                player.start();
                round++;
                simonWindow.updateRound(getRound());
            }
        }
    }

    /**
     * Generates a random number between 1-4 to be added to the linked list as a
     * user action.
     */
    public void generateAction() {
        // Generate and add an action to the LinkedList object
        Random number = new Random();
        int newNumber = number.nextInt(40) % 4 + 1;
        if (isDebug) {
            System.out.println("Added " + newNumber);
        }
        playerActions.add(newNumber);
    }

    /**
     * Resets values to zero, clears the LinkedList
     */
    public void stopGame() {

        simonWindow.setMessage(stopButtonPushed ? "Welcome to Simon. "
                + "Press play to start." : "Game Over. Press Play to try again.");

        this.playerActions.clear();
        playButtonPushed = false;
        indexNumber = 0;
        pressedButton = 0;
        simonWindow.playButton.setEnabled(true);
        simonWindow.updateRound(getRound());

        Simon.topLeft.setEnabled(false);
        Simon.topRight.setEnabled(false);
        Simon.bottomLeft.setEnabled(false);
        Simon.bottomRight.setEnabled(false);

        try {
            Thread.sleep(20);
        }
        catch (InterruptedException e) {
        }
    }

    /**
     * Declare the buttonClicked method This method is called by the interface
     * when one of the four colored buttons is clicked by the user.
     */
    public void buttonClicked(int buttonNumber) {
        if (isDebug) {
            System.out.println("The user pressed: " + buttonNumber);
        }
        pressedButton = buttonNumber;
    }

    /**
     *
     * @return round number used in header
     */
    public int getRound() {
        return round;
    }

}
