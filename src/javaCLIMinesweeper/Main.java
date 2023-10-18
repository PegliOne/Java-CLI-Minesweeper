package javaCLIMinesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		// Set Up Variables and Scanner
		
		final int boardWidth = 10;
		final int boardHeight = 10;
		boolean gameRunning = true;
		
		Scanner scanner = new Scanner(System.in);
		
		// Set Up Board
		
		ArrayList<ArrayList<Boolean>> bombMap = BoardMethods.createBombMap(boardWidth, boardHeight);	
		ArrayList<ArrayList<Square>> board = BoardMethods.createBoardFromBombMap(bombMap);
		
		// Greet User
		
		PrintTextMethods.printAppIntro();
		
		// Game Loop
		
		while (gameRunning) {
		
			// Print Board
			
			BoardMethods.printBoard(board);
			
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
			
			// Reveal Square and Check for Bombs

			selectedSquare.revealSquare();
			
			
			if (selectedSquare.hasBomb) {
				PrintTextMethods.printGameOver();
				BoardMethods.printBoard(board);
				
				scanner.close();
				gameRunning = false;
			} else {
				System.out.println("No bomb there");	
				System.out.println();
			}
		}		
	}
}