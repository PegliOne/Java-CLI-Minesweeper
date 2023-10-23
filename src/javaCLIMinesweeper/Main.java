package javaCLIMinesweeper;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to the Java CLI Minesweeper App");
		System.out.println();
		
		// Set Up Variables and Scanner
		
		int boardWidth;
		int boardHeight;
		int bombCount;
		
		boolean gameRunning = true;
		
		Scanner scanner = new Scanner(System.in);
		
		// TODO: Take in input from a configuration.json file instead
		
		// Refactor this to use functions
		// Loop through each until value is set
		
		System.out.print("Enter board width: ");
		
		try {
			boardWidth = Integer.parseInt(scanner.nextLine().replaceAll("[^0-9]", ""));
		} catch (NumberFormatException nfe) {
			System.out.println("Error: The width must be a number");
			scanner.close();
			return;
		}
		
		System.out.println();
		
		System.out.print("Enter board height: ");
		
		try {
			boardHeight = Integer.parseInt(scanner.nextLine().replaceAll("[^0-9]", ""));
		} catch (NumberFormatException nfe) {
			System.out.println("Error: The height must be a number");
			scanner.close();
			return;
		}
		
		System.out.println();
		
		System.out.print("Enter (approximate) number of bombs: ");
		
		try {
			bombCount = Integer.parseInt(scanner.nextLine().replaceAll("[^0-9]", ""));
		} catch (NumberFormatException nfe) {
			System.out.println("Error: The (approximate) number of bombs must be a number.");
			scanner.close();
			return;
		}
		
		System.out.println();
		
		// Turn ints into floats for this calculation
		
		double bombProbability = (float) bombCount / (boardWidth * boardHeight);
		
		// Set Up Board
		
		ArrayList<ArrayList<Boolean>> bombMap = SetUpMethods.createBombMap(boardWidth, boardHeight, bombProbability);	
		ArrayList<ArrayList<Square>> board = SetUpMethods.createBoardFromBombMap(bombMap, boardWidth, boardHeight);
		
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
			
			// Reveal Squares and Check for Bombs

			selectedSquare.revealSquare();
			
			if (selectedSquare.hasBomb) {
				PrintTextMethods.printGameOver();
				SetUpMethods.printBoard(board, boardHeight);
				
				scanner.close();
				gameRunning = false;
			} else {
				System.out.println("No bomb there");	
				System.out.println();
				
				// Square reveal cascade
				
				if (Integer.parseInt(selectedSquare.content.trim()) == 0) {
					SharedMethods.revealAdjacentSquares(selectedSquare, board, boardWidth, boardHeight);
				}
				
				int safeHiddenSquaresCount = SetUpMethods.getHiddenSafeSquaresCount(board, boardHeight);
				
				if (safeHiddenSquaresCount < 1) {
					System.out.println("All safe squares revealed. You win!");
					System.out.println();
					SetUpMethods.printBoard(board, boardHeight);
					gameRunning = false;
				}
			}
		}		
	}
}