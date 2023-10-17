package javaCLIMinesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Set Up Variables and Scanner
		
		int boardWidth = 10;
		int boardHeight = 10;
		boolean gameRunning = true;
		
		Scanner scanner = new Scanner(System.in);
		
		// Create Bomb Map
		
		ArrayList<ArrayList<Boolean>> bombMap = new ArrayList<ArrayList<Boolean>>();
		
		for(int i = 0; i < boardHeight; i++) {
			ArrayList<Boolean> bombMapRow = new ArrayList<Boolean>();
			for(int j = 0; j < boardWidth; j++) {
			  bombMapRow.add(Math.random() < 0.1);
			}
			bombMap.add(bombMapRow);
		}
		
		// Create Board from Bomb Map
		
		ArrayList<ArrayList<Square>> board = new ArrayList<ArrayList<Square>>();
		
		for(int i = 0; i < boardHeight; i++) {
			ArrayList<Square> boardRow = new ArrayList<Square>();
			for(int j = 0; j < boardWidth; j++) {
			  boolean hasBomb = bombMap.get(i).get(j);	
			  boardRow.add(new Square(j, i, bombMap, false, hasBomb));
			}
			board.add(boardRow);
		}
		
		// Print Instructions
		
		Methods.printAppIntro();
		
		// Game Loop
		
		while (gameRunning) {
			
			// Print Board
			
			for(int i = 0; i < boardHeight; i++) {
				ArrayList<Square> boardRow = board.get(i);
				String[] boardRowContent = boardRow.stream().map(square -> square.content).toArray((size) -> new String[boardHeight]);
				System.out.println(Arrays.toString(boardRowContent));
			}
			System.out.println();
			
			// Get User Input
			
			String[] coordinates = Methods.getSelectedCoords(scanner);
			
			// Convert Input to Numbers
			
			if (coordinates.length < 2) {
				System.out.println("Error: You have entered less than two coordinates. Remember to include a space between each coordinate.");
				System.out.println();
				continue;
			}
			
			ArrayList<Integer> coordinateNums = Methods.getCoordinateNums(coordinates);
			
			if (coordinateNums.size() < 2) {
				System.out.println();
				continue;
			}
			
			int xCoord = coordinateNums.get(0);
			int yCoord = coordinateNums.get(1);
			
			// Get Square from User Input
			
			boolean selectionIsInvalid = Methods.isValidSquare(xCoord, yCoord, boardWidth, boardHeight);
			
	    	if (selectionIsInvalid) {
			  System.out.println("Error: The coordinates you've selected are outside the board");
			  System.out.println();
			  continue;
			}

			Square selectedSquare = board.get(xCoord).get(yCoord);
			selectedSquare.isHidden = false;
			
			// Check for Bombs
			
			if (selectedSquare.hasBomb) {
				System.out.println("Boom!");
				scanner.close();
				gameRunning = false;
			} else {
				System.out.println("No bomb there");	
				System.out.println();
			}

		}		
	}
}