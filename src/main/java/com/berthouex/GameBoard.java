package com.berthouex;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The Game board consists of a square grid and is designed for two players who will pass control between them to play the game.
 */
public class GameBoard {
    private final JFrame frame;
    private final JPanel panel;
    private final JLabel gameNameLabel;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BUTTON_SIZE = 100;
    private static final Color TILE_COLOR = new Color(0xff8d1c);
    private static final Color GRAY = new Color(0x999999);
    private static final Color GOLD = new Color(0xffd700);

    protected final int GRID_SIZE;
    protected final JLabel messageLabel;
    protected final MoveHandler moveHandler;
    protected final List<JButton> buttons;
    protected final GameLogic gameLogic;

    public GameBoard(final int gridSize) {
        this.GRID_SIZE = gridSize;
        this.buttons = new ArrayList<>();
        this.moveHandler = new MoveHandler();
        this.frame = new JFrame();
        this.panel = new JPanel();
        this.gameNameLabel = new JLabel("Tic Tac Toe", JLabel.CENTER);
        this.messageLabel = new JLabel("Player 1 turn", JLabel.CENTER);
        this.gameLogic = new GameLogic(this);

        frameSetup();
    }

    private void frameSetup() {
        frame.setLayout(new GridLayout(1, 1));
        panel.setLayout(new GridBagLayout());
        panel.setSize(new Dimension(WIDTH, HEIGHT));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(2, 2, 2, 2);
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // create grid of buttons
        for (int j = 0; j < GRID_SIZE; j++) {
            for (int i = 0; i < GRID_SIZE; i++) {
                constraints.gridx = i;
                constraints.gridy = j + 1;

                JButton button = createBoardTile();
                panel.add(button, constraints);
                buttons.add(button);
            }
        }

        // game name label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipady = 40;
        constraints.gridwidth = GRID_SIZE;
        gameNameLabel.setOpaque(true);
        gameNameLabel.setBackground(GRAY);
        gameNameLabel.setFont(new Font("", Font.PLAIN, 32));
        panel.add(gameNameLabel, constraints);

        // label
        constraints.gridx = GRID_SIZE;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.ipady = 0;
        messageLabel.setFont(new Font("Courier New", Font.PLAIN, 24));
        panel.add(messageLabel, constraints);

        // new game button
        constraints.gridx = GRID_SIZE;
        constraints.gridy = 2;
        JButton newGameButton = createGameButton("New Game", e -> reset());
        panel.add(newGameButton, constraints);

        // exit button
        constraints.gridx = GRID_SIZE;
        constraints.gridy = 3;
        JButton exitButton = createGameButton("Exit", e -> System.exit(0));
        panel.add(exitButton, constraints);

        // final frame setup
        frame.getContentPane().add(panel);

        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setTitle("Tic Tac Toe");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    /**
     * Builds a button that will be used as a tile for the board with a default action on click.
     *
     * @return a button
     */
    private JButton createBoardTile() {
        JButton tile = buttonDefault("", gameLogic::move);

        tile.setBackground(TILE_COLOR);
        tile.setForeground(Color.WHITE);
        tile.setFont(new Font("", Font.BOLD, 40));
        tile.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));

        return tile;
    }

    /**
     * Creates a button with game control functionality.
     *
     * @param text    button text to display
     * @param onClick action on click
     * @return a button
     */
    private JButton createGameButton(String text, ActionListener onClick) {
        JButton button = buttonDefault(text, onClick);
        button.setBackground(GOLD);
        button.setFont(new Font("", Font.PLAIN, 20));

        return button;
    }

    /**
     * Builds a button.
     *
     * @param text    button text to display
     * @param onClick action on click
     * @return a button
     */
    private JButton buttonDefault(String text, ActionListener onClick) {
        JButton button = new JButton();
        button.setText(text);
        button.setFocusPainted(false); // stop selection frame on button text
        button.addActionListener(onClick);

        return button;
    }

    /**
     * Resets the game.
     */
    private void reset() {
        buttons.forEach(button -> {
            button.setText("");
            button.setEnabled(true);
            messageLabel.setText("Player 1 turn");
            moveHandler.reset();
        });
    }

    /**
     * Show the JFrame.
     */
    public void show() {
        frame.setVisible(true);
    }

}
