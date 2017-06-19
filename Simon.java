import java.util.*;

public class Simon {
	// Declare Variables
	private int score = 0;
	private static LinkedList<Integer> playerActions = new LinkedList<Integer>();
	static Boolean isDebug = true;
	
	
	// Declare the main method.
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	} // End of Main method.
	
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
			System.out.println(returnList);
		}// #################################
		return returnList;
	} // End of generateActionArray method.
	
	public void setScore(int score) {
		// ########## DEBUG BLOCK ##########
		if (isDebug){
			System.out.println("Setting score to " + score);
		}// #################################
		this.score = score;
	}
	
	// Declare the setNewScore() method:
	// This method will set the score of the game by adding the
	// new score to the existing score.  Each player action
	// will call this method.
	public void setNewScore(int points){
		// ########## DEBUG BLOCK ##########
		if (isDebug){
			System.out.println("Setting score to " + (this.score + points));
		}// ################################
		
		this.score = this.score + points;
	} // End of setNewScore method.
	
	public static void generateAction() {
		// Generate and add an action to the LinkedList object
		Random number = new Random();
		int newNumber = number.nextInt(4);
		// ############## DEBUG BLOCK ##########
		if (isDebug){
			System.out.println("The random action is " + newNumber);
		} // ###################################
		playerActions.add(newNumber);
	} // End of generateAction method.
	
	public void resetGame() {
		// reset the variables of the game and reset to the beginning
		this.setScore(0);
		Simon.playerActions.clear();
		
	} // End of resetGame method.
	
	public static int endGame(int actions) {
		// Get a count of how many actions the player completed
		// then return the score to be recorded by the interface
		// then reset the game.
	} // End of endGame method.

} // End of Simon class.
