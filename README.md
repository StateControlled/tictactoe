# Tic Tac Toe

This application recreates the classic Tic-Tac-Toe ([Wikipedia link](https://en.wikipedia.org/wiki/Tic-tac-toe)) game implemented in Java using Awt and Swing.

## Implementation

The Tic Tac Toe board consists of a square grid and is designed for two players who will pass control between them to play the game. By default, the board is 3 rows and 3 columns.
Clicking a tile in the grid will place the current player's symbol, an "X" or and "O" on that tile. A player wins if a single row, column, or diagonal on the board has been completely filled with their symbol.

The game will check for win conditions and announce a winner if conditions are met.
The game cannot check for a tie, but no moves can be made if all the tiles have been filled, and a new game can be started at any time, so I decided that checking for ties was not necessary. It is up to the player(s) to determine if a tie has occurred.

## Code

The game logic has been abstracted to allow for any board of size <code>n &times; n</code> to be used. The *GameBoard* constructor accepts a parameter of <code>gridSize</code> that will determine how large the board will be.
I calculated that the minimum number of turns for a player to win must be <code>gridSize &times; 2 - 1</code> and the game will not check for a win condition unless that many turns has passed.
The game only starts checking for win conditions after this minimum number of turns has passed.
I recognize that for such a small project, this optimization is unnecessary. This inclusion is purely for my own amusement.
<br><br>
The game tracks the number of turns that has passed and which player is currently acting  in the *PlayerHandler* class. After the player has moved, the game will automatically switch to the next player.
<br>
The game can be reset or exited at any time.

### Package Classes:
<dl>
    <dt>Main.java</dt>
    <dd>Executes the application.</dd>
    <dt>GameBoard.java</dt>
    <dd>Sets up the Tic-Tac-Toe board and control buttons.</dd>
    <dt>GameLogic.java</dt>
    <dd>Handles game logic, checking for wins and resetting the board for a new game.</dd>
    <dt>PlayerHandler.java</dt>
    <dd>Maintains information about the current player, such as their symbol an "X" or "O" and which player is active in the current turn. Tracks the number of turns that have taken place.</dd>
</dl>

### Exclusions

I did not include any win tracking for how many games a player has won or lost.
<br>
There is no saving of game state when the application is terminated.
<br><br>
These items were excluded becuase I felt that including them was not necessary to accomplish what I set out to do: which was simply to create a functional implementation of Tic-Tac-Toe.
