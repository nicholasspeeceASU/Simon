import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;

public class SimonController implements ActionListener {
	// Declare Variables
	private static Boolean isUserTurn = true;
	public static int pressedButton;
	public static int indexNumber;
	private static int score = 0;
	private static LinkedList<Integer> playerActions = new LinkedList<Integer>();
	static Boolean isDebug = true;
	private static Boolean isWrong = false;
	
	public SimonController(){
		// Display the application window.
		try {
			Simon simonWindow = new Simon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Create the Swing Timer object to fire the event monitor
		Timer timer = new Timer(20, this);
		
		timer.start();
	}
	
	
	//-------------------------------------------------------------------------
	// Declare the main method.
	public static void main(String[] args) {
		
		SimonController simon = new SimonController();
		
		
		
		
		// An incorrect answer was provided. Update the high score and start over.
		
		//########## DEBUG BLOCK ##########
		if (isDebug) {
			//testGameEngine();
		} //###############################
	} // End of Main method.***************************************************
	
	//-------------------------------------------------------------------------
	// Override the actionPerformed() method:
	// This method will respond to triggers from a timer, to handle
	// game events.  
	//-------------------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e){
		if (SimonController.pressedButton == SimonController.playerActions[SimonController.indexNumber]){
			SimonController.indexNumber++;
			SimonController.pressedButton = 0;
		}
		
		if (SimonController.indexNumber > SimonController.playerActions.size()){
			SimonController.generateAction();
		}
	}
	
	
	
	
	
	public void setScore(int score) {
		// ########## DEBUG BLOCK ##########
		if (isDebug){
			System.out.println("Setting score to " + score);
		}// #################################
		SimonController.score = score;
	} // End of setScore method. **********************************************
	
	//-------------------------------------------------------------------------
	// Declare the setNewScore() method:
	// This method will set the score of the game by adding the
	// new score to the existing score.  Each player action
	// will call this method.
	//-------------------------------------------------------------------------
	public void setNewScore(int points){
		// ########## DEBUG BLOCK ##########
		if (isDebug){
			System.out.println("Setting score to " + (SimonController.score + points));
		}// ################################
		
		SimonController.score = SimonController.score + points;
	} // End of setNewScore method. *******************************************
	
	//-------------------------------------------------------------------------
	// Declare the generateAction() method:
	// This method will generate a random number between 1-4 to be added
	// to the linked list as a user action.  This method is called at the 
	// successful completion of user input, which denotes "next level".
	//-------------------------------------------------------------------------
	public static void generateAction() {
		// Generate and add an action to the LinkedList object
		Random number = new Random();
		int newNumber = number.nextInt(4) + 1;
		// ############## DEBUG BLOCK ##########
		if (isDebug){
			System.out.println("The random action is " + newNumber);
		} // ###################################
		
		playerActions.add(newNumber);
	} // End of generateAction method. ****************************************
	
	public static void playbackActions() {
		Iterator<Integer> iterator = playerActions.descendingIterator();
		while (iterator.hasNext()) {
			int tempNumber = iterator.next();
			try {
				if (tempNumber == 1) {
					Simon.activate(Simon.topLeft);
					Thread.sleep(1000);
				}
				if (tempNumber == 2) {
					Simon.activate(Simon.bottomLeft);
					Thread.sleep(1000);
				}
				if (tempNumber == 3) {
					Simon.activate(Simon.topRight);
					Thread.sleep(1000);
				}
				if (tempNumber == 4) {
					Simon.activate(Simon.bottomRight);
					Thread.sleep(1000);
				}
			}
			catch (InterruptedException ex){
				System.out.println("You bothered me");
			}
		}
	}
	
	//-------------------------------------------------------------------------
	// Declare the resetGame() method:
	// This method is called by the playGame() jbutton.  This method will
	// set the score to zero, obliterate the linkedlist object, and then
	// recreate it to begin a new game.
	//-------------------------------------------------------------------------
	public void resetGame() {
		// reset the variables of the game and reset to the beginning
		this.setScore(0);
		SimonController.playerActions.clear();
		
	} // End of resetGame method.**********************************************
	
	//-------------------------------------------------------------------------
	// Declare the endGame() method:
	// This method ends the game with the assumption that the user
	// has failed to enter the appropriate sequence.  This method
	// calls the high score method, and then game reset method.
	//-------------------------------------------------------------------------
	public static int endGame(int actions) {
		// Get a count of how many actions the player completed
		// then return the score to be recorded by the interface
		// then reset the game.
		int endScore = 0; // TO BE REPLACED!!!!!!
		return endScore;
	} // End of endGame method.************************************************
	
	//-------------------------------------------------------------------------
	// Declare the buttonClicked method
	// This method is called by the interface when one of the four colored
	// buttons is clicked by the user.
	//-------------------------------------------------------------------------
	public static void buttonClicked(int buttonNumber) {
		SimonController.pressedButton = buttonNumber;
		//He is going to check if the button passed, equals the item at the index in the list.
	} // End of buttonClicked method ############################################
	
	public static void testGameEngine() {
		generateAction();
		generateAction();
		generateAction();
		generateAction();
		generateAction();
		generateAction();
		
	}

} // End of Simon class.
