package javaCLIMinesweeper;

import java.util.ArrayList;
import java.util.Arrays;

public class BoardMethods {
	public static ArrayList<ArrayList<Boolean>> createBombMap(int width, int height) {
		ArrayList<ArrayList<Boolean>> bombMap = new ArrayList<ArrayList<Boolean>>();
		
		for(int i = 0; i < height; i++) {
			ArrayList<Boolean> bombMapRow = new ArrayList<Boolean>();
			for(int j = 0; j < width; j++) {
			  bombMapRow.add(Math.random() < 0.1);
			}
			bombMap.add(bombMapRow);
		}
		
		return bombMap;
	}

	public static ArrayList<ArrayList<Square>> createBoardFromBombMap(ArrayList<ArrayList<Boolean>> bombMap) {
		int width = bombMap.get(0).size();
		int height = bombMap.size();
		
		ArrayList<ArrayList<Square>> board = new ArrayList<ArrayList<Square>>();
		
		for(int i = 0; i < height; i++) {
			ArrayList<Square> boardRow = new ArrayList<Square>();
			for(int j = 0; j < width; j++) {
			  boolean hasBomb = bombMap.get(i).get(j);	
			  boardRow.add(new Square(j, i, bombMap, true, hasBomb));
			}
			board.add(boardRow);
		}
		
		return board;
	}
	
	public static void printBoard(ArrayList<ArrayList<Square>> board) {
		int height = board.size();
		
		for(int i = 0; i < height; i++) {
			ArrayList<Square> boardRow = board.get(i);
			String[] boardRowContent = boardRow.stream().map(square -> square.content).toArray((size) -> new String[height]);
			System.out.println(Arrays.toString(boardRowContent));
		}
		System.out.println();
	}
}
