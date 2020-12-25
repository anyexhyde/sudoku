/* Name: Anyelina Wu
 * PennKey: anyelina
 * Recitation: 201
 * Execution: Grid.java
 * 
 * 
 * Draws the Sudoku grid, starting input, other functionalities, and game rules
 * 
 **/
public class Grid {
	// private fields
	private String textFile;
	private static String[][] sudokuArray = new String[6][6];
	private boolean noError = true;
	private String[][] initialArray = new String[6][6];

	private static final double[] XCOORLEFT = new double[] 
			{ 0.15, 0.25, 0.35, 0.15, 0.25, 0.35 };
	private static final double[] XCOORRIGHT = new double[] 
			{ 0.45, 0.55, 0.65, 0.45, 0.55, 0.65 };
	private static final double[] YCOORUP = new double[] 
			{ 0.65, 0.65, 0.65, 0.55, 0.55, 0.55 };
	private static final double[] YCOORCENTER = new double[] 
			{ 0.45, 0.45, 0.45, 0.35, 0.35, 0.35 };
	private static final double[] YCOORDOWN = new double[] 
			{ 0.25, 0.25, 0.25, 0.15, 0.15, 0.15 };

	// Constructor
	public Grid(String textFile) {
		this.textFile = textFile;
		In inStream = new In(textFile);

		// separate input into six labeled rows for easier error check
		String rowOne = inStream.readLine();
		String rowTwo = inStream.readLine();
		String rowThree = inStream.readLine();
		String rowFour = inStream.readLine();
		String rowFive = inStream.readLine();
		String rowSix = inStream.readLine();
		String input = rowOne + rowTwo + rowThree + rowFour + rowFive + rowSix;

		// place inputs in 2d array
		int index = 0;
		for (int row = 0; row < sudokuArray.length; row++) {
			for (int col = 0; col < sudokuArray[0].length; col++) {
				sudokuArray[row][col] = "" + input.charAt(index);
				initialArray[row][col] = "" + input.charAt(index);
				index++;
			}
		}



		// error: not 6x6
		if (rowOne.length() != 6 || rowTwo.length() != 6 || 
				rowThree.length() != 6 || rowFour.length() != 6 || 
				rowFive.length() != 6 || rowSix.length() != 6) {
			throw new IllegalArgumentException("ERROR: not 6x6");
		}

		// error: null input
		if (input.isEmpty() == true) {
			throw new RuntimeException("ERROR: empty input");
		}

		// error: invalid chars 
		for (int i = 0; i < input.length(); i++) {

			if (input.charAt(i) != '1' && input.charAt(i) != '2' && 
					input.charAt(i) != '3' && input.charAt(i) != '4' && 
					input.charAt(i) != '5' && input.charAt(i) != '6' &&
					input.charAt(i) != ' ') {

				throw new RuntimeException(
						"ERROR: input contains invalid characters");
			}
		}

		// error: same numbers in same row
		for (int i = 0; i < rowOne.length(); i++) {
			for (int j = 1; j < rowOne.length(); j++)
				if (rowOne.charAt(i) != ' ' && rowTwo.charAt(i) != ' ' && 
				rowThree.charAt(i) != ' ' && rowFour.charAt(i) != ' ' && 
				rowFive.charAt(i) != ' ' && rowSix.charAt(i) != ' ') {
					if (rowOne.charAt(i) == rowOne.charAt(j) || 
							rowTwo.charAt(i) == rowTwo.charAt(j) || 
							rowThree.charAt(i) == rowThree.charAt(j) || 
							rowFour.charAt(i) == rowFour.charAt(j) || 
							rowFive.charAt(i) == rowFive.charAt(j) || 
							rowSix.charAt(i) == rowSix.charAt(j)) {				
						throw new RuntimeException("ERROR: same numbers in row");
					}
				}	
		}

		// error: same numbers in same column
		for (int row = 0; row < sudokuArray.length; row++) {
			for (int col = 0; col < sudokuArray[0].length - 1; col++) {
				if (sudokuArray[row][col] == sudokuArray [row][col + 1]) {
					throw new RuntimeException("ERROR: same numbers in column");
				}
			}
		}
	}

