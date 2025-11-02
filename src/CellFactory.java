import javax.swing.*;

public class CellFactory {

    public static Cell createCell(String test, JButton button) {
        return switch (test) {
            case "H" -> new HitCell(button);
            case "M" -> new MissCell(button);
            default -> new BlankCell(button);
        };
    }
}
