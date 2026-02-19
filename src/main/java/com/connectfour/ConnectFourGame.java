package com.connectfour;

import java.util.logging.Logger; 

public class ConnectFourGame {
    // logger for tracking game events
    private static final Logger logger = Logger.getLogger(ConnectFourGame.class.getName());
    // instance variables for the game
    private final Gameboard gameboard; 
    private Token currentPlayer; 
    private boolean gameOver; 
    private Token winner; 
    // constructor to initialize the game 
    public ConnectFourGame(int rows, int columns, int winningLength) {
        this.gameboard = new Gameboard(rows, columns, winningLength);
        this.currentPlayer = Token.RED; 
        this.gameOver = false; 
        this.winner = null; 

        logger.info("Connect Four game initialized with " + rows + " rows, " + columns + " columns, and winning length of " + winningLength);
    }   
    // method to get the current player
    public Token getCurrentPlayer() {
        return currentPlayer; 
    }
    // method to get the winner of the game
    public Token getWinner() {
        return winner; 
    }
    // method to check if the game has ended
    public boolean isGameOver() {
        return gameOver; 
    }
    // method to switch the current player 
    private void switchPlayer() {
        currentPlayer = (currentPlayer == Token.RED) ? Token.YELLOW : Token.RED;
        logger.info("Switched player to " + currentPlayer);
    }
    // how a player drops a token into a column
    public boolean dropToken(int column) {
        if (gameOver) {
            logger.warning("Game is already over. No more moves allowed.");
            return false; 
        }
        boolean success = gameboard.dropToken(column, currentPlayer);
        if (success) {
            Token winner = gameboard.checkWinner();
            if (winner != null) {
                gameOver = true;
                this.winner = winner;
                logger.info("Game over! Winner: " + winner);
            } else {
                switchPlayer();
            }
        }
        return success;
    }
    // Method to display the current state of the game board
    public void displayBoard() {
        gameboard.displayBoard();

    }

    
}
