package javaCLIMinesweeper;

import java.util.ArrayList;

public class SharedMethods {
	public static boolean isValidSquare(int xCoord, int yCoord, int width, int height) {
		return xCoord < 0 || xCoord >= width || yCoord < 0 || yCoord >= height;
	}
	
	public static void revealAdjacentSquares(Square selectedSquare, ArrayList<ArrayList<Square>> board, int boardWidth, int boardHeight) {
		ArrayList<int[]> adjacentPositions = selectedSquare.getAdjacentPositions();
		for (int[] position : adjacentPositions) {
			int xPos = position[0];
			int yPos = position[1];
			
			boolean positionIsInvalid = SharedMethods.isValidSquare(xPos, yPos, boardWidth, boardHeight);
			
			if (positionIsInvalid) {
			  continue;
			};
			
			Square adjacentSquare = board.get(position[1]).get(position[0]);
			
			if (adjacentSquare.isHidden) {
				adjacentSquare.revealSquare();
					
				if (Integer.parseInt(adjacentSquare.content.trim()) == 0) {
					SharedMethods.revealAdjacentSquares(adjacentSquare, board, boardWidth, boardHeight);
				}
			}
		}
	}
}	
