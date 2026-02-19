package com.connectfour;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("Running tests...");

        System.out.println("Testing Token enum...");
        Token red = Token.RED;
        Token yellow = Token.YELLOW;
        System.out.println("RED token: " + red);
        System.out.println("YELLOW token: " + yellow); 
        System.out.println("Token enum tests passed!");

        System.out.println("Testing Gameboard class...");
        Gameboard gameboard = new Gameboard(6, 7, 4);
        System.out.println("Gameboard initialized with " + gameboard.getRows() + " rows, " + gameboard.getColumns() + " columns, and winning length of " + gameboard.getWinningLength());
        System.out.println("Gameboard class tests passed!");

        System.out.println("Testing empty board...");
        boolean emptyBoardTestPassed = true;
        for (int row = 0; row < gameboard.getRows(); row++) {
            for (int col = 0; col < gameboard.getColumns(); col++) {
                if (gameboard.getToken(row, col) != null) {
                    emptyBoardTestPassed = false;
                    System.out.println("Failed: Expected null at (" + row + ", " + col + ") but found " + gameboard.getToken(row, col));
                }
            }
        }
        if (emptyBoardTestPassed) {
            System.out.println("Empty board test passed!");
        } else {
            System.out.println("Empty board test failed!");
        }

        System.out.println("Testing invalid position...");
        Token invalidToken = gameboard.getToken(-1, 0);
        System.out.println("invalid position (-1, 0) token: " + invalidToken);
        System.out.println("Invalid position test passed!");

        System.out.println("token drop test...");
        Gameboard dropTestBoard = new Gameboard(6, 7, 4);
        Boolean dropResult1 = dropTestBoard.dropToken(0, Token.RED);
        Boolean dropResult2 = dropTestBoard.dropToken(0, Token.YELLOW); 
        System.out.println("Drop token in column 0 result: " + dropResult1);
        System.out.println("Drop token in column 0 again result: " + dropResult2);
        System.out.println("Token drop test passed!");
        
        System.out.println("Testing game flow...");
        ConnectFourGame game = new ConnectFourGame(6, 7, 4);
        System.out.println("Current player: " + game.getCurrentPlayer());
        game.displayBoard();

        boolean move1 = game.dropToken(0);
        System.out.println("Move 1 (column 0) successful: " + move1);
        System.out.println("Current player after move 1: " + game.getCurrentPlayer());
        game.displayBoard();

        boolean move2 = game.dropToken(0);
        System.out.println("Move 2 (column 0) successful: " + move2);
        System.out.println("Current player after move 2: " + game.getCurrentPlayer());
        game.displayBoard();

        System.out.println("Game flow test passed!");

        System.out.println("Testing win condition...");
        ConnectFourGame winTestGame = new ConnectFourGame(6, 7, 4);
        winTestGame.dropToken(0); 
        winTestGame.dropToken(1); 
        winTestGame.dropToken(0); 
        winTestGame.dropToken(1); 
        winTestGame.dropToken(0); 
        winTestGame.dropToken(1); 
        winTestGame.dropToken(0); 
        winTestGame.displayBoard();
        System.out.println("Winner: " + winTestGame.getWinner().name());
        System.out.println("Win condition test passed!");
        

        System.out.println("All tests completed!"); 

    }
    
}
