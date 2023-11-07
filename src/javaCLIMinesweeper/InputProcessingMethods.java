package javaCLIMinesweeper;

import java.util.ArrayList;
import java.util.Scanner;

public class InputProcessingMethods {
	
	public static int getBoardDimension(String name, Scanner scanner) {
		int dimension = 0;
		
		while (dimension < 1 || dimension > 28) {
			System.out.print(String.format("Enter board %s (between 1 and 28): ", name));
			
			String input = scanner.nextLine().replaceAll(" ", "");

			if (input.matches("-[0-9]*")) {
				PrintTextMethods.printSingleLineMessage(String.format("Error: The %s cannot be negative", name));
				continue;
			}

			try {
				dimension = Integer.parseInt(input);
			} catch (NumberFormatException nfe) {
				PrintTextMethods.printSingleLineMessage(String.format("Error: The %s must be a number", name));
				continue;
			}
						
			if (dimension < 1 || dimension > 28) {
				PrintTextMethods.printSingleLineMessage(String.format("Error: The %s must be be 1 - 28", name));
			}
		}
		
		System.out.println();
		return dimension;
	}
	
	public static int getBombCount(int width, int height, Scanner scanner) {
		int count = 0;
		int maxCount = height * width - 1;
		
		while (count < 1 || count > maxCount) {
			System.out.print(String.format("Enter number of bombs (between 1 and %s): ", maxCount));

			String input = scanner.nextLine().trim();
			
			if (input.matches(".*-[0-9].*")) {
				PrintTextMethods.printSingleLineMessage("Error: The bomb count cannot be negative");
				continue;
			}
			
			try {
				count = Integer.parseInt(input);
			} catch (NumberFormatException nfe) {
				PrintTextMethods.printSingleLineMessage("Error: The bomb count must be a number");
				continue;
			}
						
			if (count < 1) {
				PrintTextMethods.printSingleLineMessage("Error: Bomb count cannot be zero. At least one bomb must be placed");
				continue;
			}
			
			if (count > maxCount) {
				PrintTextMethods.printSingleLineMessage("Error: Bomb count cannot be equal to or greater than the total number of board squares. At least one square must be safe.");
			}
		}
		
		System.out.println();
		return count;
	}
	
	public static String[] getSelectedCoords(Scanner scanner) {
		System.out.print("Enter your coordinate selection: ");
		
		String coordinateStr = scanner.nextLine();
		
		System.out.println();
		
		return coordinateStr.split(" ");
	}
	
	public static ArrayList<Integer> getCoordinateNums(String[] coordInputs) {
		ArrayList<Integer> coordNums = new ArrayList<Integer>();
		
		addInputToCoordinatesArray("x", coordInputs[0], coordNums);
		addInputToCoordinatesArray("y", coordInputs[1], coordNums);
		
		return coordNums;  
	}
	
	private static void addInputToCoordinatesArray(String coordName, String coordInput, ArrayList<Integer> coordNums) {
		try {
			coordNums.add(Integer.parseInt(coordInput.trim()));
		} catch (NumberFormatException nfe) {
			System.out.println(String.format("Error: The %s coordinate must be a number", coordName));
		}
	}
}
