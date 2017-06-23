
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.UIManager;

/**
 * Description: This class a blueprint to set the look and feel of game play
 * buttons
 *
 * @author Jonathan George
 *
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SquareButton extends JButton {

    Color squareColor;
    int value;

    public SquareButton(Color pColor, int pValue) {

        try {
            UIManager.setLookAndFeel(UIManager
                    .getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        setColor(pColor);
        setBackground(getColor());
        setBorderPainted(false);
        setOpaque(true);
        setContentAreaFilled(true);
        setValue(pValue);
    }

    public void setColor(Color pColor) {
        squareColor = pColor;
    }

    public void setValue(int pValue) {
        value = pValue;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return squareColor;
    }
}
