/* Board.java provided by github starter project */
 import java.util.Random;
 import java.util.Arrays;

public class Board {
 
	private int[][] board; // holds state of game
	private Random rnd = new Random(0); // setup random # generator
	private int filledTiles = 0;
	private int size;
	private int openSpaces = 16; 
	
	/* default constructor for board */
	// constructors must match exactly the name
	// of the class.
	public Board() {
		
		// instantiate the board
		board = new int[4][4];
		populateOne(); 
		populateOne();
	}
	
	public void eraseBoard() {
		boolean x = false;
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c ++) {
				 if (board[r][c] == 0) {
				    x = true;
				}
			}
        }
        
		if (!x) {
			for (int r = 0; r < board.length; r++) {
				for (int c = 0; c < board[0].length; c ++) {
				    board[r][c] = 0;
				}
			}
		} 
}
			
	public String toString() {

		String builder = "";
		for (int r = 0; r < board.length; r++) {
			for(int c = 0; c< board[0].length; c++) {
				String str = String.format("%04d", board [r][c]);
				builder += str+" ";
			}
			builder += "\n"; 
		}
		return builder;

	}

	public void populateOne() {
	
		int aNum = rnd.nextInt(10);
		int val = rnd.nextInt(10);
		int x = rnd.nextInt(4);
		int y = rnd.nextInt(4);
		if (board[x][y] == 0) {
			if (val< 8) {
				board[x][y] = 2;
			}
			if (val >= 8) {
				board[x][y] = 4;
			}
		}
	}


	public void slideRight(int[] row) {
		
		int check = row.length;
		while (check > 0) {
			for(int i = 1; i < row.length; i ++) {
				if (row[i]==0) {
					int temp = row[i-1];
					row[i-1] = row[i];
					row[i] = temp;
				}
			}
			check--;
	  }
	
}



	public void slideRight() {

		slideRight(board[0]);
		slideRight(board[1]);
		slideRight(board[2]);
		slideRight(board[3]);
		
	}

	/**
	 * Given an array of integers, slide all non-zero elements to the left.
	 * 
	 * zero elements are considered open spots.
	 * 
	 * example:
	 * 
	 * [0 2 0 2] -> [2 2 0 0]
	 * [2 0 0 2] -> [2 2 0 0]
	 */

	public void slideLeft(int[] arr) {
		
		
		int pass = arr.length;
		while (pass > 0) {
			for(int i = 0; i < arr.length-1; i ++) {
				if (arr[i]==0) {
					int temp = arr[i+1];
					arr[i+1] = arr[i];
					arr[i] = temp;
				}
				
			}
			pass--;
		}
	}

	public void slideLeft() {
		
		slideLeft(board[0]);
		slideLeft(board[1]);
		slideLeft(board[2]);
		slideLeft(board[3]);
	}

	/*
	 * Given a 2D array and a column number, return a 1D array representing the
	 * elements in the given column number.
	 */
	public int[] getCol(int[][] data, int c) {
		
		//you can also add print out statements here
		
		int [] arr = new int[data.length];
		
		for (int i = 0; i < data.length; i++) {
			arr[i] = data[i][c];
					}
		return arr;
	
	}

	public void slideUp(int[] arr) {
		/* calls a helper method */
		// do not rewrite logic you already have!
		slideLeft(arr);
		}
		

	public void slideUp() {
		
		
        for (int i = 0; i < board[0].length; i++ ) {

		int [] temp = getCol(board , i);
		    slideUp(temp);
		
		
		    for(int j = 0; j < board.length; j++) {
			
			board[j][i] = temp[j];
			board[j][i] = temp[j];
			board[j][i] = temp[j];
			board[j][i] = temp[j];
		    }
		}
	}
		
	
	public void slideDown(int[] arr) {
		slideRight(arr);
	}
	
	public void slideDown() {
		for (int i = 0; i < board[0].length; i++ ) {

			int [] temp = getCol(board , i);
			slideDown(temp);
			
			
			for(int j = 0; j < board.length; j++) {
				
				board[j][i] = temp[j];
				board[j][i] = temp[j];
				board[j][i] = temp[j];
				board[j][i] = temp[j];
				
			}
			
		}
	}


	public void combineRight() {
		for (int r = 0; r < board.length; r++) {
			for(int c = board.length-1; c > 0; c--) {
				if (board[r][c] == board[r][c-1]) {
					board[r][c] = board[r][c]*2;
					board[r][c-1] = 0;
				}
			}
		}
	}

	/*
	 * same behavior as combineRight but the right element is zeroed out when
	 * two elements are combined
	 */

	public void combineLeft() {
		for (int r = 0; r < board.length; r++) {
			for(int c = 0; c< board[0].length-1; c++) {
				if (board[r][c] == board[r][c+1]) {
					board[r][c] = board[r][c]*2;
					board[r][c+1] = 0;
				}
			}
		}
	}
	


	public void combineUp() {
		for (int r = 0; r < board.length-1; r++) {
			for(int c = 0; c< board[0].length; c++) {
				if (board[r][c] == board[r+1][c]) {
					board[r+1][c] = board[r][c]*2;
					board[r][c] = 0;
				}
			}
		}
	}
	/*
	 * same behavior as combineRight but the top element is zeroed out when two
	 * elements are combined
	 */
	public void combineDown() {
		for (int r = 0; r < board.length-1; r++) {
			for(int c = 0; c< board[r].length; c++) {
				if (board[r][c] == board[r+1][c]) {
					board[r][c] = board[r][c]*2;
					board[r+1][c] = 0;
				}
			}
		}
	}

	/* reminder: these are the methods that will ultimately invoke
	 * a series of methods
	 * 
	 * the combine and slide methods should not worry about each other's methods
	 */
	public void left() {
	    //numbers slide, combine

		slideLeft();
		combineLeft();
		slideLeft();
		
		
	}

	public void right() {
		slideRight();
		combineRight();
		slideRight();

	}

	public void up() {
		slideUp();
		combineUp();
		slideUp();

	}

	public void down() {
	    slideDown();
	    combineDown();
	    slideDown();
		

	}
	
	

	public boolean gameOver() {
		boolean x = true;
	
		for (int r = 0; r < board.length; r++) {
			   for (int c = 0; c < board[0].length; c ++) {
				   if (board[r][c] == 0) {
					      x = false;
				   }
				}
			}
	
		if (!x) {
		    return false;
		} else {
		    return true;
		}
	}

	public int[][] getBoard() {
		return board;
	}
	
	// populate with a given 2d array
	public void populate(int[][] arr) {
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[r].length; c++) {
				board[r][c] = arr[r][c];
			}
		}
		
		int num = rnd.nextInt(6);
		//keep trying to generate a new number if the number generated is 4
		while(num==4){
	                num = rnd.nextInt(6);//generate a new number until the number generated is not 4!
		}
		int total = num+2; //now the number can be used 

	}

}