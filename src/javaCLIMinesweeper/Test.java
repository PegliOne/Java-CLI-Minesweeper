package javaCLIMinesweeper;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(verifyCode(123));
	}
	
	final static int secretCode = 123;
	static ArrayList<Integer> previousAttempts = new ArrayList();
	
	public static Boolean verifyCode(int attempt) {
		Integer currentAttempt = new Integer(attempt);
		previousAttempts.add(currentAttempt);
		if (attempt == secretCode) return true;
		
		return false;
	}

}
