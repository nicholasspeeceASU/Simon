
/**
 * Description: This class defines the random pattern that is generated
 *
 * @author Nicholas Speece
 *
 * @version 1.0
 */
import java.util.LinkedList;
import java.util.Iterator;

public class PlayActions implements Runnable {

    private LinkedList<Integer> actions = new LinkedList<Integer>();
    private Simon appWindow;

    public PlayActions(LinkedList<Integer> list, Simon window) {
        actions = list;
        appWindow = window;
    }

    @Override
    public void run() {
        Iterator<Integer> iterator = actions.iterator();
        appWindow.setMessage("My Turn!");
        try {
            Simon.topLeft.setEnabled(false);
            Simon.topRight.setEnabled(false);
            Simon.bottomLeft.setEnabled(false);
            Simon.bottomRight.setEnabled(false);
            Thread.sleep(2000);
        }
        catch (InterruptedException ex) {
            System.out.println("Don't interrupt me");
        }
        while (iterator.hasNext()) {
            int tempNumber = iterator.next();
            try {
                if (tempNumber == 1) {
                    appWindow.activate(Simon.topLeft);
                    Thread.sleep(650);
                }
                if (tempNumber == 2) {
                    appWindow.activate(Simon.topRight);
                    Thread.sleep(650);
                }
                if (tempNumber == 3) {
                    appWindow.activate(Simon.bottomLeft);
                    Thread.sleep(650);
                }
                if (tempNumber == 4) {
                    appWindow.activate(Simon.bottomRight);
                    Thread.sleep(650);
                }
            }
            catch (InterruptedException ex) {
                System.out.println("You bothered me");
            }
        }
        if (!appWindow.controller.stopButtonPushed) {
            Simon.topLeft.setEnabled(true);
            Simon.topRight.setEnabled(true);
            Simon.bottomLeft.setEnabled(true);
            Simon.bottomRight.setEnabled(true);
            appWindow.setMessage("It's your turn.");
        }
    }
}
