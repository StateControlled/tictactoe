# Tic Tac Toe

This is an implementation of the classic Tic-Tac-Toe ([Wikipedia link](https://en.wikipedia.org/wiki/Tic-tac-toe)) game implemented in Java using Awt and Swing.

## Implementation

The Tic Tac Toe board consists of a square grid and is designed for two players who will pass control between them to play the game. By default, the board is 3 rows and 3 columns.
Clicking a tile in the grid will place the current player's symbol, an "X" or and "O" on that tile. A player wins if a single row, column, or diagonal on the board has been completely filled with their symbol.

The game will check for win conditions and announce a winner if conditions are met.
The game cannot check for a tie, but no moves can be made if all the tiles have been filled, and a new game can be started at any time, so I decided that checking for ties was not necessary.

## Code

The game logic has been abstracted to allow for any board of size <code>n &times; n<code> to be used.
I calculated that the minimum number of turns for a player to win must be <code>gridSize &times; 2 - 1<code>. The game only starts checking for win conditions after this minimum number of turns has passed.

### Package Classes:
<ul>
    <li>GameBoard.java</li>
    <li>GameLogic.java</li>
    <li>PlayerHandler.java</li>
</ul>
