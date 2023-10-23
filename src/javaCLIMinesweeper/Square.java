package javaCLIMinesweeper;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Square {
	
	final float bombProbability = 0.1f;
	
	String content;
	int xPosition;
	int yPosition;
	ArrayList<ArrayList<Boolean>> bombMap;
	boolean isHidden;
	boolean hasBomb;
	
	public Square(int xPosition, int yPosition, ArrayList<ArrayList<Boolean>> bombMap, boolean isHidden, boolean hasBomb) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.bombMap = bombMap;
		this.isHidden = isHidden;
		this.hasBomb = hasBomb;
		this.content = getContent();
	}
	
	protected void revealSquare() {
	  this.isHidden = false;
	  String newSquareContent = this.getContent();
	  this.content = newSquareContent;
	}
	
	protected ArrayList<int[]> getAdjacentPositions() {
		ArrayList<int[]> adjacentPositions = new ArrayList<int[]>();

		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				// Note: This logic will add the current square as an "adjacent square"
				// It will be filtered out, since it never contains a bomb, but this does reduce efficiency
				
				int[] position = { this.xPosition + j, this.yPosition + i };
				adjacentPositions.add(position);
			}
		}
		
		return adjacentPositions;
	}
	
	private String getContent() {	
		// TODO: Break this method up
		
		String defaultContent = "  ";
		
		// Squares on the far left get an extra space to match the space that is automatically added to the other squares
		if(this.xPosition == 0) {
			defaultContent += " ";
		}
		
		StringBuilder content = new StringBuilder(defaultContent);
		char value;
			
		// Default string of spaces is return if square is hidden
		if(this.isHidden) {
			return content.toString();
		} 
		
		// The value that replaces a space in the string is determined
		if(this.hasBomb) {
		    value = 'B';
		} else {
			value = Integer.toString(getAdjacentBombCount()).charAt(0);
		}
		
		// The value replaces the second last space
		content.setCharAt(content.length() - 2, value);

		return content.toString();
	}
	
	private int getAdjacentBombCount() {
		// TODO: Split this method into smaller methods

		int boardWidth = this.bombMap.get(0).size();
		int boardHeight = this.bombMap.size();
				
		ArrayList<int[]> adjacentPositions = getAdjacentPositions();
		
		ArrayList<int[]> bombPositions = adjacentPositions.stream().filter((position) -> {
			int xPos = position[0];
			int yPos = position[1];
			
			boolean positionIsInvalid = SharedMethods.isValidSquare(xPos, yPos, boardWidth, boardHeight);
			
			if (positionIsInvalid) {
			  return false;
			};
			
			return this.bombMap.get(position[1]).get(position[0]);
		}).collect(Collectors.toCollection(ArrayList::new));
		
		return bombPositions.size();
	}
}
