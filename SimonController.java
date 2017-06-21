import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;

public class SimonController implements ActionListener {
	// Declare Variables
	public int pressedButton;
	public int indexNumber;
	private LinkedList<Integer> playerActions;
	static Boolean isDebug = true;
	public Simon simonWindow;
	public Boolean playButtonPushed;
	public int round;
	
	//-------------------------------------------------------------------------
	// Declare the main method:
	// This method simply creates an object of type SimonController
	//-------------------------------------------------------------------------
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		SimonController simonController = new SimonController();
		
	} // End of Main method.***************************************************
	
	//------------------------------------------------------------------------
	// Declare constructor method for SimonController:
	// This method constructs a SimonController object
	// which includes the display of the new simonWindow.
	//------------------------------------------------------------------------
	public SimonController(){
		// Display the application window.
		try {
			simonWindow = new Simon(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Init variables
		pressedButton = 0;
		indexNumber = 0;
		playerActions = new LinkedList<Integer>();
		playButtonPushed = false;
		round = 0;
		
		// Create the Swing Timer object to fire the event monitor
		Timer timer = new Timer(20, this);
	
		// Start the Swing timer.
		timer.start();
		
	}
		
	
	//-------------------------------------------------------------------------
	// Override the actionPerformed() method:
	// This method will respond to triggers from the swing timer, to handle
	// game events and logic.  
	//-------------------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e){
		if (playButtonPushed){
			if (round > 0){
				if (pressedButton != 0) {
					if (isDebug){
						System.out.println("pressedButton is " + pressedButton);
						System.out.println("Array size is " + playerActions.size());
						System.out.println("Index is " + indexNumber);
					}
					if (indexNumber == playerActions.size()-1){
						if (isDebug){
							System.out.println("End of array");
							System.out.println("Checking " + pressedButton + " " + playerActions.get(indexNumber));
						}	
						if (pressedButton == playerActions.get(indexNumber)){
							if (isDebug){
								System.out.println("Round is won!");
								round++;
							}
							generateAction();
							indexNumber = 0;
							pressedButton = 0;
							Thread player = new Thread(new PlayActions(playerActions, simonWindow));
							player.start();
							//simonWindow.setMessage("Your turn.  Good luck!");
						} else {
							System.out.println("Nope");
							simonWindow.setMessage("That's not it!! Loading new game...");
							
							resetGame();

						}
					} else if (indexNumber < playerActions.size()-1){
						if (pressedButton == playerActions.get(indexNumber)){
							indexNumber++;
							pressedButton = 0;
						} else {
							System.out.println("Nope");
							simonWindow.setMessage("That's not it!! Loading new game...");
							
							resetGame();
						}
					}
				}	
			} else if (round == 0){
				generateAction();
				simonWindow.setMessage("My Turn!  Remember...");
				Thread player = new Thread(new PlayActions(playerActions, simonWindow));
				player.start();
				
				round++;
			}
			
		
		}
	} // End of actionPerformed method ########################################
	
	//-------------------------------------------------------------------------
	// Declare the generateAction() method:
	// This method will generate a random number between 1-4 to be added
	// to the linked list as a user action.  This method is called at the 
	// successful completion of user inputs, which denotes "next level".
	//-------------------------------------------------------------------------
	public void generateAction() {
		// Generate and add an action to the LinkedList object
		Random number = new Random();
		int newNumber = number.nextInt(40) % 4 + 1;
		if (isDebug){
			System.out.println("Added " + newNumber);
		}
		playerActions.add(newNumber);
	} // End of generateAction method. ########################################
	
	//-------------------------------------------------------------------------
	// Declare the resetGame() method:
	// This method is called by the playGame() jbutton.  This method will
	// set the score to zero, obliterate the linkedlist object, and then
	// recreate it to begin a new game.
	//-------------------------------------------------------------------------
	public void resetGame() {
		this.playerActions.clear();
		indexNumber = 0;
		pressedButton = 0;
		round = 0;
		simonWindow.setMessage("Loading new game...");
		try{
			Thread.sleep(2000);
		}
		catch (InterruptedException e){
			
		}
	} // End of resetGame method.**********************************************
	
	//-------------------------------------------------------------------------
	// Declare the endGame() method:
	// This method ends the game with the assumption that the user
	// has failed to enter the appropriate sequence.  This method
	// calls the high score method, and then game reset method.
	//-------------------------------------------------------------------------
	public void endGame(int actions) {
		simonWindow.setMessage("You made it " + round + " rounds!");
		try{
			Thread.sleep(2000);
		}
		catch (InterruptedException e){
			
		}
		resetGame();
		
	} // End of endGame method.************************************************
	
	//-------------------------------------------------------------------------
	// Declare the buttonClicked method
	// This method is called by the interface when one of the four colored
	// buttons is clicked by the user.
	//-------------------------------------------------------------------------
	public void buttonClicked(int buttonNumber) {
		if (isDebug){
			System.out.println("The user pressed:" + buttonNumber);
		}	
		
		pressedButton = buttonNumber;
		//He is going to check if the button passed, equals the item at the index in the list.
	} // End of buttonClicked method ############################################
	
} // End of Simon class.
