import javax.swing.*;
import java.awt.*;

/**
 * Represents a blank cell on the game board.
 *
 * <p>
 * This cell starts in an unclicked state and displays a default background
 * image when rendered. The clicked state is tracked to prevent multiple
 * increments of hit/miss counters and to avoid re-rendering already clicked cells.
 * </p>
 */
public class BlankCell implements Cell {

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
     * Constructs a new BlankCell associated with the given JButton.
     *
     * @param button the JButton that represents this cell in the UI
     */
    public BlankCell(JButton button){
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
     * Renders this cell by setting its icon to the default image.
     * This method does not modify the clicked state.
     */
    @Override
    public void render(){
        ImageIcon icon = new ImageIcon(getClass().getResource("/ocean.jpg"));
        button.setIcon(icon);
    }


}
