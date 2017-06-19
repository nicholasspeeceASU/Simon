import java.util.*;

public class Simon {
	// Declare Variables
	private int score = 0;
	private static LinkedList<Integer> playerActions = new LinkedList<Integer>();
	static Boolean isDebug = true;
	
	//-------------------------------------------------------------------------
	// Declare the main method.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//########## DEBUG BLOCK ##########
		if (isDebug) {
			testGameEngine();
		} //###############################
	} // End of Main method.***************************************************
	
	//-------------------------------------------------------------------------
	// Declare the generateActionArray() method:
	// This method will generate an array of integers for
	// consumption by the interface.  
	//-------------------------------------------------------------------------
	public static int[] generateActionArray(){
		// Generate and return the action array of integers for the interface
		int[] returnList = new int[playerActions.size()];
		for (int i = 0; i < playerActions.size(); i++) {
			returnList[i] = playerActions.get(i);
		}
		// ########## DEBUG BLOCK ##########
		if (isDebug){
			for (int i = 0; i < returnList.length; i++){
				System.out.println(returnList[i]);
			}
		}// #################################
		return returnList;
	} // End of generateActionArray method.
	
	public void setScore(int score) {
		// ########## DEBUG BLOCK ##########
		if (isDebug){
			System.out.println("Setting score to " + score);
		}// #################################
		this.score = score;
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
			System.out.println("Setting score to " + (this.score + points));
		}// ################################
		
		this.score = this.score + points;
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
	
	//-------------------------------------------------------------------------
	// Declare the resetGame() method:
	// This method is called by the playGame() jbutton.  This method will
	// set the score to zero, obliterate the linkedlist object, and then
	// recreate it to begin a new game.
	//-------------------------------------------------------------------------
	public void resetGame() {
		// reset the variables of the game and reset to the beginning
		this.setScore(0);
		Simon.playerActions.clear();
		
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
	} // End of endGame method.************************************************
	
	public static void testGameEngine() {
		generateAction();
		generateAction();
		generateAction();
		
		int[] list = generateActionArray();
		System.out.println("received: " + list);
		
		
		
		
	}

} // End of Simon class.
