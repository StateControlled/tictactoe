# Tic Tac Toe

By William Berthouex

## Introduction

This is an implementation of the classic Tic-Tac-Toe ([Wikipedia link](https://en.wikipedia.org/wiki/Tic-tac-toe)) game implemented in Java using Awt and Swing.

The Tic Tac Toe board consists of a square grid and is designed for two players who will pass control between them to play the game. By default, the board is 3 rows and 3 columns.
Clicking a tile in the grid will place the current player's symbol, an "X" or and "O" on that tile. A player wins if a single row, column, or diagonal on the board has been completely filled with their symbol.

## Game Implementation

The game will check for win conditions and announce a winner if conditions are met.
The game cannot check for a tie, but no moves can be made if all the tiles have been filled, so I decided that checking for ties was not necessary.