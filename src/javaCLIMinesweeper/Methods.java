package javaCLIMinesweeper;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Methods {
	
	public static ArrayList<ArrayList<Square>> createAndPrintBoard(int width, int height) {
		ArrayList<ArrayList<Square>> board = new ArrayList<ArrayList<Square>>();
		
		// Add bombs to empty board
		
		for(int i = 0; i < height; i++) {
			ArrayList<Square> boardRow = new ArrayList<Square>();
			for(int j = 0; j < width; j++) {
			  //boardRow.add(new Square(j, i, board));
			}
			board.add(boardRow);
		}
		
		ArrayList<ArrayList<Square>> updatedBoard = new ArrayList<ArrayList<Square>>();
		
		for(int i = 0; i < height; i++) {
			ArrayList<Square> boardRow = new ArrayList<Square>();
			for(int j = 0; j < width; j++) {
			  //boardRow.add(new Square(j, i, board));
			}
			updatedBoard.add(boardRow);
			String[] boardRowContent = boardRow.stream().map(square -> square.content).toArray((size) -> new String[height]);
			// Separate out board printing logic so the same board prints every time
			
			System.out.println(Arrays.toString(boardRowContent));
		}
		
		return updatedBoard;
	}
	
	public static void printAppIntro() {
		System.out.println();
		System.out.println("The x and y coordiantes of the board range from 0 - 9.");
		System.out.println("Select the coordinates of the square you wish to reveal");
		System.out.println("Please separate the values by a space (e.g. 5 5)");
		System.out.println();
	}
	
	public static String[] getSelectedCoords(Scanner scanner) {
		System.out.print("Enter your coordinate selection: ");
		
		String coordinateStr = scanner.nextLine();
		
		System.out.println();
		
		return coordinateStr.split(" ");
	}
	
	public static ArrayList<Integer> getCoordinateNums(String[] coordStr) {
		ArrayList<Integer> coordNums = new ArrayList<Integer>();
		
		// TODO: Use a loop for this
		
		try {
			coordNums.add(Integer.parseInt(coordStr[0].replaceAll("[^0-9]", "")));
		} catch (NumberFormatException nfe) {
			System.out.println("Error: The x coordinate is not a number");
		}
		
		try {
			coordNums.add(Integer.parseInt(coordStr[1].replaceAll("[^0-9]", "")));
		} catch (NumberFormatException nfe) {
			System.out.println("Error: The y coordinate is not a number");
		}
		
		return coordNums;  
	}
	
	// TODO: Create separate validations class
	
	public static boolean isValidSquare(int xCoord, int yCoord, int width, int height) {
		return xCoord < 0 || xCoord >= width || yCoord < 0 || yCoord >= height;
	}
}
