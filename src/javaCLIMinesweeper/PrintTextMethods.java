package javaCLIMinesweeper;

public class PrintTextMethods {
	public static void printAppIntro() {
		System.out.println("Welcome to the Java CLI Minesweeper App");
		System.out.println();
		System.out.println("Select the coordinates of the square you wish to reveal");
		System.out.println("Enter the x coordinate followed by the y coordinate");
		System.out.println("Please separate the values by a space (e.g. 5 5)");
		System.out.println("The origin (i.e. the square with coordinates 0 0) is the top left square");
		System.out.println();
	}
	
	public static void printGameOver() {
		System.out.println("Boom!");
		System.out.println();
		System.out.println("Game Over");
		System.out.println();
	}
}