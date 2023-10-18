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
	
	public void revealSquare() {
	  this.isHidden = false;
	  String newSquareContent = this.getContent();
	  this.content = newSquareContent;
	}
	
	private String getContent() {
		if(this.isHidden) {
		  return " ";
		}

		if(this.hasBomb) {
			return "B";
		}
		
		return Integer.toString(getAdjacentBombCount());
	}
	
	private int getAdjacentBombCount() {
		// TODO: Split this method into smaller methods

		int boardWidth = this.bombMap.get(0).size();
		int boardHeight = this.bombMap.size();
				
		ArrayList<int[]> adjacentPositions = getAdjacentPositions();
		
		ArrayList<int[]> bombPositions = adjacentPositions.stream().filter((position) -> {
			int xCoord = position[0];
			int yCoord = position[1];
			
			boolean positionIsInvalid = SharedMethods.isValidSquare(xCoord, yCoord, boardWidth, boardHeight);
			
			if (positionIsInvalid) {
			  return false;
			};
			
			return this.bombMap.get(position[1]).get(position[0]);
		}).collect(Collectors.toCollection(ArrayList::new));
		
		return bombPositions.size();
	}
	
	private ArrayList<int[]> getAdjacentPositions() {
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
}
