package com.berthouex;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.util.List;

public class GameLogic {
    private final GameBoard board;

    public GameLogic(GameBoard board) {
        this.board = board;
    }

    /**
     * Resets the game.
     */
    public void reset() {
        board.buttons.forEach(button -> {
            button.setText("");
            button.setEnabled(true);
            board.messageLabel.setText("Player 1 turn");
            board.playerHandler.reset();
        });
    }

    /**
     * Handles the actual game logic: changes the game tile to the current player's symbol, an "X" or an "O", and checks
     * for win conditions.
     *
     * @param e an ActionEvent
     */
    public void move(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        button.setText(board.playerHandler.move());
        button.setEnabled(false);

        final int MIN_TURNS_TO_WIN = (board.GRID_SIZE * 2) - 1;

        // check for win condition and, if met, display win result and return to stop game.
        if (board.playerHandler.turnCount() >= MIN_TURNS_TO_WIN - 1) {
            if (checkForWin(board.buttons, board.GRID_SIZE)) {
                board.messageLabel.setText(board.playerHandler.currentPlayer() + " WINS");
                board.buttons.forEach(b -> b.setEnabled(false));
                return;
            }
        }

        board.playerHandler.nextTurn();
        board.messageLabel.setText(board.playerHandler.currentPlayer() + " turn");
    }

    /**
     * Check rows, columns, and diagonals for victory condition.
     *
     * @param buttons   a list of JButton objects that form the board
     * @param gridSize  the size of the board
     *
     * @return  <code>true</code> if a player has won
     */
    public boolean checkForWin(final List<JButton> buttons, final int gridSize) {
        String[][] grid = new String[gridSize][gridSize]; // TODO

        // Fill grid with button text
        for (int i = 0; i < gridSize * gridSize; i++) {
            grid[i / gridSize][i % gridSize] = buttons.get(i).getText(); // convert 1D array to 2D array
        }

        // Check rows and columns
        for (int i = 0; i < gridSize; i++) {
            boolean rowMatch = true;
            boolean colMatch = true;
            String rowStart = grid[i][0];
            String colStart = grid[0][i];

            for (int j = 0; j < gridSize; j++) {
                if (rowStart.isEmpty() || !grid[i][j].equals(rowStart)) {
                    rowMatch = false;
                }
                if (colStart.isEmpty() || !grid[j][i].equals(colStart)) {
                    colMatch = false;
                }
            }

            if (rowMatch || colMatch) {
                return true;
            }
        }

        // Check top-left to bottom-right diagonal
        String leftToRightDiagonal = grid[0][0];
        boolean diag1Match = !leftToRightDiagonal.isEmpty();

        for (int i = 0; i < gridSize; i++) {
            if (!grid[i][i].equals(leftToRightDiagonal)) {
                diag1Match = false;
                break;
            }
        }

        // Check top-right to bottom-left diagonal
        String rightToLeftDiagonal = grid[0][gridSize - 1];
        boolean diag2Match = !rightToLeftDiagonal.isEmpty();

        for (int i = 0; i < gridSize; i++) {
            if (!grid[i][gridSize - 1 - i].equals(rightToLeftDiagonal)) {
                diag2Match = false;
                break;
            }
        }

        return diag1Match || diag2Match;
    }

}
