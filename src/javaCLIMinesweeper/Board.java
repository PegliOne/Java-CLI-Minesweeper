package javaCLIMinesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Board {
	
	int width;
	int height;
	double bombProbability;
	ArrayList<ArrayList<Square>> squares;
	
	public Board(int width, int height, double bombProbability) {
		this.width = width;
		this.height = height;
		this.bombProbability = bombProbability;
		this.squares = getSquares();
	}
	
	private ArrayList<ArrayList<Square>> getSquares() {
		ArrayList<ArrayList<Boolean>> bombMap = createBombMap();
		ArrayList<ArrayList<Square>> squares = new ArrayList<ArrayList<Square>>();
		
		for(int i = 0; i < this.height; i++) {
			ArrayList<Square> squaresRow = new ArrayList<Square>();
			for(int j = 0; j < this.width; j++) {
			  boolean hasBomb = bombMap.get(i).get(j);	
			  squaresRow.add(new Square(j, i, width, height, bombMap, true, hasBomb));
			}
			squares.add(squaresRow);
		}
		
		return squares;
	}
	
	private ArrayList<ArrayList<Boolean>> createBombMap() {
		ArrayList<ArrayList<Boolean>> bombMap = new ArrayList<ArrayList<Boolean>>();
		
		for(int i = 0; i < this.height; i++) {
			ArrayList<Boolean> bombMapRow = new ArrayList<Boolean>();
			for(int j = 0; j < this.width; j++) {
			  bombMapRow.add(Math.random() < this.bombProbability);
			}
			bombMap.add(bombMapRow);
		}
		
		return bombMap;
	}
	
	protected void revealAdjacentSquares(Square selectedSquare) {
		ArrayList<int[]> adjacentPositions = selectedSquare.getAdjacentPositions();
		for (int[] position : adjacentPositions) {
			int xPos = position[0];
			int yPos = position[1];
			
			boolean positionIsInvalid = SharedMethods.isValidSquare(xPos, yPos, this.width, this.height);
			
			if (positionIsInvalid) {
			  continue;
			};
			
			Square adjacentSquare = this.squares.get(position[1]).get(position[0]);
			
			if (adjacentSquare.isHidden) {
				adjacentSquare.revealSquare();
					
				if (Integer.parseInt(adjacentSquare.content.trim()) == 0) {
					this.revealAdjacentSquares(adjacentSquare);
				}
			}
		}
	}
	
	protected void revealAllBombs() {	
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				Square square = this.squares.get(i).get(j);
				if(square.hasBomb) {
					square.revealSquare();
				}
			}
		}
	}
	
	protected int getHiddenSafeSquaresCount() {
		int safeHiddenSquaresCount = 0;
		
		for(int i = 0; i < this.height; i++) {
			ArrayList<Square> squaresRow = this.squares.get(i);
			ArrayList<Square> safeHiddenSquares = squaresRow.stream().filter(square -> !square.hasBomb && square.isHidden).collect(Collectors.toCollection(ArrayList::new));
			safeHiddenSquaresCount += safeHiddenSquares.size();
		}
		
		return safeHiddenSquaresCount;
	}
	
	protected void printBoard() {
		for(int i = 0; i < this.height; i++) {
			ArrayList<Square> squaresRow = this.squares.get(i);
			String[] squaresRowContent = squaresRow.stream().map(square -> square.content).toArray((size) -> new String[width]);
			System.out.println(Arrays.toString(squaresRowContent));
		}
		System.out.println();
	}
}
