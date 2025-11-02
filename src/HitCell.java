import javax.swing.*;
import java.awt.*;

/**
 * Represents a hit cell on the game board.
 *
 * <p>
 * This cell starts in an unclicked state and displays a hit icon when rendered.
 * The clicked state is tracked to prevent multiple increments of hit/miss counters
 * and to avoid re-rendering already clicked cells.
 * </p>
 */
public class HitCell implements Cell {

    JButton button;

    /**
     * Tracks whether this cell has already been clicked.
     * <ul>
     *     <li>Prevents hit/miss counters from incrementing multiple times.</li>
     *     <li>Prevents visual changes to a cell that has already been revealed.</li>
     * </ul>
     */
    private boolean clicked = false;

    /**
     * Constructs a new HitCell associated with the given JButton.
     *
     * @param button the JButton that represents this cell in the UI
     */
    public HitCell(JButton button){
        this.button = button;
        CellUtils.applyDefaultFont(button);
    }

    /**
     * Sets the clicked state of this cell.
     *
     * @param clicked true if the cell has been clicked, false otherwise
     */
    @Override
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    /**
     * Returns whether this cell has been clicked.
     *
     * @return true if the cell has been clicked, false otherwise
     */
    @Override
    public boolean isClicked() {
        return clicked;
    }

    /**
     * Renders this cell as a hit, setting the icon and text to indicate a hit.
     * This method does not modify the clicked state.
     */
    @Override
    public void render(){
        ImageIcon icon = new ImageIcon(getClass().getResource("/explosion.jpg"));
        button.setIcon(icon);
        button.setText("X");
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setForeground(Color.RED);
    }
}
