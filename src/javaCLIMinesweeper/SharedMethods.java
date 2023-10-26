package javaCLIMinesweeper;

public class SharedMethods {
	public static boolean isValidSquare(int xCoord, int yCoord, int width, int height) {
		return xCoord < 0 || xCoord >= width || yCoord < 0 || yCoord >= height;
	}
}	
