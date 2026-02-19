package com.connectfour;

import java.util.logging.Logger;
import java.util.Scanner;

public class ConnectFourApp {
    // logger for tracking game events
    private static final Logger logger = Logger.getLogger(ConnectFourApp.class.getName());
    // main method to start the application
    public static void main(String[] args) {
        logger.info("Starting Connect Four application...");
        Scanner scanner = new Scanner(System.in);

        // configure game settings
        System.out.print("Enter number of rows (press Enter for default 6): ");
        int rows = getIntInput(scanner, 6);

        System.out.print("Enter number of columns (press Enter for default 7): ");
        int columns = getIntInput(scanner, 7);

        System.out.print("Enter winning length (press Enter for default 4): ");
        int winningLength = getIntInput(scanner, 4);

        ConnectFourGame game = new ConnectFourGame(rows, columns, winningLength);

        System.out.println("Game started! Player " + game.getCurrentPlayer() + " goes first.");
        System.out.println("type 'exit' to quit the game.");
        
        // Main game loop
        while (!game.isGameOver()){
            game.displayBoard();
            System.out.println("Player " + game.getCurrentPlayer() + ", enter column to drop your token: ");
            System.out.println("Enter column number (0 to " + (columns - 1) + "): ");
            
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                logger.info("Exiting game...");
                System.out.println("Thanks for playing! Goodbye!");
                break;
            }
            try {
                int column = Integer.parseInt(input);
                boolean moveSuccessful = game.dropToken(column);
                if (!moveSuccessful) {
                    System.out.println("Invalid move. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid column number or 'exit' to quit.");
            }

        }

        if (game.isGameOver()) {
            game.displayBoard();
            if (game.getWinner() != null) {
                System.out.println("Congratulations! Player " + game.getWinner() + " wins!");
            } else {
                System.out.println("Game over! It's a draw!");
            }
            logger.info("Game over. Winner: " + game.getWinner());
        }
        scanner.close();


    }
    // Helper method to get integer input with default value
    private static int getIntInput(Scanner scanner, int defaultValue) {
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("No input provided. Using default value: " + defaultValue);
            return defaultValue;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Using default value: " + defaultValue);
            return defaultValue;
        
        }
        
    }
}

