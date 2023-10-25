package javaCLIMinesweeper;

import java.util.ArrayList;

public class SetUpMethods {
	public static ArrayList<ArrayList<Boolean>> createBombMap(int width, int height, double bombProbability) {
		ArrayList<ArrayList<Boolean>> bombMap = new ArrayList<ArrayList<Boolean>>();
		
		for(int i = 0; i < height; i++) {
			ArrayList<Boolean> bombMapRow = new ArrayList<Boolean>();
			for(int j = 0; j < width; j++) {
			  bombMapRow.add(Math.random() < bombProbability);
			}
			bombMap.add(bombMapRow);
		}
		
		return bombMap;
	}

	public static ArrayList<ArrayList<Square>> createBoardFromBombMap(ArrayList<ArrayList<Boolean>> bombMap, int width, int height) {
		ArrayList<ArrayList<Square>> board = new ArrayList<ArrayList<Square>>();
		
		for(int i = 0; i < height; i++) {
			ArrayList<Square> boardRow = new ArrayList<Square>();
			for(int j = 0; j < width; j++) {
			  boolean hasBomb = bombMap.get(i).get(j);	
			  boardRow.add(new Square(j, i, width, height, bombMap, true, hasBomb));
			}
			board.add(boardRow);
		}
		
		return board;
	}
}