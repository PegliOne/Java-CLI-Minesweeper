package javaCLIMinesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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
			  boardRow.add(new Square(j, i, bombMap, true, hasBomb));
			}
			board.add(boardRow);
		}
		
		return board;
	}
	
	public static int getHiddenSafeSquaresCount(ArrayList<ArrayList<Square>> board, int height) {
		int safeHiddenSquaresCount = 0;
		
		for(int i = 0; i < height; i++) {
			ArrayList<Square> boardRow = board.get(i);
			ArrayList<Square> safeHiddenSquares = boardRow.stream().filter(square -> !square.hasBomb && square.isHidden).collect(Collectors.toCollection(ArrayList::new));
			safeHiddenSquaresCount += safeHiddenSquares.size();
		}
		
		return safeHiddenSquaresCount;
	}
	
	public static void printBoard(ArrayList<ArrayList<Square>> board, int width, int height) {
		for(int i = 0; i < height; i++) {
			ArrayList<Square> boardRow = board.get(i);
			String[] boardRowContent = boardRow.stream().map(square -> square.content).toArray((size) -> new String[width]);
			System.out.println(Arrays.toString(boardRowContent));
		}
		System.out.println();
	}
}
