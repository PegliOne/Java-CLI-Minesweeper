package javaCLIMinesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Board {
	
	int width;
	int height;
	int bombCount;
	ArrayList<ArrayList<Square>> squares;
	
	public Board(int width, int height, int bombCount) {
		this.width = width;
		this.height = height;
		this.bombCount = bombCount;
		this.squares = getSquares();
	}
	
	private ArrayList<ArrayList<Square>> getSquares() {
		boolean[][] bombMap = createBombMap();
		ArrayList<ArrayList<Square>> squares = new ArrayList<ArrayList<Square>>();
		
		for(int i = 0; i < this.height; i++) {
			ArrayList<Square> squaresRow = new ArrayList<Square>();
			for(int j = 0; j < this.width; j++) {
			  boolean hasBomb = bombMap[i][j];	
			  squaresRow.add(new Square(j, i, width, height, bombMap, true, hasBomb));
			}
			squares.add(squaresRow);
		}
		
		return squares;
	}
	
	private boolean[][] createBombMap() {
		boolean[][] bombMap = new boolean[this.height][this.width];
		
		for (int i = 0; i < bombMap.length; i++) {
			Arrays.fill(bombMap[i], false);
		}
		
		addBombs(bombMap);
		
		return bombMap;
	}
	
	private void addBombs(boolean[][] bombMap) {
		ArrayList<int[]> bombPositions = new ArrayList<int[]>();
		
		while (bombPositions.size() < this.bombCount) {
			int x = (int) Math.floor(Math.random() * this.width);
			int y = (int) Math.floor(Math.random() * this.height);
			int[] newBombPosition = { x, y };
			
			boolean bombAlreadyExists = bombPositions.stream().anyMatch((bombPosition) -> bombPosition[0] == x && bombPosition[1] == y);
			
			if (bombAlreadyExists) {
				continue;
			}
			
			bombMap[x][y] = true;
			bombPositions.add(newBombPosition);
		}
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