	/* 
	 * Description: draws Sudoku Grid
	 *
	 * Input: 
	 *
	 * Output: void
	 */    

	public void drawGrid() {

		// draw columns
		for (double i = 0.1; i <= 0.7; i += 0.1) {
			// thicc border and center lines
			if (i == 0.1 || i == 0.4 || i == 0.7) {
				PennDraw.setPenRadius(0.008);
				PennDraw.line(i, 0.1, i, 0.7);
			} else { // normal lines
				PennDraw.setPenRadius();
				PennDraw.line(i, 0.1, i, 0.7);
			}
		}

		// draw column number labels
		PennDraw.setFontBold();
		PennDraw.setPenColor(49, 140, 0);
		PennDraw.text(0.4, 0.85, "COLUMN");
		double colX = 0.15;
		double colY = 0.75;
		int colCount = 0;
		while (colX <= 0.65 && colCount < 6) {
			PennDraw.text(colX, colY, "" + colCount);
			colX += 0.1;
			colCount++;
		}
		PennDraw.setFontPlain();
		PennDraw.setPenColor();


		// draw rows 
		for (double j = 0.1; j <= 0.7; j += 0.1) {
			// thicc 2x3 box dividing lines
			if (j == 0.30000000000000004 || j == 0.1 || j == 0.7 || j == 0.5) {
				PennDraw.setPenRadius(0.008);
				PennDraw.line(0.1, j, 0.7, j);
			} else { // normal lines
				PennDraw.setPenRadius();
				PennDraw.line(0.1, j, 0.7, j);
			}
		}

		// draw row number labels
		PennDraw.setFontBold();
		PennDraw.setPenColor(PennDraw.MAGENTA);
		PennDraw.text(0.85, 0.4, "ROW");
		double rowX = 0.75;
		double rowY = 0.65;
		int rowCount = 0;
		while (rowY >= 0.15 && rowCount < 6) {
			PennDraw.text(rowX, rowY, "" + rowCount);
			rowY -= 0.1;
			rowCount++;
		}
		PennDraw.setFontPlain();
		PennDraw.setPenColor();
	}

	/* 
	 * Description: add input numbers to grid 
	 *
	 * Input: String textFile
	 *
	 * Output: void
	 */    

	public void addInput(String textFile) {

		// place numbers on grids' center
		double xCenter = 0.15;
		double yCenter = 0.65;
		// input numbers from top to bottom row, left to right
		while (xCenter <= 0.7 && yCenter >= 0.15) {
			for (int row = 0; row < sudokuArray.length; row++) {
				for (int col = 0; col < sudokuArray[0].length; col++) {

					if (xCenter > 0.65) {
						xCenter = 0.15;
						yCenter -= 0.1;
					}

					PennDraw.text(xCenter, yCenter, initialArray[row][col]);
					xCenter += 0.1;
				}
			}
		}
	}

	/* 
	 * Description: add new numbers to grid (marked in blue)
	 *
	 * Input: String textFile
	 *
	 * Output: void
	 */
	public void addNumbers(String input, int row, int col, double x, double y) {
		if (!input.equals("D")) {
			PennDraw.setPenColor(PennDraw.BLUE);
			sudokuArray[row][col] = input;
			PennDraw.text(x, y, input);
		}

		// error: checks if same numbers in same column
		for (int i = 0; i < sudokuArray.length; i++) {
			if (i == row) {
				continue;
			}
			if (sudokuArray[i][col].equals(input)) {
				PennDraw.setPenColor(PennDraw.RED);
				PennDraw.setPenRadius();
				PennDraw.rectangle(Math.round(x * 10 * 2) / 2.0 / 10,
						0.4, 0.05, 0.3);
				PennDraw.text(Math.round(x * 10 * 2) / 2.0 / 10, 
						Math.round(y * 10 * 2) / 2.0 / 10, input);
				PennDraw.text(0.5, 0.9, "ERROR: same numbers in this column");
				setError();
			} 
		}

		// error: checks if same numbers in same row
		for (int i = 0; i < 6; i++) {
			if (i == col) {
				continue;
			}
			if (sudokuArray[row][i].equals(input)) {
				PennDraw.setPenColor(PennDraw.RED);
				PennDraw.setPenRadius();
				PennDraw.rectangle(0.4, Math.round(y * 10 * 2) / 2.0 / 10, 
						0.3, 0.05);
				PennDraw.text(Math.round(x * 10 * 2) / 2.0 / 10, 
						Math.round(y * 10 * 2) / 2.0 / 10, input);
				PennDraw.text(0.5, 0.97, "ERROR: same numbers in this row");
				setError();
			}
		}
	}


