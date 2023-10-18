package javaCLIMinesweeper;

import java.util.ArrayList;
import java.util.Scanner;

public class InputProcessingMethods {
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
}
