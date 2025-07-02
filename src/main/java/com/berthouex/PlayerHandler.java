package com.berthouex;

/**
 * Tracks the current turn and what symbol is in use.
 */
public class PlayerHandler {

    /**
     * Data object for holding information about the players.
     */
    private enum Player {
        PLAYER_ONE("X", "Player 1"),
        PLAYER_TWO("O", "Player 2");

        private final String symbol;
        private final String player;

        /**
         * @param symbol    a symbol to associate with the player
         * @param player    the player name
         */
        Player(String symbol, String player) {
            this.symbol = symbol;
            this.player = player;
        }

        /**
         * @return the player name
         */
        public String player() {
            return player;
        }

        /**
         * @return  the symbol associated with this player
         */
        public String symbol() {
            return symbol;
        }

        /**
         * @return  the next player in the sequence
         */
        public Player nextTurn() {
            return switch(this) {
                case PLAYER_ONE -> PLAYER_TWO;
                case PLAYER_TWO -> PLAYER_ONE;
            };
        }

        /**
         * @return  the previous player in the sequence
         */
        public String previous() {
            return switch(this) {
                case PLAYER_ONE -> PLAYER_TWO.player();
                case PLAYER_TWO -> PLAYER_ONE.player();
            };
        }
    }

    private Player player;
    private int turnCounter;

    public PlayerHandler() {
        this.player = Player.PLAYER_ONE;
        this.turnCounter = 0;
    }

    /**
     * Return the current player's symbol and advance to the next turn.
     *
     * @return  the current player's symbol
     */
    public String move() {
        return player.symbol();
    }

    public String currentPlayer() {
        return this.player.player();
    }

    /**
     * Advance to the next turn by changing to the next player and incrementing the turn counter.
     */
    public void nextTurn() {
        this.player = player.nextTurn();
        this.turnCounter++;
    }

    /**
     * The number of turns taken this game.
     *
     * @return  the number of turns
     */
    public int turnCount() {
        return turnCounter;
    }

    /**
     * Resets to Player 1's turn.
     */
    public void reset() {
        this.player = Player.PLAYER_ONE;
        this.turnCounter = 0;
    }

}