	/* 
	 * Description: check if same numbers in same 3x2 square
	 *
	 * Input: 
	 *
	 * Output: 
	 */

	public void checkSameSquare() {
		// one stores elements in upper left 3x2 grid
		String[] one = new String[6];
		one[0] = sudokuArray[0][0];
		one[1] = sudokuArray[0][1];
		one[2] = sudokuArray[0][2];
		one[3] = sudokuArray[1][0];
		one[4] = sudokuArray[1][1];
		one[5] = sudokuArray[1][2];

		for (int i = 0; i < one.length - 1; i++) {
			if (!one[i].equals(" ")) {
				if (one[i].equals(one[i + 1])) {
					PennDraw.setPenColor(PennDraw.RED);
					PennDraw.text(0.5, 0.93, "ERROR: same numbers in this grid");
					setError();
				}
			}
		}
		// two stores elements in center left 3x2 grid
		String[] two = new String[6];
		two[0] = sudokuArray[2][0];
		two[1] = sudokuArray[2][1];
		two[2] = sudokuArray[2][2];
		two[3] = sudokuArray[3][0];
		two[4] = sudokuArray[3][1];
		two[5] = sudokuArray[3][2];

		for (int i = 0; i < two.length - 1; i++) {
			if (!one[i].equals(" ")) {
				if (two[i].equals(two[i + 1])) {
					PennDraw.setPenColor(PennDraw.RED);
					//PennDraw.rectangle(0.25, 0.4, 0.15, 0.1);
					PennDraw.text(0.5, 0.93, "ERROR: same numbers in this grid");
					setError();
				}
			}
		}

		// three stores elements in bottom left 3x2 grid
		String[] three = new String[6];
		three[0] = sudokuArray[4][0];
		three[1] = sudokuArray[4][1];
		three[2] = sudokuArray[4][2];
		three[3] = sudokuArray[5][0];
		three[4] = sudokuArray[5][1];
		three[5] = sudokuArray[5][2];

		for (int i = 0; i < three.length - 1; i++) {
			if (!one[i].equals(" ")) {
				if (three[i].equals(three[i + 1])) {
					PennDraw.setPenColor(PennDraw.RED);
					//PennDraw.rectangle(0.25, 0.2, 0.15, 0.1);
					PennDraw.text(0.5, 0.93, "ERROR: same numbers in this grid");
					setError();
				}
			}
		}

		// four stores elements in upper right 3x2 grid
		String[] four = new String[6];
		four[0] = sudokuArray[0][3];
		four[1] = sudokuArray[0][4];
		four[2] = sudokuArray[0][5];
		four[3] = sudokuArray[1][3];
		four[4] = sudokuArray[1][4];
		four[5] = sudokuArray[1][5];

		for (int i = 0; i < four.length - 1; i++) {
			if (!one[i].equals(" ")) {
				if (four[i].equals(four[i + 1])) {
					PennDraw.setPenColor(PennDraw.RED);
					//PennDraw.rectangle(0.55, 0.6, 0.15, 0.1);
					PennDraw.text(0.5, 0.93, "ERROR: same numbers in this grid");
					setError();
				}
			}
		}

		String[] five = new String[6];
		five[0] = sudokuArray[2][3];
		five[1] = sudokuArray[2][4];
		five[2] = sudokuArray[2][5];
		five[3] = sudokuArray[3][3];
		five[4] = sudokuArray[3][4];
		five[5] = sudokuArray[3][5];

		for (int i = 0; i < five.length - 1; i++) {
			if (!one[i].equals(" ")) {
				if (five[i].equals(five[i + 1])) {
					PennDraw.setPenColor(0, 100, 100);
					//PennDraw.rectangle(0.55, 0.4, 0.15, 0.1);
					PennDraw.text(0.5, 0.93, "ERROR: same numbers in this grid");
					setError();
				}
			}
		}

		String[] six = new String[6];
		six[0] = sudokuArray[4][3];
		six[1] = sudokuArray[4][4];
		six[2] = sudokuArray[4][5];
		six[3] = sudokuArray[5][3];
		six[4] = sudokuArray[5][4];
		six[5] = sudokuArray[5][5];

		for (int i = 0; i < six.length - 1; i++) {
			if (!one[i].equals(" ")) {
				if (six[i].equals(six[i + 1])) {
					PennDraw.setPenColor(PennDraw.RED);
					//PennDraw.rectangle(0.55, 0.2, 0.15, 0.1);
					PennDraw.text(0.5, 0.93, "ERROR: same numbers in this grid");
					setError();
				}
			}
		}
	}

