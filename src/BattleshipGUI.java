import javax.swing.*;
import java.awt.*;

public class BattleshipGUI {

    private Game Game = new Game();
    private final JFrame frame;
    private final int[][] board = Game.getShips();
    private final JPanel boardPanel = new JPanel();
    private final JPanel scorePanel = new JPanel();
    private final JTextField missField = new JTextField();
    private final JTextField totalHitField = new JTextField();
    private final JTextField totalMissField = new JTextField();
    private final JTextField strikeField = new JTextField();
    Cell cells[][] = new Cell[10][10];


    public BattleshipGUI() {

        frame = new JFrame("Battleship");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(scorePanel(), BorderLayout.NORTH);
        frame.add(gamePanel(), BorderLayout.CENTER);
        frame.add(bottomPanel(), BorderLayout.SOUTH);
        frame.setVisible(true);
        Game.setShips();





    }

      // add this to your class fields

    private JPanel scorePanel() {

        scorePanel.setLayout(new GridLayout(1, 5)); // increase columns to fit the new field
        scorePanel.setBackground(Color.LIGHT_GRAY);

        // Labels

        JLabel missLabel = new JLabel("Misses:");
        JLabel totalHitLabel = new JLabel("Total Hits:");
        JLabel totalMissLabel = new JLabel("Total Misses:");
        JLabel strikeLabel = new JLabel("Strikes:");  // new label

        // Make text fields read-only

        missField.setEditable(false);
        totalHitField.setEditable(false);
        totalMissField.setEditable(false);
        strikeField.setEditable(false); // new field

        // Add components to panel

        scorePanel.add(missLabel);
        scorePanel.add(missField);
        scorePanel.add(totalHitLabel);
        scorePanel.add(totalHitField);
        scorePanel.add(totalMissLabel);
        scorePanel.add(totalMissField);
        scorePanel.add(strikeLabel);  // add strike label
        scorePanel.add(strikeField);  // add strike text field

        return scorePanel;
    }

    private JPanel gamePanel() {

        boardPanel.setLayout(new GridLayout(10, 10));
        boardPanel.setBackground(Color.lightGray);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                JButton button = new JButton();
                cells[i][j] = CellFactory.createCell("",button);
//
                cells[i][j].render();
                boardPanel.add(button);
                final int row = i;
                final int col = j;
                button.addActionListener(e -> {
                 cellClicked(button, row, col, cells[row][col]);
                });






            }
        }

        return boardPanel;


    }

    private void cellClicked(JButton button, int row, int col, Cell cell) {

        if (!cell.isClicked()) {
            Cell newCell;
            boolean isHit = board[row][col] != 0;




            boardPanel.remove(button);

            if (isHit) {
                newCell = CellFactory.createCell("H", button);
            } else {
                newCell = CellFactory.createCell("M", button);
            }

            cell.setClicked(true);
            newCell.render();
            boardPanel.add(button);



            // Update counters and text fields
            if (isHit) {

                Game.decreaseShipHealth(board[row][col]);  // decrease first



                // Now check if ship is sunk
                if (Game.getShipHealth(board[row][col]) == 0) {
                    Game.resetCounter();  // reset miss counter for strike logic
                    missField.setText("");
                    JOptionPane.showMessageDialog(frame, "You sunk a ship!");
                }
                Game.addTotalHit();
                totalHitField.setText(String.valueOf(Game.getTotalHit()));
            } else {
                Game.addTotalMiss();
                Game.addMiss();
                totalMissField.setText(String.valueOf(Game.getTotalMiss()));
                missField.setText(String.valueOf(Game.getMiss()));
            }



            if (Game.getMiss() == 5) {
                Game.resetCounter();

                missField.setText("");
                Game.addStrike();
                strikeField.setText(String.valueOf(Game.getStrike()));
            }
        }
        if (Game.strikedOut()) endGame("Striked out!");
        if (Game.hitAllShips()) endGame("You hit all the ships!");



    }
    private void endGame(String message) {
        int response = JOptionPane.showConfirmDialog(frame, message + " Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            frame.dispose();

            new BattleshipGUI();
        } else {
            System.exit(0);
        }
    }



    private JPanel bottomPanel() {
        JPanel bottomPanel = new JPanel();

        // Play Again Button
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to start a new game?",
                    "Confirm Play Again",
                    JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                frame.dispose();
                new BattleshipGUI();
            }
        });

        // Quit Button
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to quit?",
                    "Confirm Quit",
                    JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        bottomPanel.add(playAgainButton);
        bottomPanel.add(quitButton);

        return bottomPanel;
    }









    public static void main(String[] args) {

        SwingUtilities.invokeLater(BattleshipGUI::new);
    }

}
