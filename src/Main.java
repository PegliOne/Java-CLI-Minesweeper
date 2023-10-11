import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int boardWidth = 10;
		int boardHeight = 10;
		
		ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < boardHeight; i++) {
			ArrayList<String> boardRow = new ArrayList<String>();
			for(int j = 0; j < boardWidth; j++) {
			  boardRow.add(new Square().content);
			}
			board.add(boardRow);
			System.out.println(boardRow);
		}

	}

}