	/* 
	 * Description: check win
	 *
	 * Input: 
	 *
	 * Output: 
	 */
	public void checkWin() {
		boolean isFull = true;
		boolean noError = true;

		for (int i = 0; i < sudokuArray.length; i++) {
			for (int j = 0; j < sudokuArray.length; j++) {
				if (sudokuArray[i][j].equals(" ")) {
					System.out.println("i" + i);
					System.out.println("j" + j);
					isFull = false;
				} 
			}
		}
		//System.out.println("isFull" + isFull);
		//System.out.println("error" + noError);
		if (isFull && noError) {
			System.out.println("ads");
			PennDraw.setPenColor(PennDraw.RED);
			PennDraw.setFontSize(80);
			PennDraw.text(0.5, 0.5, "WIN");
			PennDraw.setPenColor();
		}
	}

	/* 
	 * Description: setter for variable noError
	 *
	 * Input: 
	 *
	 * Output: 
	 */

	public void setError() {
		noError = false;
	}

	/* 
	 * Description: set true for variable noError
	 *
	 * Input: 
	 *
	 * Output: 
	 */

	public void setErrorTrue() {
		noError = true;
	}

	/* 
	 * Description: delete numbers from grid
	 *
	 * Input: int row, int col
	 *
	 * Output: void
	 */
	public void deleteNumber(int row, int col, double x, double y) {
		sudokuArray[row][col] = " ";
		PennDraw.setPenColor(PennDraw.WHITE);
		PennDraw.filledSquare(x, y, 0.04);
		setErrorTrue();
	}

	/* 
	 * Description: reset grid to starting input
	 *
	 * Input: 
	 *
	 * Output: void
	 */  

	public void reset() {
		sudokuArray = initialArray;
		PennDraw.clear();
		PennDraw.setPenRadius();
		PennDraw.setPenColor();
		drawGrid();
		addInput(textFile);
		PennDraw.square(0.9, 0.9, 0.05);
		PennDraw.text(0.9, 0.9, "Reset");
		PennDraw.square(0.1, 0.9, 0.05);
		PennDraw.text(0.1, 0.9, "Win?");
		PennDraw.rectangle(0.9, 0.78, 0.1, 0.05);
		PennDraw.text(0.9, 0.78, "Clear Error");
		setErrorTrue();

	}

	//public static void main(String[] args) {
	/*	Grid grid = new Grid("start.txt");
		grid.drawGrid();
		grid.addInput("start.txt"); */
	//} 

}