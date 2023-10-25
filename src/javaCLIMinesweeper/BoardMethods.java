package javaCLIMinesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BoardMethods {
	public static void revealAllBombs(ArrayList<ArrayList<Square>> board, int width, int height) {	
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				Square square = board.get(i).get(j);
				if(square.hasBomb) {
					square.revealSquare();
				}
			}
		}
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
