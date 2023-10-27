package javaCLIMinesweeper;

import java.util.ArrayList;
import java.util.Scanner;

public class InputProcessingMethods {
	
	// TODO: Refactor functions to further reduce repetition
	
	public static int getBoardDimension(String name, Scanner scanner) {
		int dimension = 0;
		
		while (dimension < 1) {
			System.out.print(String.format("Enter board %s (from 1 - 28): ", name));
			
			String input = scanner.nextLine().replaceAll(" ", "");
			
			if (input.matches("-[0-9]*")) {
				System.out.println();
				System.out.println(String.format("Error: The %s cannot be negative", name));
				System.out.println();
				continue;
			}
			
			try {
				dimension = Integer.parseInt(input);
			} catch (NumberFormatException nfe) {
				System.out.println();
				System.out.println(String.format("Error: The %s must be a number", name));
				System.out.println();
				continue;
			}
						
			// Check for valid numerical input
			
			if (dimension < 1 || dimension > 28) {
				System.out.println();
				System.out.println(String.format("Error: The %s must be be 1 - 28", name));
				System.out.println();
			}
		}
		
		System.out.println();
		return dimension;
	}
	
	public static int getBombCount(int width, int height, Scanner scanner) {
		int count = 0;
		int maxCount = height * width - 1;
		
		while (count < 1 || count > maxCount) {
			System.out.print("Enter (approximate) number of bombs: ");

			String input = scanner.nextLine().trim();
			
			if (input.matches(".*-[0-9].*")) {
				System.out.println();
				System.out.println(String.format("Error: The bomb count cannot be negative"));
				System.out.println();
				continue;
			}
			
			try {
				count = Integer.parseInt(input);
			} catch (NumberFormatException nfe) {
				System.out.println();
				System.out.println(String.format("Error: The bomb count must be a number"));
				System.out.println();
				continue;
			}
						
			// Check for valid numerical input
			
			if (count < 1) {
				System.out.println();
				System.out.println("Error: Bomb count cannot be zero. At least one bomb must be placed");
				System.out.println();
				continue;
			}
			
			if (count > maxCount) {
				System.out.println();
				System.out.print("Error: Bomb count is equal to or above total number of board squares. At least one square must be safe.");
				System.out.println();
			}
			
			System.out.println();
		}
		return count;
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
			coordNums.add(Integer.parseInt(coordStr[0].trim()));
		} catch (NumberFormatException nfe) {
			System.out.println("Error: The x coordinate must be a number");
		}
		
		try {
			coordNums.add(Integer.parseInt(coordStr[1].trim()));
		} catch (NumberFormatException nfe) {
			System.out.println("Error: The y coordiante must be a number");
		}
		
		return coordNums;  
	}
}
