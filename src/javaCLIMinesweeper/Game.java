package javaCLIMinesweeper;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	int boardWidth;
	int boardHeight;
	int bombCount;
	Board board;
	boolean gameRunning;
	Scanner scanner = new Scanner(System.in);
	
	public void setUpGame() {
		System.out.println("Welcome to the Java CLI Minesweeper App");
		System.out.println();
		
		setGameSettings();
		
		board = new Board(this.boardWidth, this.boardHeight, this.bombCount);
		
		PrintTextMethods.printAppIntro();
		
		this.gameRunning = true;
		
		this.board.printBoard();
	}
	
	public void runGame() {
		while (this.gameRunning) {	
			// Get User Input and Convert to Integers
			
			String[] coordinates = InputProcessingMethods.getSelectedCoords(this.scanner);
			
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
			
			boolean selectionIsInvalid = SharedMethods.isValidSquare(xCoord, yCoord, this.boardWidth, this.boardHeight);
			
	    	if (selectionIsInvalid) {
			  System.out.println("Error: The coordinates you've selected are outside the board");
			  System.out.println();
			  continue;
			}

			Square selectedSquare = this.board.squares.get(yCoord).get(xCoord);
			
			// Reveal Squares and Check for Bombs

			selectedSquare.revealSquare();
			
			if (selectedSquare.hasBomb) {
				PrintTextMethods.printGameOver();
				this.board.revealAllBombs();
				this.board.printBoard();
				
				this.scanner.close();
				this.gameRunning = false;
			} else {
				System.out.println("No bomb there");
				System.out.println();
				
				// Square reveal cascade
				
				if (Integer.parseInt(selectedSquare.content.trim()) == 0) {
					this.board.revealAdjacentSquares(selectedSquare);
				}
				
				int safeHiddenSquaresCount = this.board.getHiddenSafeSquaresCount();
				
				if (safeHiddenSquaresCount < 1) {
					System.out.println("All safe squares revealed. You win!");
					System.out.println();
					this.board.revealAllBombs();
					this.board.printBoard();
					this.gameRunning = false;
					continue;
				}
				
				this.board.printBoard();
			}
		}		
	}
	
	private void setGameSettings() {
		this.boardWidth = InputProcessingMethods.getBoardDimension("width", this.scanner);
		this.boardHeight = InputProcessingMethods.getBoardDimension("height", this.scanner);
		this.bombCount = InputProcessingMethods.getBombCount(boardWidth, boardHeight, this.scanner);
	}
}
