import java.util.*;

public class PlayActions implements Runnable {
	private LinkedList<Integer> actions = new LinkedList<Integer>();
	private Simon appWindow;
	
	public PlayActions(LinkedList<Integer> list, Simon window) {
		actions = list;
		appWindow = window;
	}
	
	
	public void run() {
		Iterator<Integer> iterator = actions.iterator();
		// Create a delay for the user to get ready to learn the pattern.
		appWindow.setMessage("My turn! Round " + actions.size());
		try {
			Thread.sleep(4000);
		}
		catch (InterruptedException e2) {
			System.out.println("Don't interrupt me");
		}
		while (iterator.hasNext()) {
			int tempNumber = iterator.next();
			try {
				if (tempNumber == 1) {
					appWindow.activate(Simon.topLeft);
					Thread.sleep(1000);
				}
				if (tempNumber == 2) {
					appWindow.activate(Simon.topRight);
					Thread.sleep(1000);
				}
				if (tempNumber == 3) {
					appWindow.activate(Simon.bottomLeft);
					Thread.sleep(1000);
				}
				if (tempNumber == 4) {
					appWindow.activate(Simon.bottomRight);
					Thread.sleep(1000);
				}
			}
			catch (InterruptedException ex){
				System.out.println("You bothered me");
			}
		}
		appWindow.setMessage("Your Turn...");
	}

	
}
