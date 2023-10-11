public class Square {
	
	String content;

	public Square() {
		this.content = setContent();
	}
	
	private String setContent() {
		boolean hasBomb = Math.random() >= 0.5;
		if(hasBomb) {
			return "BOMB!";
		} else {
			return "     ";
		}
	}
	
}
