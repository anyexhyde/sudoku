/* Name: Anyelina Wu
 * PennKey: anyelina
 * Recitation: 201
 * Execution: RunGame.java
 * 
 * 
 * Game action takes place here
 * 
 **/

public class RunGame {

	// main
	public static void main(String[] args) {
		PennDraw.setPenRadius();
		PennDraw.square(0.9, 0.9, 0.05);
		PennDraw.text(0.9, 0.9, "Reset");
		PennDraw.square(0.1, 0.9, 0.05);
		PennDraw.text(0.1, 0.9, "Win?");
		PennDraw.rectangle(0.9, 0.78, 0.1, 0.05);
		PennDraw.text(0.9, 0.78, "Clear Error");


		// command line args

		String textFile = args[0];
		Grid grid = new Grid(textFile);
		grid.drawGrid();
		grid.addInput(args[0]);

		while (true) { // while game is playing

			// mouse press conditions
			int row = 0;
			int col = 0;

			while (PennDraw.mousePressed() == false) { 
				// left empty to account for when mouse isn't pressed
			}

			double mx = PennDraw.mouseX();
			double my = PennDraw.mouseY();

			// clicking on "Clear Error" clears error message and highlights
			if (mx > 0.8 && my < 0.8 && my > 0.67) {
				PennDraw.setPenColor(PennDraw.WHITE);
				PennDraw.filledRectangle(0.5, 0.95, 0.3, 0.07);
				PennDraw.setPenColor();
				grid.drawGrid();
				grid.setErrorTrue();
			}

			// clicking on "Reset" resets grid
			if (mx >= 0.8 && my >= 0.8) {
				PennDraw.clear();
				grid.reset();
			} 

			// clicking on "Win?" checks if game is won
			//System.out.println(my);
			//System.out.println(mx);
			if (mx <= 0.2 && my >= 0.8) {
				grid.checkWin();
			}

			//System.out.println("mouse pressed");

			// converts mouse clicking to row/col number
			col = (int) (mx * 10) - 1;
			row = 6 - (int) (my * 10);
			/* System.out.println("mx" + mx);
			System.out.println("my" + my);
			System.out.println(row);
			System.out.println(col); */

			while (PennDraw.hasNextKeyTyped() == false) {
				// left empty to account for when key isn't pressed
			}

			//System.out.println("key");

			// add numbers to grid			
			char typed = PennDraw.nextKeyTyped();
			if (mx <= 0.7 && mx >= 0.1) {
				if (my <= 0.7 && my >= 0.1) {

					// delete selected number
					if (typed == 'D') {
						//System.out.println("D");
						grid.deleteNumber(row, col, Math.round(mx * 10 * 2) / 2.0 
								/ 10, Math.round(my * 10 * 2) / 2.0 / 10);

					} // error check: typed key not 1-6
					else if (typed < '1' || typed > '6') {
						PennDraw.text(0.5, 0.9, "ERROR: input must be from 1-6");
					}

					grid.addNumbers("" + typed, row, col, mx, my);
					grid.setErrorTrue();

				}
			}

		}
	}

}
