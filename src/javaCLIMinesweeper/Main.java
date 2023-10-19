package javaCLIMinesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	
	public static void main(String[] args) {
		
		// Set Up Variables and Scanner
		
		final int boardWidth = 10;
		final int boardHeight = 10;
		boolean gameRunning = true;
		
		Scanner scanner = new Scanner(System.in);
		
		// Set Up Board
		
		ArrayList<ArrayList<Boolean>> bombMap = SetUpMethods.createBombMap(boardWidth, boardHeight);	
		ArrayList<ArrayList<Square>> board = SetUpMethods.createBoardFromBombMap(bombMap, boardWidth, boardHeight);
		
		int safeHiddenSquaresCount = SetUpMethods.getHiddenSafeSquaresCount(board, boardHeight);
		
		// Greet User
		
		PrintTextMethods.printAppIntro();
		
		// Game Loop
		
		while (gameRunning) {
		
			// Print Board
			
			SetUpMethods.printBoard(board, boardHeight);
			
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
			
			// Reveal Squares

			selectedSquare.revealSquare();
			
			if (Integer.parseInt(selectedSquare.content.trim()) == 0) {
				ArrayList<int[]> adjacentPositions = selectedSquare.getAdjacentPositions();
				for (int[] position : adjacentPositions) {
					int xPos = position[0];
					int yPos = position[1];
					
					boolean positionIsInvalid = SharedMethods.isValidSquare(xPos, yPos, boardWidth, boardHeight);
					
//					if (positionIsInvalid) {
//					  return false;
//					};
					
					if (!positionIsInvalid) {
						Square adjacentSquare = board.get(position[1]).get(position[0]);
						adjacentSquare.revealSquare();
					}
				}
			}
			
			// Check for bombs
			
			if (selectedSquare.hasBomb) {
				PrintTextMethods.printGameOver();
				SetUpMethods.printBoard(board, boardHeight);
				
				scanner.close();
				gameRunning = false;
			} else {
				System.out.println("No bomb there");	
				System.out.println();
				safeHiddenSquaresCount--;
				
				if (safeHiddenSquaresCount < 1) {
					System.out.println("All safe squares revealed. You win!");
					System.out.println();
					gameRunning = false;
				}
			}
		}		
	}
}