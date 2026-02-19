package com.connectfour;

import java.util.logging.Logger;



public class Gameboard {
    private static final Logger logger = Logger.getLogger(Gameboard.class.getName());

    // this creates a game board with the specified dementions and winning length
    private final int rows;
    private final int columns;
    private final Token[][] grid;
    private final int winningLength;

    public Gameboard(int rows, int columns, int winningLength) {
        this.rows = rows;
        this.columns = columns;
        this.winningLength = winningLength;
        this.grid = new Token[rows][columns]; 
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getWinningLength() {
        return winningLength;
    }

    public Token getToken(int row, int column) {
        if (isValidPosition(row, column)) {
            return grid[row][column];
        }
        return null;
    }
    
    private boolean isValidPosition(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public boolean dropToken(int column, Token token) {
        // Check if the column is valid and not full
        if (column < 0 || column >= columns) {
            logger.warning("Invalid column: " + column);
            return false;
        }
        // Drop the token to the lowest available position in the column
        for (int row = rows - 1; row >= 0; row--) {
            if (grid[row][column] == null) {
                grid[row][column] = token;
                return true; 
            }
        }
        logger.warning("Column " + column + " is full");
        return false; 
    }

    // Game Board Display
    public void displayBoard() {
        System.out.println("\n === Game Board ===");

        // column numbers
        System.out.print("  ");
        for(int col = 0; col < columns; col++) {
            System.out.print(col + " ");
        }
        System.out.println();

        // Top border
        System.out.print("  ");
        for (int col = 0; col < columns; col++) {
            System.out.print("--");
        }
        System.out.println("-");

        // Board rows
        for (int row = 0; row < rows; row++) {
            System.out.print(row + " |"); 
            for (int col = 0; col < columns; col++) {
                Token token = grid[row][col];
                System.out.print((token != null ? token : " ") + "|"); 
                
            }
            System.out.println();
    
        }
        // Bottom border
        System.out.print("  ");
        for (int col = 0; col < columns; col++) {
            System.out.print("--");
        }
        System.out.println("-");


    }
    
    public Token checkWinner() {
        // Check all positions for potential wins
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Token token = grid[row][col];
                if (token != null) {
                    // Check all 4 directions from this position
                    if (checkDirection(row, col, 1, 0, token) ||    // Horizontal 
                        checkDirection(row, col, 0, 1, token) ||    // Vertical 
                        checkDirection(row, col, 1, 1, token) ||    // Diagonal Right
                        checkDirection(row, col, 1, -1, token)) {   // Diagonal Left
                        return token; // Found winner!
                    }
                }
            }
        }
        return null; 
    }
    // Helper method to check a specific direction for a winning sequence
    private boolean checkDirection(int startRow, int startCol, int rowDir, int colDir, Token token) {
        int count = 0; 
        for (int i = 0; i < winningLength; i++) {
            int row = startRow + i * rowDir;
            int col = startCol + i * colDir;
            //
            if (isValidPosition(row, col) && grid[row][col] == token) {
                count++;
            } else {
                break; 
            }
        }
        return count >= winningLength; // Check if we found the required number of tokens in a row
    }
}
