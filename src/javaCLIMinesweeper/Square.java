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
		this.content = setContent();
	}
	
	private String setContent() {
		if(this.isHidden) {
		  return " ";
		}
		
		if(this.hasBomb) {
			return "B";
		}
		
		return Integer.toString(getSurroundingBombCount());
	}
	
	private ArrayList<int[]> getSurroundingPositions() {
		ArrayList<int[]> surroundingPositions = new ArrayList<int[]>();
		
		// rewrite to use a loop of some kind
		
		int[] topLeft = { this.xPosition - 1, this.yPosition - 1 };
		surroundingPositions.add(topLeft);
		
		int[] top = { this.xPosition, this.yPosition - 1 };
		surroundingPositions.add(top);
		
		int[] topRight = { this.xPosition + 1, this.yPosition - 1 };
		surroundingPositions.add(topRight);
		
		int[] left = { this.xPosition - 1, this.yPosition };
		surroundingPositions.add(left);
		
		int[] right = { this.xPosition + 1, this.yPosition };
		surroundingPositions.add(right);
		
		int[] bottomLeft = { this.xPosition - 1, this.yPosition + 1 };
		surroundingPositions.add(bottomLeft);
		
		int[] bottom = { this.xPosition, this.yPosition + 1 };
		surroundingPositions.add(bottom);
		
		int[] bottomRight = { this.xPosition + 1, this.yPosition + 1 };
		surroundingPositions.add(bottomRight);
		
		return surroundingPositions;
	}
	
	private int getSurroundingBombCount() {
		ArrayList<int[]> surroundingPositions = getSurroundingPositions();
		
		// Pass in board width and height
		
		ArrayList<int[]> bombPositions = surroundingPositions.stream().filter((position) -> {
			if (position[0] < 0 || position[0] >= 10 || position[1] < 0 || position[1] >= 10) {
			  return false;
			};
			return this.bombMap.get(position[1]).get(position[0]);
		}).collect(Collectors.toCollection(ArrayList::new));
		return bombPositions.size();
	}
}
