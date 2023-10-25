package javaCLIMinesweeper;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		// Greet User and Set up Scanner
		
		System.out.println("Welcome to the Java CLI Minesweeper App");
		System.out.println();
		
		Scanner scanner = new Scanner(System.in);
		
		// Get Board Dimensions from User Input
		
		// TODO: Take in input from a configuration.json file using the the JSON simple library instead
		
		final int boardWidth = InputProcessingMethods.getBoardDimension("width", scanner);
		final int boardHeight = InputProcessingMethods.getBoardDimension("height", scanner);
		final int desiredBombCount = InputProcessingMethods.getBombCount(boardWidth, boardHeight, scanner);
		
		double bombProbability = (float) desiredBombCount / (boardWidth * boardHeight);
		
		// Set Up Board
		
		ArrayList<ArrayList<Boolean>> bombMap = SetUpMethods.createBombMap(boardWidth, boardHeight, bombProbability);	
		ArrayList<ArrayList<Square>> board = SetUpMethods.createBoardFromBombMap(bombMap, boardWidth, boardHeight);
		
		// Start Game
		
		PrintTextMethods.printAppIntro();
		
		boolean gameRunning = true;
		
		// Game Loop
		
		while (gameRunning) {
		
			// Print Board
			
			BoardMethods.printBoard(board, boardWidth, boardHeight);
			
			// Get User Input and Convert to Integers
			
			String[] coordinates = InputProcessingMethods.getSelectedCoords(scanner);
			
			if (coordinates.length < 2) {
				System.out.println("Error: You have entered less than two coordinates. Remember to include a space between each coordinate.");
				System.out.println();
				continue;
			}
			
			ArrayList<Integer> coordinateNums = InputProcessingMethods.getCoordinateNums(coordinates);
			
			if (coordinateNums.size() < 2) {
				System.out.println();
				continue;
			}
			
			int xCoord = coordinateNums.get(0);
			int yCoord = coordinateNums.get(1);
			
			// Validate and Get Square from User Input
			
			boolean selectionIsInvalid = SharedMethods.isValidSquare(xCoord, yCoord, boardWidth, boardHeight);
			
	    	if (selectionIsInvalid) {
			  System.out.println("Error: The coordinates you've selected are outside the board");
			  System.out.println();
			  continue;
			}

			Square selectedSquare = board.get(yCoord).get(xCoord);
			
			// Reveal Squares and Check for Bombs

			selectedSquare.revealSquare();
			
			if (selectedSquare.hasBomb) {
				PrintTextMethods.printGameOver();
				BoardMethods.revealAllBombs(board, boardWidth, boardHeight);
				BoardMethods.printBoard(board, boardWidth, boardHeight);
				
				scanner.close();
				gameRunning = false;
			} else {
				System.out.println("No bomb there");	
				System.out.println();
				
				// Square reveal cascade
				
				if (Integer.parseInt(selectedSquare.content.trim()) == 0) {
					SharedMethods.revealAdjacentSquares(selectedSquare, board, boardWidth, boardHeight);
				}
				
				int safeHiddenSquaresCount = BoardMethods.getHiddenSafeSquaresCount(board, boardHeight);
				
				if (safeHiddenSquaresCount < 1) {
					System.out.println("All safe squares revealed. You win!");
					System.out.println();
					BoardMethods.revealAllBombs(board, boardWidth, boardHeight);
					BoardMethods.printBoard(board, boardWidth, boardHeight);
					gameRunning = false;
				}
			}
		}		
	}
}